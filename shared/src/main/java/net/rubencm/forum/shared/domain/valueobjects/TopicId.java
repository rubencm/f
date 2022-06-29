package net.rubencm.forum.shared.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class TopicId extends Identifier {
    public TopicId(@NonNull String value) {
        super(value);
    }

    public TopicId(@NonNull UUID value) {
        super(value.toString());
    }

    TopicId() {
        super();
    }
}
