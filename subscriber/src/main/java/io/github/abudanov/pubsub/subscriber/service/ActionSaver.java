package io.github.abudanov.pubsub.subscriber.service;

import io.github.abudanov.pubsub.commondata.value.Action;

public interface ActionSaver<T> {
    Action getSupportedAction();

    void save(T t);
}
