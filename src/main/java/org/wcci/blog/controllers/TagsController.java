package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tags")
public class TagsController {

    public TagsController() {

    }

    @RequestMapping
    public String displayTags(Model model) {
        model.addAttribute("tags", "tag");
        return "tags";
    }
}
