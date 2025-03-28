package ru.itq.library_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.itq.library_service.dto.BookRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountingBookControllerTest extends IntegrationTest {

    @Test
    void testPublishToQueue() throws Exception {
        List<BookRecord> records = new ArrayList<>();
        records.add(new BookRecord("Suhareva", "Сухарева Ксения Владимировна", "k.sukhareva@itq-group.com", true,
                "Властелин колец", "Дж. Р. Р. Толкин", LocalDateTime.now(), LocalDateTime.now().toLocalDate(), null));

        records.add(new BookRecord("Petrov", "Петров Петр Петрович", "p.petrov@itq-group.com", true,
                "Властелин колец", "Дж. Р. Р. Толкин", LocalDateTime.now(), LocalDateTime.now().toLocalDate(), null));


        mockMvc.perform(MockMvcRequestBuilders.post("/accountingBooks/pushToQueue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(records)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}