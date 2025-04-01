package ru.itq.library_service.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itq.library_service.config.LibraryProperties;
import ru.itq.library_service.dto.BookRecord;
import ru.itq.library_service.kafka.LibraryKafkaPublisher;
import ru.itq.library_service.model.entity.AccountingBook;
import ru.itq.library_service.model.entity.Book;
import ru.itq.library_service.model.entity.Subscription;
import ru.itq.library_service.repository.AccountingBookRepository;
import ru.itq.library_service.service.AccountingBookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountingBookServiceImpl implements AccountingBookService {
    private static final String FIND_BOOK_SQL = "SELECT b FROM Book b WHERE b.bookName  = :bookName AND b.bookAuthor = :bookAuthor";
    private static final String FIND_SUBSCRIPTION_SQL = "SELECT s FROM Subscription s JOIN FETCH s.books WHERE s.userFullName = :fullName";
    private static final Integer ALLOWED_PERIOD_DAYS = 20;
    private final LibraryProperties properties;
    private final AccountingBookRepository accountingBookRepository;
    private final LibraryKafkaPublisher libraryPublisher;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AccountingBook> findOverdueBooks() {
        return accountingBookRepository.findOverdueBooks(LocalDate.now().minusDays(ALLOWED_PERIOD_DAYS));
    }

    @Override
    public void publishToQueue(List<BookRecord> records) {
        records.stream()
                .forEach(record -> libraryPublisher.publish(record, "push-record", properties.getQueueRecordTopic()));
        log.info("Publish {} record", records.size());
    }

    @Override
    @Transactional
    public void saveOrUpdateBatch(List<BookRecord> records) {
        Map<String, Book> bookCache = new HashMap<>();
        Map<String, Subscription> subscriptionCache = new HashMap<>();

        for (BookRecord record : records) {
            Book book = findBookFromCacheOrBase(record, bookCache);
            Subscription subscription = findSubscriptionFromCacheOrBase(record, book, subscriptionCache);
            saveAccountingBook(book, subscription);
        }
        entityManager.flush();
        entityManager.clear();

        bookCache.clear();
        subscriptionCache.clear();
        log.info("Successful bach");
    }

    private Book findBookFromCacheOrBase(BookRecord record, Map<String, Book> bookCache) {
        return bookCache.computeIfAbsent(record.getBookName(), bookName -> {
            return entityManager.createQuery(FIND_BOOK_SQL, Book.class)
                    .setParameter("bookName", record.getBookName())
                    .setParameter("bookAuthor", record.getBookAuthor())
                    .setMaxResults(1)
                    .getResultStream()
                    .findFirst()
                    .orElseGet(() -> {
                        Book newBook = new Book(record.getBookName(), record.getBookAuthor());
                        entityManager.persist(newBook);
                        return newBook;
                    });
        });
    }


    private Subscription findSubscriptionFromCacheOrBase(BookRecord record, Book newBook, Map<String, Subscription> subscriptionCache) {
        Subscription subscription = subscriptionCache.computeIfAbsent(record.getUserFullName(), fullName -> {
            return entityManager.createQuery(FIND_SUBSCRIPTION_SQL, Subscription.class)
                    .setParameter("fullName", fullName)
                    .setMaxResults(1)
                    .getResultStream()
                    .findFirst()
                    .orElseGet(() -> {
                        Subscription newSubscription = new Subscription(record.getUserName(), record.getUserFullName(), record.isUserActive());
                        entityManager.persist(newSubscription);
                        return newSubscription;
                    });
        });
        return updateSubscriptionWithBook(subscription, newBook);
    }

    private Subscription updateSubscriptionWithBook(Subscription subscription, Book book) {
        List<Book> books = subscription.getBooks();
        if (books == null) {
            books = new ArrayList<>();
        }
        if (!books.contains(book)) {
            books.add(book);
        }
        subscription.setBooks(books);
        return entityManager.merge(subscription);
    }

    private void saveAccountingBook(Book book, Subscription subscription) {
        AccountingBook accountingBook = new AccountingBook(subscription, book);
        entityManager.persist(accountingBook);
    }
}
