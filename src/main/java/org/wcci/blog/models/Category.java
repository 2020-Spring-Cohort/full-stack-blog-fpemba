package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private String name;
    @OneToMany(mappedBy = "category")
    private Collection<Post> posts;


    public Category() {

    }

    public Category(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

}
