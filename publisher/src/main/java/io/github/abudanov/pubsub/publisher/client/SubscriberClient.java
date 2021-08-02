package io.github.abudanov.pubsub.publisher.client;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SubscriberClient {


    private final RestTemplate restTemplate;
    private final String host;


    public SubscriberClient(
            @Autowired RestTemplate restTemplate,
            @Value("${client.subscriber.host}") String host
    ) {
        this.restTemplate = restTemplate;
        this.host = host;
    }

    public ResponseEntity<String> sendMessage(MessageDto dto) {
        String endpoint = host + "/messages";
        return restTemplate.postForEntity(endpoint, dto, String.class);
    }

}
