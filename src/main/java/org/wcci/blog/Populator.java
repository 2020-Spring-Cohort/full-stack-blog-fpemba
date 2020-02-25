package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storages.AuthorStorage;
import org.wcci.blog.storages.CategoryStorage;
import org.wcci.blog.storages.PostStorage;
import org.wcci.blog.storages.TagStorage;


@Component
public class Populator implements CommandLineRunner {

    private AuthorStorage authorStorage;
    private CategoryStorage categoryStorage;
    private PostStorage postStorage;
    private TagStorage tagStorage;

    public Populator(AuthorStorage authorStorage, CategoryStorage categoryStorage, PostStorage postStorage, TagStorage tagStorage) {
        this.authorStorage = authorStorage;
        this.categoryStorage = categoryStorage;
        this.postStorage = postStorage;
        this.tagStorage = tagStorage;
    }

    @Override
    public void run(String... args) throws Exception {

        Author user = new Author("user");
        authorStorage.store(user);

        Category water = new Category("water");
        categoryStorage.store(water);

        Post waterPost1 = new Post("test", "test");
        Post waterPost2 = new Post("test1", "test");
        Post waterPost3 = new Post("test2", "test");
        postStorage.store(waterPost1);
        postStorage.store(waterPost2);
        postStorage.store(waterPost3);

        Tag fresh = new Tag("#fresh", waterPost1, waterPost2, waterPost3);
        tagStorage.add(fresh);

    }
}
