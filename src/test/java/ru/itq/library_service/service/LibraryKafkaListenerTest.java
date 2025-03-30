package ru.itq.library_service.service;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import ru.itq.library_service.config.WithContext;
import ru.itq.library_service.dto.BookRecord;
import ru.itq.library_service.kafka.LibraryKafkaListener;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class LibraryKafkaListenerTest extends WithContext {
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//    @InjectMocks
//    private LibraryKafkaListener libraryKafkaListener;
//
//    @MockBean
//    private AccountingBookService accountingBookService;
//
//    @DynamicPropertySource
//    static void setKafkaProperties(DynamicPropertyRegistry registry) {
//        registry.add("publish.record.topic", () -> "listen-record");
//        registry.add("spring.kafka.consumer.group-id", () -> "test-group");
//    }
//
//    @BeforeEach
//    void setUp() {
//        ReflectionTestUtils.setField(libraryKafkaListener, "accountingBookService", accountingBookService);
//    }
//
//    @Test
//    void testKafkaListenerProcessesMessages() throws Exception {
//        List<BookRecord> records = new ArrayList<>();
//        records.add(new BookRecord("Suhareva", "Сухарева Ксения Владимировна", "k.sukhareva@itq-group.com",
//                true, "Властелин колец", "Дж. Р. Р. Толкин", LocalDate.of(1954, 6, 29),
//                LocalDate.of(2025, 3, 30), null));
//        records.add(new BookRecord("Petrov", "Петров Петр Петрович", "p.petrov@itq-group.com",
//                true, "Властелин колец", "Дж. Р. Р. Толкин", LocalDate.of(1954, 6, 29),
//                LocalDate.of(2024, 3, 30), LocalDate.of(2024, 4, 15)));
//
//        for (BookRecord record : records) {
//            kafkaTemplate.send("listen-record", objectMapper.writeValueAsString(record));
//        }
//
//        Awaitility.await()
//                .atMost(Duration.ofSeconds(5))
//                .untilAsserted(() ->
//                        verify(accountingBookService, times(1)).saveOrUpdateBatch(records)
//                );
//    }
}
