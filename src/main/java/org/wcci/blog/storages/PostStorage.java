package org.wcci.blog.storages;

import org.wcci.blog.models.Post;

import java.util.Collection;

public interface PostStorage {


    Collection<Post> getAll();

    void store(Post post);

    Object findPostById(Long id);

}

