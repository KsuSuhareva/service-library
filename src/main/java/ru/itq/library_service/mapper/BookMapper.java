package ru.itq.library_service.mapper;

import org.mapstruct.Mapper;
import ru.itq.library_service.dto.BookDto;
import ru.itq.library_service.model.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto bookToBookDto(Book book);
}
