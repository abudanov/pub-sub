package io.github.abudanov.pubsub.publisher.client;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class SubscriberClient {

    private final RestTemplate restTemplate;
    private final String host;

    public SubscriberClient(
            RestTemplate restTemplate,
            @Value("${client.subscriber.host}") String host
    ) {
        this.restTemplate = restTemplate;
        this.host = host;
    }

    public void sendMessage(MessageDto messageDto) {
        String endpoint = host + "/messages";
        ResponseEntity<String> response = restTemplate.postForEntity(endpoint, messageDto, String.class);
        if (response.getStatusCode().isError()) {
            log.error("Error while sending the message. Request: {}; response: {}", messageDto, response);
        } else {
            log.info("Message was send. Request: {}; response: {}", messageDto, response);
        }
    }
}
