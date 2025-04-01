package ru.itq.library_service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itq.library_service.controller.AccountingBookController;
import ru.itq.library_service.dto.AccountingBookData;
import ru.itq.library_service.service.AccountingBookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accountingbooks")
@Validated
public class AccountingBookControllerImpl implements AccountingBookController {
    private final AccountingBookService accountingBookService;

    @PostMapping
    @Override
    public ResponseEntity<HttpStatus> publishToQueue(@RequestBody AccountingBookData data) {
        accountingBookService.publishToQueue(data.getBookRecords());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
