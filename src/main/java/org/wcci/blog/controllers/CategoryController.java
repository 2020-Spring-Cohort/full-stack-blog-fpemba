package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Category;
import org.wcci.blog.storages.CategoryStorage;

@Controller
@RequestMapping("category")
public class CategoryController {

    private CategoryStorage categoryStorage;

    public CategoryController(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }

    @RequestMapping
    public String displayCategories(Model model) {
        model.addAttribute("categories", categoryStorage.getAll());
        return "category";
    }

    @GetMapping("add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", "Add a category");
        return "category";
    }

    @PostMapping("submit")
    public String processAddCategoryForm(@RequestParam("categoryName") String categoryName) {
        categoryStorage.store(new Category(categoryName));
        return "redirect:";
    }


}

