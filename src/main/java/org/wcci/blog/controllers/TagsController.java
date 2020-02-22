package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Tags;

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

    @GetMapping("")
    public String addTagForm(Model model) {
        model.addAttribute("title", "Add a tag");
        return "tags";
    }

    @PostMapping("submit")
    public String processAddTagForm(@RequestParam("tagName") String tagName, @RequestParam("tagPost") String tagPost) {
        new Tags(tagName, tagPost);
        return "redirect:";
    }
}
