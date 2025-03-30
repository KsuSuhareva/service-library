package ru.itq.library_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookRecord {
    @NotNull
    private String userLogin;
    @NotNull
    private String userFullName;
    @NotNull
    private String userEmail;
    @NotNull
    private boolean borrowAllowed;
    @NotNull
    private String bookTitle;
    @NotNull
    private String bookAuthor;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookPublishedDate;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate borrowedDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnedDate;
}
