package net.rubencm.forum.shared.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class ForumId extends Identifier {
    public ForumId(@NonNull String value) {
        super(value);
    }

    public ForumId(@NonNull UUID value) {
        super(value.toString());
    }

    ForumId() {
        super();
    }
}
