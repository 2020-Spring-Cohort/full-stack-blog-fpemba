package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class SpringWebAppTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReceiveOKFromPostsEndpoint() throws Exception {
        mockMvc.perform(get("/post/all-posts"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void shouldReceiveOKFromCategoriesEndPoint() throws Exception {

        mockMvc.perform(get("/category/all-categories"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveOKFromAuthorsEndPoint() throws Exception {

        mockMvc.perform(get("/author/all-authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveOKFromTagsEndPoint() throws Exception {

        mockMvc.perform(get("/tag/all-tags"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
