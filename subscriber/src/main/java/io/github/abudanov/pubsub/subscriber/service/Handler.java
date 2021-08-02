package io.github.abudanov.pubsub.subscriber.service;

public interface Handler<T> {
    void handle(T t);
}
