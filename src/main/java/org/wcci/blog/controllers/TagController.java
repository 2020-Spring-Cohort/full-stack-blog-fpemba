package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storages.TagStorage;

@Controller
@RequestMapping("tags")
public class TagController {

    private TagStorage tagStorage;

    public TagController(TagStorage tagStorage) {
        this.tagStorage = tagStorage;
    }

    @RequestMapping
    public String displayTags(Model model) {
        model.addAttribute("tags", tagStorage.getAll());
        return "tags";
    }

    @GetMapping("add")
    public String addTagForm(Model model) {
        model.addAttribute("Tags", "Add a tag");
        return "tags";
    }

    @PostMapping("submit")
    public String processAddTagForm(@RequestParam("tagName") String tagName) {
        tagStorage.store(new Tag(tagName));
        return "redirect:";
    }
}
