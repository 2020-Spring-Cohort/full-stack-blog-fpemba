package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Author;
import org.wcci.blog.storages.AuthorStorage;

@Controller
@RequestMapping("author")
public class AuthorController {

    private AuthorStorage authorStorage;

    public AuthorController(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    @RequestMapping
    public String displayAuthors(Model model) {
        model.addAttribute("authors", authorStorage.getAll());
        return "author";
    }

    @GetMapping("add")
    public String addAuthorForm(Model model) {
        model.addAttribute("Author", "Add an author");
        return "author";
    }

    @PostMapping("submit")
    public String processAddAuthorForm(@RequestParam("authorName") String authorName) {
        authorStorage.store(new Author(authorName));
        return "redirect:";
    }
}
