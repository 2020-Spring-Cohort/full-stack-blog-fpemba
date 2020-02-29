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

        Author user = new Author("Bob");
        authorStorage.store(user);

        Category water = new Category("Water");
        categoryStorage.store(water);


        Post waterPost1 = new Post(user, water, "Spring Water", "Bottled water is drinking water packaged in plastic or glass water bottles. ");
        Post waterPost2 = new Post(user, water, "Fiji Water", "From a sustainable ancient artesian aquifer in Fiji.");
        Post waterPost3 = new Post(user, water, "Ice Mountain", "Ice Mountain is a brand of bottled water from the Nestlé company.");

        postStorage.store(waterPost1);
        postStorage.store(waterPost2);
        postStorage.store(waterPost3);


        Tag fresh = new Tag("fresh", waterPost1, waterPost2, waterPost3);
        tagStorage.add(fresh);


    }
}
