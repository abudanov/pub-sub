package io.github.abudanov.pubsub.commondata.dto;

import io.github.abudanov.pubsub.commondata.value.Action;
import lombok.Value;

@Value
public class MessageDto {
    long id;
    long msisdn;
    Action action;
    long timestamp;
}
