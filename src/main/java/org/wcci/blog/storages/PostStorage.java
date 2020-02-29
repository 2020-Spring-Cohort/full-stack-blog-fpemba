package org.wcci.blog.storages;


import org.wcci.blog.models.Post;

import java.util.Collection;

public interface PostStorage {


    Collection<Post> getAll();

    void store(Post post);


    Post findPostById(long id);

    Post findPostByTitle(String title);

}

