package io.github.abudanov.pubsub.commondata.value;

public enum Action {
    PURCHASE(0), SUBSCRIPTION(1);

    private final int value;

    Action(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
