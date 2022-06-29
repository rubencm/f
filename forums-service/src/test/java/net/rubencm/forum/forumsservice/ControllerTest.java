package net.rubencm.forum.forumsservice;

import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.forumsservice.domain.repositories.Forums;
import net.rubencm.forum.forumsservice.domain.valueobjects.ForumDescription;
import net.rubencm.forum.forumsservice.domain.valueobjects.ForumIcon;
import net.rubencm.forum.forumsservice.domain.valueobjects.ForumName;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Forums forums;

    @Test
    public void shouldReturnForumIfExist() throws Exception {
        String id = "e134064a-4d4d-47c5-8ae9-26c4443019c6";
        String name = "forum name";

        // given
        forums.save(Forum.create(
                new ForumId(id),
                new ForumName(name),
                new ForumDescription("forum description"),
                new ForumIcon("icon")
        ));

        // when
        this.mockMvc.perform(get("/"+id))
        // then
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(name)));
    }

    @Test
    public void shouldReturnNotFoundIfForumNoExist() throws Exception {
        String id = "278357a0-1161-4056-84b5-4af477c33086";

        // when
        this.mockMvc.perform(get("/"+id))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnListOfForums() throws Exception {
        // given
        forums.save(Forum.create(
                new ForumId(UUID.randomUUID()),
                new ForumName("forum name"),
                new ForumDescription("forum description"),
                new ForumIcon("icon")
        ));

        // when
        this.mockMvc.perform(get("/"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    public void shouldReturnEmptyListOfForums() throws Exception {
        // when
        this.mockMvc.perform(get("/"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }
}
