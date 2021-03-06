package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Category;
import org.wcci.blog.storages.CategoryStorage;

@Controller
@RequestMapping("category")
public class CategoryController {

    private CategoryStorage categoryStorage;

    public CategoryController(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }


    @GetMapping("/{categoryId}")
    public String displayCategoryFromPostPage(@PathVariable long categoryId, Model model) {
        Category retrievedCategory = categoryStorage.findCategoryById(categoryId);
        model.addAttribute("category", retrievedCategory);
        return "category";
    }

    @PostMapping("add")
    public String AddCategoryForm(@RequestParam String categoryName) {
        categoryStorage.store(new Category(categoryName));
        return "redirect:/category/all-categories";
    }

    @GetMapping("all-categories")
    public String viewAllCategories(Model model) {
        model.addAttribute("categories", categoryStorage.getAll());
        return "categories";
    }


}

