package ru.itq.library_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itq.library_service.controller.handler.ErrorMessage;
import ru.itq.library_service.dto.AccountingBookData;
import ru.itq.library_service.dto.SubscriptionDto;

public interface SubscriptionController {
    @Operation(summary = "Наполнение очереди в асинхронном режиме", description = "Возвращает статус OK")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Принято",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountingBookData.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Абонемент не найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервера",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))})
    })
    @GetMapping
    ResponseEntity<SubscriptionDto> getByUserName(@RequestParam @Pattern(regexp = "(.|\\s)*\\S(.|\\s)*") String username);
}
