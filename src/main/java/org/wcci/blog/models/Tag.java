package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private Collection<Post> posts;

    public Tag() {
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public Tag(String name, Post... posts) {
        this.name = name;
        this.posts = Arrays.asList(posts);
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
