package net.rubencm.forum.forumsservice.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;
import net.rubencm.forum.shared.domain.valueobjects.PositiveInteger;

@Value
public class NumberTopics extends PositiveInteger {
    public NumberTopics(@NonNull Integer value) {
        super(value);
    }

    NumberTopics() {
        super();
    }
}
