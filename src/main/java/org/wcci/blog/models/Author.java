package org.wcci.blog.models;

public class Author {


    private String name;
    private String post;

    public Author(String name, String post) {
        this.name = name;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }
}
