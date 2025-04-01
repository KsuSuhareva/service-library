package ru.itq.library_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.itq.library_service.config.WithContext;
import ru.itq.library_service.dto.AccountingBookData;
import ru.itq.library_service.dto.BookRecord;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountingBookControllerTest extends WithContext {

    @Test
    void testPublishToQueue() throws Exception {
        List<BookRecord> records = new ArrayList<>();
        records.add(new BookRecord("Suhareva", "Сухарева Ксения Владимировна", true,
                "Властелин колец", "Дж. Р. Р. Толкин"));
        records.add(new BookRecord("Petrov", "Петров Петр Петрович", true,
                "Властелин колец", "Дж. Р. Р. Толкин"));
        AccountingBookData accountingBookData = new AccountingBookData(records);

        mockMvc.perform(MockMvcRequestBuilders.post("/accountingbooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountingBookData)))
                .andExpect(status().isOk());
    }
}