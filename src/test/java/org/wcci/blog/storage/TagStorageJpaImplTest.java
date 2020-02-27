package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storages.Repositories.TagRepository;
import org.wcci.blog.storages.TagStorage;
import org.wcci.blog.storages.TagStorageJpaImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TagStorageJpaImplTest {

    private TagRepository tagRepo;
    private TagStorage underTest;
    private Tag testTag;

    @BeforeEach
    void setUp() {
        tagRepo = mock(TagRepository.class);
        underTest = new TagStorageJpaImpl(tagRepo);
        Author testAuthor = new Author("user");
        Category testCategory = new Category("water");
        Post testPost = new Post("test", "test");
        testTag = new Tag("#fresh", testPost);
    }

    @Test
    public void shouldFindTagByName() {
        when(tagRepo.findByName("#fresh")).thenReturn(Optional.of(testTag));
        Tag retrievedTag = underTest.findTagByName("#fresh");
        assertThat(retrievedTag).isEqualTo(testTag);
    }

    @Test
    public void shouldFindTagById() {
        when(tagRepo.findById(1L)).thenReturn(Optional.of(testTag));
        Tag retrievedTag = underTest.findTagById(1L);
        assertThat(retrievedTag).isEqualTo(testTag);
    }
}
