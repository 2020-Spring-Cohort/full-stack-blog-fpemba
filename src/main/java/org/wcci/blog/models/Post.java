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

    public Post(String author, String category, String postTitle, String postBody) {

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

    public Author getAuthors() { //not sure

        return author;
    }

    public Category getCategories() {

        return category;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (body != null ? !body.equals(post.body) : post.body != null) return false;
        if (author != null ? !author.equals(post.author) : post.author != null) return false;
        if (publishedDate != null ? !publishedDate.equals(post.publishedDate) : post.publishedDate != null)
            return false;
        return category != null ? category.equals(post.category) : post.category == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishedDate != null ? publishedDate.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
