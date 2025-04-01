package ru.itq.library_service.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itq.library_service.dto.BookDto;
import ru.itq.library_service.model.entity.Book;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookMapperTest {
    @InjectMocks
    private static BookMapper bookMapper;

    @BeforeAll
    static void init() {
        bookMapper = new BookMapperImpl();
    }

    @Test
    void SubscriptionToSubscriptionDto() {
        Book book = new Book("Властелин колец", "Дж. Р. Р. Толкин");

        BookDto bookDto = bookMapper.bookToBookDto(book);

        assertEquals(bookDto.getBookName(), book.getBookName());
        assertEquals(bookDto.getBookAuthor(), book.getBookAuthor());
    }

}
