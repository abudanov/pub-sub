package io.github.abudanov.pubsub.subscriber.controller;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.subscriber.service.MessageProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private final MessageProcessingService messageProcessingService;

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MessageDto messageDto) {
        messageProcessingService.process(messageDto);
    }
}
