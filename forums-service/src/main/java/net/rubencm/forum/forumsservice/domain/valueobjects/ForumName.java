package net.rubencm.forum.forumsservice.domain.valueobjects;

import lombok.NonNull;

public class ForumName {
    protected final String value;

    public ForumName(@NonNull String value) {
        this.validateLength(value);
        this.value = value;
    }

    protected ForumName() {
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
