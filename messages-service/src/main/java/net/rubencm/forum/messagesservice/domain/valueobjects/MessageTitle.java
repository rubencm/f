package net.rubencm.forum.messagesservice.domain.valueobjects;

import lombok.NonNull;

public class MessageTitle {
    protected final String value;

    public MessageTitle(@NonNull String value) {
        this.validateLength(value);
        this.value = value;
    }

    protected MessageTitle() {
        this.value = null;
    }

    public @NonNull String value() {
        return this.value;
    }

    private void validateLength(String value) {
        Integer length = value.length();
        if (length < 5 || length > 100) {
            throw new IllegalArgumentException("Value must have a length between 5 and 100 characters.");
        }
    }
}
