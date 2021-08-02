package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.subscriber.entity.SubscriptionEntity;
import io.github.abudanov.pubsub.subscriber.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("SUBSCRIPTION")
@RequiredArgsConstructor
public class SubscriptionHandler implements Handler<MessageDto> {

    @Autowired
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void handle(MessageDto messageDto) {
        SubscriptionEntity entity = dtoToEntity(messageDto);
        subscriptionRepository.save(entity);
    }

    private SubscriptionEntity dtoToEntity(MessageDto dto) {
        return new SubscriptionEntity(
                dto.getId(),
                dto.getMsisdn(),
                new Timestamp(dto.getTimestamp() * 1000)
        );
    }
}
