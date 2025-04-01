package ru.itq.library_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные о книгах для учета")
public class AccountingBookData {
    @NotBlank
    @JsonProperty("data")
    @Schema(description = "Список записей о книгах", required = true)
    private List<BookRecord> bookRecords;
}
