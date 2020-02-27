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
    private Author author;
    private String publishedDate;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tags;


    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
        this.publishedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }

    public Post(Author author, Category category, String title, String body, Tag... tags) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.body = body;
        this.tags = Arrays.asList(tags);
    }

    public Post(Author author, Category category, String title, String body) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.body = body;
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

    public Author getAuthor() {

        return author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public Category getCategory() {
        return category;
    }
}
