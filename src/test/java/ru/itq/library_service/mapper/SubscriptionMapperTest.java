package ru.itq.library_service.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itq.library_service.dto.BookDto;
import ru.itq.library_service.dto.SubscriptionDto;
import ru.itq.library_service.model.entity.Book;
import ru.itq.library_service.model.entity.Subscription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubscriptionMapperTest {
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private static SubscriptionMapper subscriptionMapper;

    @BeforeAll
    static void init() {
        subscriptionMapper = new SubscriptionMapperImpl();
    }

    @Test
    void SubscriptionToSubscriptionDto() {
        List<Book> books = new ArrayList<>();
        Book book = new Book("Властелин колец", "Дж. Р. Р. Толкин");
        books.add(book);
        Subscription subscription = new Subscription(1l, "login", "Сухарева Ксения",
                "k.sukhareva@itq-group.com", true, books);
        BookDto bookDto = new BookDto("Властелин колец", "Дж. Р. Р. Толкин");
        when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);
        SubscriptionDto subscriptionDto = subscriptionMapper.subscriptionToSubscriptionDto(subscription);

        assertEquals(subscriptionDto.getUserName(), subscription.getUserName());
        assertEquals(subscriptionDto.getUserFullName(), subscription.getUserFullName());
        assertNotNull(subscriptionDto.getBooks());
        assertFalse(subscriptionDto.getBooks().isEmpty());
        assertEquals(subscriptionDto.getBooks().get(0).getBookName(), book.getBookName());
        assertEquals(subscriptionDto.getBooks().get(0).getBookAuthor(), book.getBookAuthor());

    }
}
