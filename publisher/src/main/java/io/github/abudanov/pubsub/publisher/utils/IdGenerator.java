package io.github.abudanov.pubsub.publisher.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {
    private final AtomicLong VALUE = new AtomicLong(0L);

    public long next() {
        return VALUE.updateAndGet(value -> value == Long.MAX_VALUE ? 0 : value + 1);
    }

}
