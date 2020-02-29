package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Author;
import org.wcci.blog.storages.AuthorStorage;
import org.wcci.blog.storages.PostStorage;

@Controller
@RequestMapping("author")
public class AuthorController {

    private AuthorStorage authorStorage;
    private PostStorage postStorage;

    public AuthorController(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    @GetMapping("/single-author/{authorName}")
    public String displaySingleAuthor(@PathVariable String authorName, Model model) {
        Author retrievedAuthor = authorStorage.findAuthorByName(authorName);
        model.addAttribute("author", retrievedAuthor);
        return "author";
    }

    @GetMapping("/{authorId}")
    public String displayAuthorFromPostPage(@PathVariable long authorId, Model model) {
        Author retrievedAuthor = authorStorage.findAuthorById(authorId);
        model.addAttribute("author", retrievedAuthor);
        return "author";
    }

    @PostMapping("add")
    public String AddAuthorForm(@RequestParam String authorName) {
        authorStorage.store(new Author(authorName));
        return "redirect:/author/all-authors";
    }

    @GetMapping("all-authors")
    public String viewAllAuthors(Model model) {
        model.addAttribute("authors", authorStorage.getAll());
        return "authors";

    }


}
