package ru.itq.library_service.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itq.library_service.model.entity.Subscription;
import ru.itq.library_service.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Validated
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<Subscription> getByUserName(@RequestParam @Pattern(regexp = "(.|\\s)*\\S(.|\\s)*") String username) {
        return new ResponseEntity<>(subscriptionService.findByUserFullName(username), HttpStatus.OK);
    }
}
