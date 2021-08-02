package io.github.abudanov.pubsub.publisher.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.commondata.value.Action;
import io.github.abudanov.pubsub.publisher.client.SubscriberClient;
import io.github.abudanov.pubsub.publisher.utils.IdGenerator;
import io.github.abudanov.pubsub.publisher.utils.PhoneGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;


@Component
@Slf4j
@RequiredArgsConstructor
public class SendMessageTask implements Runnable {

    @Autowired
    private final IdGenerator idGenerator;
    @Autowired
    private final PhoneGenerator phoneGenerator;
    @Autowired
    private final SubscriberClient subscriberClient;
    private final Random random = new Random();

    @Override
    public void run() {
        MessageDto dto = new MessageDto(
                idGenerator.next(),
                phoneGenerator.next(),
                Action.values()[random.nextInt(Action.values().length)],
                Instant.now().getEpochSecond()
        );
        ResponseEntity<String> response = subscriberClient.sendMessage(dto);
        log.info("Message was send. Request: " + dto + "; response: " + response.toString());
    }
}
