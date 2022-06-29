package net.rubencm.forum.messagesservice.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class PosterName {
    protected final String value;

    protected PosterName() {
        this.value = null;
    }

    public PosterName(@NonNull String value) {
        this.value = value;
    }

    public @NonNull String value() {
        return this.value;
    }
}
