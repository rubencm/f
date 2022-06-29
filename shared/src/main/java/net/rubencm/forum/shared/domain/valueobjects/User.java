package net.rubencm.forum.shared.domain.valueobjects;

import lombok.Value;

@Value
public class User {
    String value;

    public User(String value) {
        this.validateLength(value);
        this.value = value;
    }

    public User() {
        this.value = null;
    }

    public String value() {
        return this.value;
    }

    void validateLength(String value) {
        Integer length = value.length();
        if (length == 0) return;
        if (length < 5 || length > 20) {
            throw new IllegalArgumentException("Value must have a length between 5 and 20 characters.");
        }
    }
}
