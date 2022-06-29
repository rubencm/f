package net.rubencm.forum.messagesservice.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.shared.domain.query.Response;

import java.util.Date;

@Data
@AllArgsConstructor
public class GetMessageResponse implements Response {
    @NonNull
    private String id;
    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private String posterName;
    @NonNull
    private Integer numModifications;
    @NonNull
    private Date creationDate;
    @NonNull
    private Date lastModificationDate;
    @NonNull
    private String topicId;


    public static GetMessageResponse fromMessage(Message message) {
        return new GetMessageResponse(
                message.getId().value(),
                message.getTitle().value(),
                message.getMessageBody().value(),
                message.getPosterName().value(),
                message.getNumModifications().value(),
                message.getCreationDate(),
                message.getLastModificationDate(),
                message.getTopicId().value()
        );
    }
}
