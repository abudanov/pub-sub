package io.github.abudanov.pubsub.publisher.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class PhoneGenerator {
    private final long MIN_PHONE_NUMBER = 7_900_000_00_00L;
    private final long MAX_PHONE_NUMBER = 7_999_999_99_99L;

    public long next() {
        return ThreadLocalRandom.current().nextLong(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER);
    }

}
