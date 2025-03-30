package ru.itq.library_service.service;

import ru.itq.library_service.dto.SubscriptionDto;

public interface SubscriptionService {
    SubscriptionDto findByUserFullName(String userFullName);
}
