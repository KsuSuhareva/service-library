package ru.itq.library_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о подписке пользователя")
public class SubscriptionDto {

    @Schema(description = "Имя пользователя", example = "suhareva")
    private String userName;

    @Schema(description = "Полное имя пользователя", example = "Suhareva Ksu")
    private String userFullName;

    @Schema(description = "Статус активности пользователя", example = "true")
    private boolean userActive;

    @Schema(description = "Список книг, связанных с подпиской")
    private List<BookDto> books;
}
