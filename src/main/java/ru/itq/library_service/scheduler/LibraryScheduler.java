package ru.itq.library_service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itq.library_service.model.entity.AccountingBook;
import ru.itq.library_service.sender.Message;
import ru.itq.library_service.sender.NotificationSender;
import ru.itq.library_service.service.AccountingBookService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibraryScheduler {
    private final AccountingBookService accountingBookService;
    private final NotificationSender sender;

    @Scheduled(cron = "${notifications.cron}")
    public void checkOverdueBooks() {
        List<AccountingBook> accountingBooks = accountingBookService.findOverdueBooks();
        if (accountingBooks.isEmpty()) {
            return;
        }

        List<String> emails = accountingBooks.stream()
                .map(accountingBook -> accountingBook.getSubscription().getUserEmail())
                .toList();

        Message message = Message.builder()
                .title("Напоминание: Верните книгу!")
                .text("Вы держите книгу более 20 дней. Пожалуйста, верните её в библиотеку.")
                .recipientContacts(emails)
                .build();

        sender.send(message);
        log.info("Отправка уведомлений прошла успешно");
    }
}
