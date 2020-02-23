package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.PostStorage;

@Controller
@RequestMapping("blog")
public class PostController {

    private PostStorage postStorage;

    public PostController(PostStorage storage) {
        this.postStorage = storage;
    }

    @GetMapping
    public String displayBlogPosts(Model model) {
        model.addAttribute("posts", postStorage.getAll());
        return "post";
    }

    @GetMapping("add")
    public String addPostForm(Model model) {
        model.addAttribute("Post", "Add a Post");
        return "post";
    }

    @PostMapping("add")
    public String processAddReviewForm(@RequestParam("postTitle") String postTitle, @RequestParam("publishedDate") String publishedDate, @RequestParam("postBody") String postBody) {
        postStorage.store(new Post(postTitle, publishedDate, postBody));
        return "redirect:";
    }

}

