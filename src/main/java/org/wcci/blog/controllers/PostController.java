package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Posts;

@Controller
@RequestMapping("blog")
public class PostController {

    @GetMapping
    public String displayBlogPosts(Model model) {
        model.addAttribute("reviews", "add a post");
        return "post";
    }

    @GetMapping("add")
    public String addPostForm(Model model) {
        model.addAttribute("title", "Add a Post");
        return "post";
    }

    @PostMapping("add")
    public String processAddReviewForm(@RequestParam("postTitle") String postTitle, @RequestParam("postAuthor") String postAuthor, @RequestParam("publishedDate") int publishedDate, @RequestParam("postBody") String postBody, @RequestParam("postCategory") String postCategory, @RequestParam("postTag") String postTag) {
        new Posts(postTitle, postAuthor, postBody, publishedDate, postCategory, postTag);
        return "redirect:";
    }
}

