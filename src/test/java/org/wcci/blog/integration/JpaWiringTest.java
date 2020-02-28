package org.wcci.blog.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.Repositories.AuthorRepository;
import org.wcci.blog.storages.Repositories.CategoryRepository;
import org.wcci.blog.storages.Repositories.PostRepository;
import org.wcci.blog.storages.Repositories.TagRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void authorShouldHaveAListOfPosts() {


        Author testAuthor = new Author("name");
        Category testCategory = new Category("water");
        Post testPost = new Post(testAuthor, testCategory, "postTitle", "postBody");

        authorRepository.save(testAuthor);
        postRepository.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Optional<Author> retrievedAuthorOptional = authorRepository.findById(testAuthor.getId());
        Author retrievedAuthor = retrievedAuthorOptional.get();
        Post retrievedPost = postRepository.findPostById(testPost.getId()).get();

        assertThat(retrievedAuthor.getPosts()).contains(testPost);
//        assertThat(retrievedPost.getAuthors()).contains(testAuthor);
    }

    @Test
    public void categoryShouldHaveAListOfPosts() {

        Category testCategory = new Category("water");
        Author testAuthor = new Author("name");

        Post testPost = new Post(testAuthor, testCategory, "postTitle", "postBody");

        authorRepository.save(testAuthor);
        categoryRepository.save(testCategory);
        postRepository.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Optional<Author> retrievedAuthorOptional = authorRepository.findById(testAuthor.getId());
        Optional<Category> retrievedCategoryOptional = categoryRepository.findById(testCategory.getId());
        Author retrievedAuthor = retrievedAuthorOptional.get();
        Category retrievedCategory = retrievedCategoryOptional.get();
        Post retrievedPost = postRepository.findById(testPost.getId()).get();

        assertThat(retrievedCategory.getPosts()).contains(testPost);
    }
//
//    @Test
//    public void tagsShouldBeAbleToHaveMultiplePosts() {
//
//        Category testCategory = new Category("water");
//        Post testPost1 = new Post(testCategory, "postTitle1", "body");
//        Post testPost2 = new Post(testCategory, "postTitle2", "body");
//        Post testPost3 = new Post(testCategory, "postTitle3", "body");
//
//        Tag testTag1 = new Tag("#fresh", testPost1, testPost2);
//        Tag testTag2 = new Tag("#freshcool", testPost2, testPost3);
//
//
//        categoryRepository.save(testCategory);
//
//        postRepository.save(testPost1);
//        postRepository.save(testPost2);
//        postRepository.save(testPost3);
//
//
//        tagRepository.save(testTag1);
//        tagRepository.save(testTag2);
//
//
//        entityManager.flush();
//        entityManager.clear();
//
//        Post retrievedPost = postRepository.findById(testPost1.getId()).get();
//        Tag retrievedTag1 = tagRepository.findById(testTag1.getId()).get();
//        Tag retrievedTag2 = tagRepository.findById(testTag2.getId()).get();
//        assertThat(retrievedPost.getTags()).contains(testTag1, testTag2);
//        assertThat(retrievedTag1.getPosts()).contains(testPost1, testPost2);
//        assertThat(retrievedTag2.getPosts()).contains(testPost2, testPost3);
//    }
}
