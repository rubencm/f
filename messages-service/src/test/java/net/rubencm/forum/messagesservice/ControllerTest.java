package net.rubencm.forum.messagesservice;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageBody;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageTitle;
import net.rubencm.forum.messagesservice.domain.valueobjects.PosterName;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.UUID;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Messages messages;

    @Test
    public void shouldReturnMessageIfExists() throws Exception {
        // given
        String id = UUID.randomUUID().toString();

        messages.save(Message.create(
                new MessageId(id),
                new MessageTitle("message title"),
                new MessageBody("message body"),
                new PosterName("poster name"),
                new TopicId(UUID.randomUUID()),
                new ForumId(UUID.randomUUID())
        ));

        // when
        this.mockMvc.perform(get("/"+id))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorIfMessageNotExists() throws Exception {
        String id = "a42736f9-00f5-4fb7-a885-2355ccb46d04";

        // when
        this.mockMvc.perform(get("/"+id))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnOkWhenCreateMessage() throws Exception {
        // when
        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"messageTitle\":\"message title\",\n" +
                                "\"messageBody\": \"message body\",\n" +
                                "\"posterName\": \"poster name\",\n" +
                                "\"topicId\": \"5e6866d1-f92b-45b9-9fee-b67e06c2aad7\",\n" +
                                "\"forumId\": \"3bd1c45e-c8e0-4c78-9097-b5b47d518822\"\n" +
                                "}"))
                // then
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldValidateMessage() throws Exception {
        // when
        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"messageTitle\":\"a\",\n" +
                                "\"messageBody\": \"b\",\n" +
                                "\"posterName\": \"c\",\n" +
                                "\"topicId\": \"5e6866d1-f92b-45b9-9fee-b67e06c2aad7\",\n" +
                                "\"forumId\": \"3bd1c45e-c8e0-4c78-9097-b5b47d518822\"\n" +
                                "}"))
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnOkWhenCreateTopic() throws Exception {
        // when
        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"messageTitle\":\"message title\",\n" +
                                "\"messageBody\": \"message body\",\n" +
                                "\"posterName\": \"poster name\",\n" +
                                "\"forumId\": \"3bd1c45e-c8e0-4c78-9097-b5b47d518822\"\n" +
                                "}"))
                // then
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateMessage() throws Exception {
        // given
        String id = UUID.randomUUID().toString();

        messages.save(Message.create(
                new MessageId(id),
                new MessageTitle("message title"),
                new MessageBody("message body"),
                new PosterName("poster name"),
                new TopicId(UUID.randomUUID()),
                new ForumId(UUID.randomUUID())
        ));

        // when
        this.mockMvc.perform(put("/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"messageTitle\":\"new message title\",\n" +
                                "\"messageBody\": \"new message body\"\n" +
                                "}"))
                // then
                .andExpect(status().isOk());

        Message message = messages.byId(new MessageId(id)).get();

        Assert.isTrue(message.getTitle().value().equals("new message title"));
        Assert.isTrue(message.getMessageBody().value().equals("new message body"));
    }

    @Test
    public void shouldThrowErrorWhenUpdateIfMessageNotExists() throws Exception {
        // when
        this.mockMvc.perform(put("/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"messageTitle\":\"new message title\",\n" +
                                "\"messageBody\": \"new message body\"\n" +
                                "}"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteMessageIfExists() throws Exception {
        String id = "00021611-5221-44e8-97e6-740944e610cd";

        // given
        messages.save(Message.create(
                new MessageId(id),
                new MessageTitle("message title"),
                new MessageBody("message body"),
                new PosterName("poster name"),
                new TopicId(UUID.randomUUID()),
                new ForumId(UUID.randomUUID())
        ));

        // when
        this.mockMvc.perform(delete("/"+id))
                // then
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        Assert.isTrue(messages.byId(new MessageId(id)).isEmpty());

    }

    @Test
    public void shouldThrowErrorWhenDeleteMessageIfNotExists() throws Exception {
        String id = "85b307d0-97b0-4a1f-9b2e-1d96f4f41ddd";

        this.mockMvc.perform(delete("/"+id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnListOfMessages() throws Exception {
        String topicId = "0c65f461-e22d-4bdc-b84a-41811bd9e230";

        // given
        messages.save(Message.create(
                new MessageId(UUID.randomUUID()),
                new MessageTitle("message title"),
                new MessageBody("message body"),
                new PosterName("poster name"),
                new TopicId(topicId),
                new ForumId(UUID.randomUUID())
        ));

        // when
        this.mockMvc.perform(get("/bytopic/" + topicId + "?page=0"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    public void shouldReturnEmptyListOfMessagesIfTopicNotExists() throws Exception {
        String topicId = "7e5ecfab-b1de-4465-8530-e0961de7e9bc";

        this.mockMvc.perform(get("/bytopic/" + topicId + "?page=0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }
}
