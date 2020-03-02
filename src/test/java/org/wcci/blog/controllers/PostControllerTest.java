package org.wcci.blog.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.AuthorStorage;
import org.wcci.blog.storages.CategoryStorage;
import org.wcci.blog.storages.PostStorage;
import org.wcci.blog.storages.TagStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {

    private PostController underTest;
    private Model model;
    private PostStorage mockPostStorage;
    private Post testPost;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockPostStorage = mock(PostStorage.class);
        CategoryStorage mockCategoryStorage = mock(CategoryStorage.class);
        TagStorage mockTagStorage = mock(TagStorage.class);
        AuthorStorage mockAuthorStorage = mock(AuthorStorage.class);
        underTest = new PostController(mockPostStorage, mockAuthorStorage, mockCategoryStorage, mockTagStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        model = mock(Model.class);
        Author testAuthor = new Author("user");
        Category testCategory = new Category("water");
        testPost = new Post("test", "test");
        when(mockCategoryStorage.findCategoryByName("water")).thenReturn(testCategory);
        when(mockPostStorage.findPostById(1L)).thenReturn(testPost);
    }

    @Test
    public void displayPostReturnsPostTemplate() {
        String result = underTest.displayPostFromAuthorPage((long) 1, model);
        assertThat(result).isEqualTo("post");

    }

    @Test
    public void displayPostInteractsWithDependenciesCorrectly() {
        underTest.displayPostFromAuthorPage((long) 1, model);
        verify(mockPostStorage).findPostById(1L);
        verify(model).addAttribute("post", testPost);


    }

    @Test
    public void displayPostMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/post/single-post/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", testPost));
    }

    @Test
    public void addPostShouldRedirect() throws Exception {
        mockMvc.perform(post("/post/add")
                .param("author", "user")
                .param("category", "water")
                .param("postTitle", "test")
                .param("postBody", "test"))
                .andExpect(status().is3xxRedirection());

        verify(mockPostStorage).store(new Post("user", "water", "test", "test"));
    }


}
