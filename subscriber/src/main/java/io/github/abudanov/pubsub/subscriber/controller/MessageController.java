package io.github.abudanov.pubsub.subscriber.controller;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.subscriber.service.MessageProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private final MessageProcessingService messageProcessingService;

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MessageDto messageDto) {
        log.info("Accept new request: " + messageDto);
        messageProcessingService.process(messageDto);
    }
}
