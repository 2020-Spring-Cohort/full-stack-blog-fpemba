package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Category;
import org.wcci.blog.storages.CategoryStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerTest {

    private MockMvc mockMvc;
    private CategoryController underTest;
    private CategoryStorage mockStorage;
    private Model mockModel;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        mockStorage = mock(CategoryStorage.class);
        underTest = new CategoryController(mockStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOneCategory() {
        Category testCategory = new Category("water");
        when(mockStorage.findCategoryById(1)).thenReturn(testCategory);

        underTest.displayCategoryFromPostPage(1, mockModel);

        verify(mockStorage).findCategoryById(1);
        verify(mockModel).addAttribute("category", testCategory);
    }

    @Test
    public void shouldReturnViewNamedCategoryWhenDisplaySingleCategoryIsCalled() {
        String viewName = underTest.displayCategoryFromPostPage(1, mockModel);
        assertThat(viewName).isEqualTo("category");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception {
        Category testCategory = new Category("water");
        when(mockStorage.findCategoryById(1)).thenReturn(testCategory);

        mockMvc.perform(get("/category/1/"))
                .andExpect(status().isOk())
                .andExpect(view().name("category"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("category", testCategory));
    }

    @Test
    public void categoriesEndPointShouldDisplayAllCategories() throws Exception {
        Category testCategory = new Category("Test");

        List<Category> categoryList = Collections.singletonList(testCategory);

        when(mockStorage.getAll()).thenReturn(categoryList);

        mockMvc.perform(get("/category/all-categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("categories"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", categoryList));
    }

    @Test
    public void addCategoryShouldRedirect() throws Exception {
        mockMvc.perform(post("/category/add/").param("categoryName", "test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).store(new Category("test"));
    }


}
