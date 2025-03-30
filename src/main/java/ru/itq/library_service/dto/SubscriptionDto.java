package ru.itq.library_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    private String userLogin;
    private String userFullName;
    private String userEmail;
    private boolean borrowAllowed;
    private List<BookDto> books;
}
