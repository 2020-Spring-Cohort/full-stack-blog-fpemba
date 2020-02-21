package org.wcci.blog.models;

public class Tags {

    private String name;
    private String post;

    public Tags(String name, String post) {
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
