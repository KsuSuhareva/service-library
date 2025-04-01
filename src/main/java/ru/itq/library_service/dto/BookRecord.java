package ru.itq.library_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BookRecord {
    @NotNull
    @JsonProperty("username")
    private String userName;
    @NotNull
    private String userFullName;
    @NotNull
    private boolean userActive;
    @NotNull
    private String bookName;
    @NotNull
    private String bookAuthor;
}
