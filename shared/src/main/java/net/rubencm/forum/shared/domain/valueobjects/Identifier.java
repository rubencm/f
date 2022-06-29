package net.rubencm.forum.shared.domain.valueobjects;


import lombok.NonNull;

import java.io.Serializable;
import java.util.UUID;

public abstract class Identifier implements Serializable {
    protected final UUID value;

    protected Identifier(@NonNull String value) {
        this.value = UUID.fromString(value);
    }

    protected Identifier() {
        this.value = null;
    }

    public @NonNull String value() {
        return this.value.toString();
    }
}
