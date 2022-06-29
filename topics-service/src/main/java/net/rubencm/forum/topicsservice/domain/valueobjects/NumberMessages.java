package net.rubencm.forum.topicsservice.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;
import net.rubencm.forum.shared.domain.valueobjects.PositiveInteger;

@Value
public class NumberMessages extends PositiveInteger {
    public @NonNull NumberMessages(Integer value) {
        super(value);
    }

    NumberMessages() {
        super();
    }
}
