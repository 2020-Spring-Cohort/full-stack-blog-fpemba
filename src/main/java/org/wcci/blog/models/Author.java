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

    public Collection<Post> getPosts() {

        return posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id != null) return false;
        return name != null ? name.equals(author.name) : author.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
