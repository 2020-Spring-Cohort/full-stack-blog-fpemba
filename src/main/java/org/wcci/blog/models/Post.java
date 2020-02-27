package org.wcci.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Post {


    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;
    @ManyToOne
    private Author authors;
    private String publishedDate;
    @ManyToOne
    private Category categories;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tags;


    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
        this.publishedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }

    public Post(Author authors, String title, String body) {
        this.authors = authors;
        this.title = title;
        this.body = body;
    }

    public Post(Category categories, String title, String body) {
        this.categories = categories;
        this.title = title;
        this.body = body;
    }

    public Post(Category categories, String title, String body, Tag... tags) {
        this.categories = categories;
        this.title = title;
        this.body = body;
        this.tags = Arrays.asList(tags);
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Long getId() {
        return id;
    }

    public Collection<Tag> getTags() {

        return tags;
    }

    public Collection<Author> getAuthors() {

        return (Collection<Author>) authors;
    }
}
