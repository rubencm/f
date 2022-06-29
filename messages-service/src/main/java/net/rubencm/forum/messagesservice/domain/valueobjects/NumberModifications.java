package net.rubencm.forum.messagesservice.domain.valueobjects;

import lombok.NonNull;
import lombok.Value;
import net.rubencm.forum.shared.domain.valueobjects.PositiveInteger;

@Value
public class NumberModifications extends PositiveInteger {
    public @NonNull NumberModifications(Integer value) {
        super(value);
    }

    public NumberModifications() {
        super();
    }
}
