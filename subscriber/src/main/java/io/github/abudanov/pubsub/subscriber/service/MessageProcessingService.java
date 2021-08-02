package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageProcessingService {

    @Autowired
    private final Map<String, Handler<MessageDto>> actionNameToMessageHandlerMap;

    public void process(MessageDto messageDto) {
        String actionName = messageDto.getAction().name();
        Handler<MessageDto> handler = actionNameToMessageHandlerMap.get(actionName);
        handler.handle(messageDto);
    }
}
