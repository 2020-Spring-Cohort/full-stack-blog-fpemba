package org.wcci.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Long getId() {
        return id;
    }
}
