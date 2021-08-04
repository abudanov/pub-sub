package io.github.abudanov.pubsub.publisher.utils;

import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.commondata.value.Action;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MessageGenerator {
    private static final Action[] actions = Action.values();
    private final Random random = new Random();
    private final AtomicLong idSequence = new AtomicLong(0L);
    private final long MIN_MSISDN = 7_900_000_00_00L;
    private final long MAX_MSISDN = 7_999_999_99_99L;

    public MessageDto next() {
        return new MessageDto(
                getId(),
                getMsisdn(),
                getAction(),
                System.currentTimeMillis() / 1000
        );
    }

    private long getMsisdn() {
        return ThreadLocalRandom.current().nextLong(MIN_MSISDN, MAX_MSISDN);
    }

    private long getId() {
        return idSequence.updateAndGet(value -> value == Long.MAX_VALUE ? 0 : value + 1);
    }

    private Action getAction() {
        return actions[random.nextInt(Action.values().length)];
    }
}
