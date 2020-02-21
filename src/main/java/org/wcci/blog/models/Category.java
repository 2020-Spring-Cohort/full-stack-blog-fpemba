package org.wcci.blog.models;

public class Category {


    private String name;
    private String post;

    public Category(String name, String post) {
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
