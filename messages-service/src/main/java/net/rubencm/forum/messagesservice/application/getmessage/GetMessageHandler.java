package net.rubencm.forum.messagesservice.application.getmessage;

import net.rubencm.forum.messagesservice.application.GetMessageResponse;
import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.findmessage.FindMessage;
import net.rubencm.forum.shared.domain.query.QueryHandler;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetMessageHandler implements QueryHandler<GetMessageQuery, GetMessageResponse> {
    final private FindMessage findMessage;

    public GetMessageHandler(@Autowired FindMessage findMessage) {
        this.findMessage = findMessage;
    }

    @Override
    public GetMessageResponse handle(GetMessageQuery query) {
        Message message = this.findMessage.execute(new MessageId(query.getId()));

        return GetMessageResponse.fromMessage(message);
    }
}
