package net.rubencm.forum.shared.domain.valueobjects;

import lombok.NonNull;

public abstract class PositiveInteger {
    protected final Integer value;

    protected PositiveInteger(Integer value) {
        this.validateThatIsEqualOrGreaterThanZero(value);
        this.value = value;
    }

    protected PositiveInteger() {
        this.value = null;
    }

    protected void validateThatIsEqualOrGreaterThanZero(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be equal or greather than zero.");
        }
    }

    public @NonNull Integer value() {
        return this.value;
    }
}
