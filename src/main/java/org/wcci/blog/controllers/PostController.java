package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.PostStorage;

@Controller
@RequestMapping("post")
public class PostController {

    private PostStorage postStorage;

    public PostController(PostStorage storage) {
        this.postStorage = storage;
    }

    @RequestMapping
    public String displayBlogPosts(Model model) {
        model.addAttribute("posts", postStorage.getAll());
        return "post";
    }

//    @GetMapping("add")
//    public String addPostForm(Model model) {
//        model.addAttribute("Post", "Add a Post");
//        return "post";
//    }


    @GetMapping("/single-post/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        Post retrievedPost = postStorage.findPostById(id);
        model.addAttribute("post", retrievedPost);
        return "post";
    }

    @PostMapping("add")
    public String AddPostForm(@RequestParam("postTitle") String postTitle, @RequestParam("postBody") String postBody) {
        postStorage.store(new Post(postTitle, postBody));
        return "redirect:/post/all-posts";
    }

    @GetMapping("all-posts")
    public String viewAllAuthors(Model model) {
        model.addAttribute("posts", postStorage.getAll());
        return "posts";

    }


}

