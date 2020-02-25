package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Author;
import org.wcci.blog.storages.AuthorStorage;

@Controller
@RequestMapping("author")
public class AuthorController {

    private AuthorStorage authorStorage;

    public AuthorController(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

//    @RequestMapping()
//    public String displayAuthors(Model model) {
//        model.addAttribute("authors", authorStorage.getAll());
//        return "author";
//    }

    @GetMapping("/single-author/{authorName}")
    public String displaySingleAuthor(@PathVariable String authorName, Model model) {
        Author retrievedAuthor = authorStorage.findAuthorByName(authorName);
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
