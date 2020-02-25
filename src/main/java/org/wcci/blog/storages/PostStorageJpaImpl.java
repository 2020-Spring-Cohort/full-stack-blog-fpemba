package org.wcci.blog.storages;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.Repositories.PostRepository;

import java.util.Collection;

@Service
public class PostStorageJpaImpl implements PostStorage {

    PostRepository repository;

    public PostStorageJpaImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Post> getAll() {
        return (Collection<Post>) repository.findAll();
    }

    @Override
    public void store(Post post) {

        repository.save(post);

    }

    @Override
    public Post findPostById(long id) {
        return repository.findById(id).get();
    }


}
