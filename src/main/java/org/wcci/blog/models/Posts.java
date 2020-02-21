package org.wcci.blog.models;

public class Posts {

    private String title;
    private String body;
    private String author;
    private int publishedDate;
    private String category;
    private String tag;

    public Posts(String title, String body, String author, int publishedDate, String category, String tag) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.publishedDate = publishedDate;
        this.category = category;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    public String getCategory() {
        return category;
    }

    public String getTag() {
        return tag;
    }
}
