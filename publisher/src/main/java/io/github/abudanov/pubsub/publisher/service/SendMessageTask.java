package io.github.abudanov.pubsub.publisher.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.publisher.client.SubscriberClient;
import io.github.abudanov.pubsub.publisher.utils.MessageGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendMessageTask implements Runnable {

    private final MessageGenerator messageGenerator;
    private final SubscriberClient subscriberClient;

    @Override
    public void run() {
        MessageDto messageDto = messageGenerator.next();
        try {
            subscriberClient.sendMessage(messageDto);
        } catch (RuntimeException e) {
            log.error("Error while sending the message: {}", messageDto, e);
        }
    }
}
