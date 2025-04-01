package ru.itq.library_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Запись о книге")
public class BookRecord {
    @NotNull
    @JsonProperty("username")
    @Schema(description = "Имя пользователя", example = "suhareva", required = true)
    private String userName;

    @NotNull
    @Schema(description = "Полное имя пользователя", example = "Suhareva Ksu", required = true)
    private String userFullName;

    @NotNull
    @Schema(description = "Активность пользователя", example = "true", required = true)
    private boolean userActive;

    @NotNull
    @Schema(description = "Название книги", example = "Война и мир", required = true)
    private String bookName;

    @NotNull
    @Schema(description = "Автор книги", example = "Лев Толстой", required = true)
    private String bookAuthor;
}