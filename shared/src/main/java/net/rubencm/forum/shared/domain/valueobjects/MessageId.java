package net.rubencm.forum.shared.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class MessageId extends Identifier {
    public MessageId(@NonNull String value) {
        super(value);
    }

    public MessageId(@NonNull UUID value) {
        super(value.toString());
    }

    MessageId() {
        super();
    }
}
