package ru.itq.library_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.itq.library_service.config.WithContext;
import ru.itq.library_service.dto.BookRecord;
import ru.itq.library_service.model.entity.AccountingBook;
import ru.itq.library_service.repository.AccountingBookRepository;
import ru.itq.library_service.service.impl.AccountingBookServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountingBookServiceImplTest extends WithContext {

    @Autowired
    private AccountingBookServiceImpl accountingBookService;

    @Autowired
    private AccountingBookRepository accountingBookRepository;


    @Test
    @Transactional
    public void testSaveOrUpdateBatch() {
        BookRecord bookRecord = new BookRecord(
                "Suhareva", "Сухарева Ксения Владимировна", "sukhareva@itq-group.com", true,
                "Властелин колец", "Дж. Р. Р. Толкин", LocalDate.now(), LocalDate.now(), null
        );

        accountingBookService.saveOrUpdateBatch(List.of(bookRecord));
        List<AccountingBook> accountingBooks = accountingBookRepository.findAll();

        AccountingBook savedAccountingBook = accountingBooks.get(4);
        assertThat(savedAccountingBook).isNotNull();
        assertEquals(savedAccountingBook.getBook().getTitle(), bookRecord.getBookTitle());
        assertEquals(savedAccountingBook.getBook().getAuthor(), bookRecord.getBookAuthor());
        assertThat(savedAccountingBook.getSubscription()).isNotNull();
        assertEquals(savedAccountingBook.getSubscription().getUserFullName(), bookRecord.getUserFullName());
        assertEquals(savedAccountingBook.getSubscription().getBooks().get(0).getTitle(), bookRecord.getBookTitle());
    }

    @Test
    public void testFindOverdueBooks() {
        List<AccountingBook> books = accountingBookService.findOverdueBooks();
        assertThat(books).isNotNull();
        assertThat(books).isNotEmpty();
    }
}
