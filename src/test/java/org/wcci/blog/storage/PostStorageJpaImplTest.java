package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.PostStorage;
import org.wcci.blog.storages.PostStorageJpaImpl;
import org.wcci.blog.storages.Repositories.AuthorRepository;
import org.wcci.blog.storages.Repositories.CategoryRepository;
import org.wcci.blog.storages.Repositories.PostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PostStorageJpaImplTest {

    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private PostRepository postRepository;
    private PostStorage postStorage;
    private Post post;

    @BeforeEach
    void setUp() {
        authorRepository = mock(AuthorRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        postRepository = mock(PostRepository.class);
        postStorage = new PostStorageJpaImpl(authorRepository, categoryRepository, postRepository);
        Category category = new Category("water");
        Author author = new Author("user");
        post = new Post(author, category, "test", "test");
    }

    @Test
    public void shouldFindPostByID() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Post retrievedPost = (Post) postStorage.findPostById(1L);
        assertThat(retrievedPost).isEqualTo(post);
    }

    @Test
    public void shouldStorePost() {
        postStorage.store(post);
        verify(authorRepository).save(post.getAuthor());
        verify(categoryRepository).save(post.getCategory());
        verify(postRepository).save(post);

    }
}
