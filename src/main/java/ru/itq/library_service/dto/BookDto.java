package ru.itq.library_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные о книге")
public class BookDto {

    @Schema(description = "Название книги", example = "Война и мир")
    private String bookName;

    @Schema(description = "Автор книги", example = "Лев Толстой")
    private String bookAuthor;
}
