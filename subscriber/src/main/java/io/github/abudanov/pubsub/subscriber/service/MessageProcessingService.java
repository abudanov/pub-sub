package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.commondata.value.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageProcessingService {

    private final Map<Action, ActionSaver<MessageDto>> actionSaverMap;

    public MessageProcessingService(List<ActionSaver<MessageDto>> actionSavers) {
        this.actionSaverMap = actionSavers.stream()
                .collect(
                        Collectors.toMap(ActionSaver::getSupportedAction, actionSaver -> actionSaver)
                );
    }

    public void process(MessageDto messageDto) {
        ActionSaver<MessageDto> actionSaver = actionSaverMap.get(messageDto.getAction());
        log.info("Define the {} for the {} action", actionSaver, messageDto.getAction());
        actionSaver.save(messageDto);
    }
}
