package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.commondata.value.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class MessageProcessingServiceIntegrationTest {

    @Mock(name = "purchaseActionSaver")
    PurchaseActionSaver mockPurchaseActionSaver;

    @Mock(name = "subscriptionActionSaver")
    SubscriptionActionSaver mockSubscriptionActionSaver;

    private MessageProcessingService messageProcessingService;

    @BeforeEach
    public void init() {
        doNothing().when(mockPurchaseActionSaver).save(isA(MessageDto.class));
        when(mockPurchaseActionSaver.getSupportedAction()).thenReturn(Action.PURCHASE);

        doNothing().when(mockSubscriptionActionSaver).save(isA(MessageDto.class));
        when(mockSubscriptionActionSaver.getSupportedAction()).thenReturn(Action.SUBSCRIPTION);

        List<ActionSaver<MessageDto>> actionSavers = List.of(mockPurchaseActionSaver, mockSubscriptionActionSaver);
        messageProcessingService = new MessageProcessingService(actionSavers);
    }

    @Test
    public void isPurchaseActionPassed() {
        MessageDto messageDto = new MessageDto(0, 0, Action.PURCHASE, System.currentTimeMillis() / 1000);
        messageProcessingService.process(messageDto);
        verify(mockPurchaseActionSaver, times(1)).save(messageDto);
        verify(mockSubscriptionActionSaver, times(0)).save(messageDto);
    }

    @Test
    public void isSubscriptionActionPassed() {
        MessageDto messageDto = new MessageDto(0, 0, Action.SUBSCRIPTION, System.currentTimeMillis() / 1000);
        messageProcessingService.process(messageDto);
        verify(mockSubscriptionActionSaver, times(1)).save(messageDto);
        verify(mockPurchaseActionSaver, times(0)).save(messageDto);

    }
}