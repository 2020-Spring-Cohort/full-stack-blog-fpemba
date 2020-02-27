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

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
