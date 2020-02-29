package org.wcci.blog.storages;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.Repositories.AuthorRepository;
import org.wcci.blog.storages.Repositories.CategoryRepository;
import org.wcci.blog.storages.Repositories.PostRepository;

import java.util.Collection;

@Service
public class PostStorageJpaImpl implements PostStorage {

    PostRepository repository;
    AuthorRepository authorRepository;
    CategoryRepository categoryRepository;

    public PostStorageJpaImpl(AuthorRepository authorRepository, CategoryRepository categoryRepository, PostRepository repository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.repository = repository;

    }

    @Override
    public Collection<Post> getAll() {
        return (Collection<Post>) repository.findAll();
    }

    @Override
    public void store(Post post) {

        authorRepository.save(post.getAuthor());
        categoryRepository.save(post.getCategory());
        repository.save(post);


    }

    @Override
    public Post findPostById(long id) {

        return repository.findById(id).get();

    }

    @Override
    public Post findPostByTitle(String title) {

        return repository.findByTitle(title);
    }

}
