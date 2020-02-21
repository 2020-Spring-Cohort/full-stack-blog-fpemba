package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("author")
public class AuthorController {

    public AuthorController() {
    }

    @RequestMapping
    public String displayAuthors(Model model) {
        model.addAttribute("authors", "author");
        return "author";
    }
}
