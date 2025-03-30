package ru.itq.library_service.mapper;

import org.mapstruct.Mapper;
import ru.itq.library_service.dto.SubscriptionDto;
import ru.itq.library_service.model.entity.Subscription;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface SubscriptionMapper {
    SubscriptionDto subscriptionToSubscriptionDto(Subscription subscription);
}
