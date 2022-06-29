package net.rubencm.forum.topicsservice;

import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.shared.domain.valueobjects.User;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import net.rubencm.forum.topicsservice.domain.valueobjects.TopicTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Topics topics;

    @Test
    public void shouldReturnTopicIfExists() throws Exception {
        String id = UUID.randomUUID().toString();

        topics.save(Topic.create(
                new TopicId(id),
                new TopicTitle("topic title"),
                new User("username"),
                new ForumId(UUID.randomUUID())
        ));

        this.mockMvc.perform(get("/"+id))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorIfTopicNotExists() throws Exception {
        String id = UUID.randomUUID().toString();

        this.mockMvc.perform(get("/"+id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnListOfTopics() throws Exception {
        String forumId = UUID.randomUUID().toString();

        topics.save(Topic.create(
                new TopicId(UUID.randomUUID()),
                new TopicTitle("topic title"),
                new User("username"),
                new ForumId(forumId)
        ));

        this.mockMvc.perform(get("/byforum/" + forumId + "?page=0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    public void shouldReturnEmptyListOfTopicsIfForumNotExists() throws Exception {
        String forumId =  UUID.randomUUID().toString();

        this.mockMvc.perform(get("/byforum/" + forumId + "?page=0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }
}
