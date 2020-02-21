package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class CategoryController {


    public CategoryController() {

    }

    @RequestMapping
    public String displayCategories(Model model) {
        model.addAttribute("categories", "category");
        return "category";
    }


}

