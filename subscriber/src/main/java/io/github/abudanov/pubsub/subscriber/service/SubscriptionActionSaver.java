package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.commondata.value.Action;
import io.github.abudanov.pubsub.subscriber.entity.SubscriptionEntity;
import io.github.abudanov.pubsub.subscriber.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionActionSaver implements ActionSaver<MessageDto> {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Action getSupportedAction() {
        return Action.SUBSCRIPTION;
    }

    @Override
    public void save(MessageDto messageDto) {
        SubscriptionEntity entity = dtoToEntity(messageDto);
        log.info("Saving entity {}", entity);
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
