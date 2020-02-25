package org.wcci.blog.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "authors")
    private Collection<Post> posts;


    public Author(String name) {
        this.name = name;
    }

    public Author() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
