package org.wcci.blog.storages.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Post;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {


    Optional<Post> findPostById(Long id);


    Post findByTitle(String title);
}
