package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.commondata.value.Action;
import io.github.abudanov.pubsub.subscriber.entity.PurchaseEntity;
import io.github.abudanov.pubsub.subscriber.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseActionSaver implements ActionSaver<MessageDto> {

    private final PurchaseRepository purchaseRepository;

    @Override
    public Action getSupportedAction() {
        return Action.PURCHASE;
    }

    @Override
    public void save(MessageDto messageDto) {
        PurchaseEntity entity = dtoToEntity(messageDto);
        log.info("Saving entity {}",entity);
        purchaseRepository.save(entity);
    }

    private PurchaseEntity dtoToEntity(MessageDto dto) {
        return new PurchaseEntity(
                dto.getId(),
                dto.getMsisdn(),
                new Timestamp(dto.getTimestamp() * 1000)
        );
    }
}
