package net.rubencm.forum.messagesservice.application.getallmessages;

import net.rubencm.forum.messagesservice.application.GetMessagesListResponse;
import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.shared.domain.query.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllMessagesHandler implements QueryHandler<GetAllMessagesQuery, GetMessagesListResponse> {
    final private Messages messages;

    public GetAllMessagesHandler(@Autowired Messages messages) {
        this.messages = messages;
    }

    @Override
    public GetMessagesListResponse handle(GetAllMessagesQuery query) {
        List<Message> messages = this.messages.all();

        return GetMessagesListResponse.fromMessages(messages);
    }
}
