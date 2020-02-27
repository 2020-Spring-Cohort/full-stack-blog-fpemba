package org.wcci.blog.storage;

import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Category;
import org.wcci.blog.storages.CategoryStorage;
import org.wcci.blog.storages.CategoryStorageJpaImpl;
import org.wcci.blog.storages.Repositories.CategoryRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryStorageJpaImplTest {

    @Test
    public void shouldStoreCategory() {
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        CategoryStorage storage = new CategoryStorageJpaImpl(categoryRepository);
        Category testCategory = new Category("water");
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(testCategory));
        storage.store(testCategory);
        verify(categoryRepository).save(testCategory);
        assertThat(storage.getAll()).contains(testCategory);
    }

    @Test
    public void shouldRetrieveSinglePostByName() {
        CategoryRepository mockRepo = mock(CategoryRepository.class);
        Category testCategory1 = new Category("water");
        Category testCategory2 = new Category("soda");
        CategoryStorage underTest = new CategoryStorageJpaImpl(mockRepo);
        underTest.store(testCategory1);
        underTest.store(testCategory2);
        Optional<Category> testCategory1Optional = Optional.of(testCategory1);
        when(mockRepo.findByName("water")).thenReturn(testCategory1Optional);

        Optional<Category> testCategory2Optional = Optional.of(testCategory2);
        when(mockRepo.findByName("soda")).thenReturn(testCategory2Optional);

        Category retrievedCategory1 = underTest.findCategoryByName("water");
        Category retrievedCategory2 = underTest.findCategoryByName("soda");
        assertThat(retrievedCategory1).isEqualTo(testCategory1);
        assertThat(retrievedCategory2).isEqualTo(testCategory2);
    }
}
