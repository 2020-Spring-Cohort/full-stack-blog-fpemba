package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Author;
import org.wcci.blog.storages.AuthorStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorControllerTest {

    private MockMvc mockMvc;
    private AuthorController underTest;
    private AuthorStorage mockStorage;
    private Model mockModel;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        mockStorage = mock(AuthorStorage.class);
        underTest = new AuthorController(mockStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOneAuthor() {
        Author testAuthor = new Author("user");
        when(mockStorage.findAuthorByName("user")).thenReturn(testAuthor);

        underTest.displaySingleAuthor("user", mockModel);

        verify(mockStorage).findAuthorByName("user");
        verify(mockModel).addAttribute("author", testAuthor);
    }

    @Test
    public void shouldReturnViewNamedAuthorWhenDisplaySingleAuthorIsCalled() {
        String viewName = underTest.displaySingleAuthor("user", mockModel);
        assertThat(viewName).isEqualTo("author");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception {
        Author testAuthor = new Author("user");
        when(mockStorage.findAuthorByName("user")).thenReturn(testAuthor);

        mockMvc.perform(get("/author/single-author/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("author"))
                .andExpect(model().attributeExists("author"))
                .andExpect(model().attribute("author", testAuthor));
    }

    @Test
    public void tagsEndPointShouldDisplayAllTags() throws Exception {
        Author testAuthor = new Author("user");

        List<Author> authorList = Collections.singletonList(testAuthor);

        when(mockStorage.getAll()).thenReturn(authorList);

        mockMvc.perform(get("/author/all-authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attribute("authors", authorList));
    }

    @Test
    public void addTagShouldRedirect() throws Exception {
        mockMvc.perform(post("/author/add/").param("authorName", "user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).store(new Author("user"));
    }


}
