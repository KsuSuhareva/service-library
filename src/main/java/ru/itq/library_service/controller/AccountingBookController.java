package ru.itq.library_service.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itq.library_service.controller.handler.ErrorMessage;
import ru.itq.library_service.dto.AccountingBookData;

public interface AccountingBookController {

    @Operation(summary = "Наполнение очереди в асинхронном режиме", description = "Возвращает статус OK")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Принято",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountingBookData.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный формат json",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервера",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))})
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HttpStatus> publishToQueue(@RequestBody AccountingBookData data);

}
