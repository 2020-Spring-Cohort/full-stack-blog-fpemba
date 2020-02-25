package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storages.TagStorage;

@Controller
@RequestMapping("tag")
public class TagController {

    private TagStorage tagStorage;

    public TagController(TagStorage tagStorage) {
        this.tagStorage = tagStorage;
    }

//    @RequestMapping
//    public String displayTags(Model model) {
//        model.addAttribute("tags", tagStorage.getAll());
//        return "tag";
//    }

    @GetMapping("/single-tag/{tagName}")
    public String displaySingleTag(@PathVariable String tagName, Model model) {
        Tag retrievedTag = tagStorage.findTagByName(tagName);
        model.addAttribute("tag", retrievedTag);
        return "tag";
    }

    @PostMapping("add")
    public String AddTagForm(@RequestParam String tagName) {
        tagStorage.store(new Tag(tagName));
        return "redirect:/tag/all-tags";
    }

    @GetMapping("all-tags")
    public String viewAllTags(Model model) {
        model.addAttribute("tags", tagStorage.getAll());
        return "tags";

    }

}