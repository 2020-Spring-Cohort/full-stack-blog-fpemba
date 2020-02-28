package org.wcci.blog.integration;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.storages.AuthorStorage;
import org.wcci.blog.storages.CategoryStorage;
import org.wcci.blog.storages.PostStorage;
import org.wcci.blog.storages.TagStorage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PostStorage postStorage;
    @MockBean
    AuthorStorage authorStorage;
    @MockBean
    CategoryStorage categoryStorage;
    @MockBean
    TagStorage tagStorage;

    @Test
    public void postsShouldBeOkAndReturnThePostViewWithPostsModelAttribute() throws Exception {
        mockMvc.perform(get("/post/all-posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("post"));
    }

    @Test
    public void authorsShouldBeOkAndReturnTheAuthorViewWithAuthorsModelAttribute() throws Exception {
        mockMvc.perform(get("/author/all-authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"));
    }

    @Test
    public void categoriesShouldBeOkAndReturnTheCategoryViewWithCategoriesModelAttribute() throws Exception {
        mockMvc.perform(get("/category/all-categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("categories"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void tagsShouldBeOkAndReturnTheTagViewWithTagsModelAttribute() throws Exception {
        mockMvc.perform(get("/tag/all-tags"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("tags"));
    }
}
