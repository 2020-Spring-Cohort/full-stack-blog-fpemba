package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Post;
import org.wcci.blog.storages.AuthorStorage;
import org.wcci.blog.storages.CategoryStorage;
import org.wcci.blog.storages.PostStorage;
import org.wcci.blog.storages.TagStorage;

@Controller
@RequestMapping("post")
public class PostController {

    private PostStorage postStorage;
    private AuthorStorage authorStorage;
    private CategoryStorage categoryStorage;
    private TagStorage tagStorage;


    public PostController(PostStorage postStorage, AuthorStorage authorStorage, CategoryStorage categoryStorage, TagStorage tagStorage) {
        this.postStorage = postStorage;
        this.authorStorage = authorStorage;
        this.categoryStorage = categoryStorage;
        this.tagStorage = tagStorage;
    }


    @GetMapping("/single-post/{id}}")
    public String displaySinglePost(@PathVariable Long id, Model model) {
        Post retrievedPost = (Post) postStorage.findById(id);
        model.addAttribute("post", retrievedPost);
        model.addAttribute("tags", tagStorage.getAll());
        return "post";
//
    }

    @PostMapping("add")
    public String AddPostForm(@RequestParam("postTitle") String postTitle, @RequestParam("postBody") String postBody) {
        postStorage.store(new Post(postTitle, postBody));
        return "redirect:/post/all-posts";
    }

    @GetMapping("all-posts")
    public String viewAllPosts(Model model) {
        model.addAttribute("posts", postStorage.getAll());
        model.addAttribute("author", authorStorage.getAll());
        model.addAttribute("category", categoryStorage.getAll());
        model.addAttribute("tags", tagStorage.getAll());
        return "posts";

    }


}

