package net.rubencm.forum.messagesservice.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class MessageBody {
    String value;

    public MessageBody(@NonNull String value) {
        this.validateLength(value);
        this.value = value;
    }

    MessageBody() {
        this.value = null;
    }

    public @NonNull String value() {
        return this.value;
    }

    private void validateLength(String value) {
        Integer length = value.length();
        if (length < 3 || length > 4000) {
            throw new IllegalArgumentException("Value must have a max length between 3 and 4000 characters.");
        }
    }
}
