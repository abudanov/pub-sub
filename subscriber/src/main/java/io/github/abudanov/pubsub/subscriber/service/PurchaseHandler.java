package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.subscriber.entity.PurchaseEntity;
import io.github.abudanov.pubsub.subscriber.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("PURCHASE")
@RequiredArgsConstructor
public class PurchaseHandler implements Handler<MessageDto> {

    private final PurchaseRepository purchaseRepository;

    @Override
    public void handle(MessageDto messageDto) {
        PurchaseEntity entity = dtoToEntity(messageDto);
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
