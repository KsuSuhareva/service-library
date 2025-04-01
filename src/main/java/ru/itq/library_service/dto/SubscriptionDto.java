package ru.itq.library_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String userName;
    private String userFullName;
    private boolean userActive;
    private List<BookDto> books;
}
