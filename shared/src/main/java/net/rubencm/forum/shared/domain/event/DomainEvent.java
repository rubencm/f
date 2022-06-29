package net.rubencm.forum.shared.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.*;
import java.time.LocalDateTime;

// Serialize and deserialize should handle json
// because the current format is only usable
// inside this application

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class DomainEvent implements Serializable {
    String aggregateId = null;
    String ocurredOn = LocalDateTime.now().toString();

    public DomainEvent(@NonNull String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public static DomainEvent deserialize(byte[] b) {
        ByteArrayInputStream bi = new ByteArrayInputStream(b);
        ObjectInputStream si;
        try {
            si = new ObjectInputStream(bi);
            return (DomainEvent) si.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract String eventName();

    public byte[] serialize() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = null;
        try {
            so = new ObjectOutputStream(bo);
            so.writeObject(this);
            so.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bo.toByteArray();
    }
}
