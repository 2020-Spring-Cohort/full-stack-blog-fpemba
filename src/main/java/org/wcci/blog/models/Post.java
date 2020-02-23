package org.wcci.blog.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Post {


    @Id
    @GeneratedValue
    private String title;
    private String body;
    @ManyToOne
    private Author authors;
    private String publishedDate;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tags;
    private Long id;

    public Post() {
    }

    public Post(String title, String body, String publishedDate) {
        this.title = title;
        this.body = body;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getPublishedDate() {
        return publishedDate;
    }


}
