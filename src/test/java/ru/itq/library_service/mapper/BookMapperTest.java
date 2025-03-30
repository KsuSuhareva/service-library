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
        Book book = new Book("Властелин колец", "Дж. Р. Р. Толкин",
                LocalDate.of(1954, 6, 29).atStartOfDay());

        BookDto bookDto = bookMapper.bookToBookDto(book);

        assertEquals(bookDto.getTitle(), book.getTitle());
        assertEquals(bookDto.getAuthor(), book.getAuthor());
        assertEquals(bookDto.getPublishedDate(), book.getPublishedDate());
    }

}
