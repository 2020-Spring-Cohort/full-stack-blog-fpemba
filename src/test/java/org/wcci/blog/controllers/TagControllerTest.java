package org.wcci.blog.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storages.TagStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TagControllerTest {

    private MockMvc mockMvc;
    private TagController underTest;
    private TagStorage mockStorage;
    private Model mockModel;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        mockStorage = mock(TagStorage.class);
        underTest = new TagController(mockStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOneTag() {
        Tag testTag = new Tag("fresh");
        when(mockStorage.findTagById(1)).thenReturn(testTag);

        underTest.displaySingleTag(1, mockModel);

        verify(mockStorage).findTagById(1);
        verify(mockModel).addAttribute("tag", testTag);
    }

    @Test
    public void shouldReturnViewNamedTagWhenDisplaySingleTagIsCalled() {
        String viewName = underTest.displaySingleTag(1, mockModel);
        assertThat(viewName).isEqualTo("tag");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception {
        Tag testTag = new Tag("fresh");
        when(mockStorage.findTagById(1)).thenReturn(testTag);

        mockMvc.perform(get("/tag/1/"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag"))
                .andExpect(model().attributeExists("tag"))
                .andExpect(model().attribute("tag", testTag));
    }

    @Test
    public void tagsEndPointShouldDisplayAllTags() throws Exception {
        Tag testTag = new Tag("Test");

        List<Tag> tagList = Collections.singletonList(testTag);

        when(mockStorage.getAll()).thenReturn(tagList);

        mockMvc.perform(get("/tag/all-tags"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("tags", tagList));
    }

    @Test
    public void addTagShouldRedirect() throws Exception {
        mockMvc.perform(post("/tag/add/").param("tagName", "test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).add(new Tag("test"));
    }


}




