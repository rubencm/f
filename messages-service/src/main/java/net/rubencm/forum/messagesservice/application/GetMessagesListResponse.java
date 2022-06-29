package net.rubencm.forum.messagesservice.application;

import lombok.Data;
import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.shared.domain.query.Response;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetMessagesListResponse extends ArrayList<GetMessageResponse> implements Response {

    public static GetMessagesListResponse fromMessages(List<Message> messages) {
        GetMessagesListResponse getAllForumsResponse = new GetMessagesListResponse();

        for (Message message : messages) {
            getAllForumsResponse.add(GetMessageResponse.fromMessage(message));
        }

        return getAllForumsResponse;
    }
}