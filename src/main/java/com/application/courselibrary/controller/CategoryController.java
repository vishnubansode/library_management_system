package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Category;
import com.application.courselibrary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // ✅ List all categories
    @GetMapping("/categories")
    public String findAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }

    // ✅ Show form to add new category
    @GetMapping("/add-category")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category()); // Ensure form has backing object
        return "add-category";
    }

    // ✅ Save new category
    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "add-category";
        }
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    // ✅ Show form to update category
    @GetMapping("/update-category/{id}")
    public String showUpdateCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "update-category";
    }

    // ✅ Save updated category
    @PostMapping("/update-category/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @ModelAttribute("category") Category category,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "update-category";
        }
        category.setId(id); // Ensure correct ID
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    // ✅ Delete category
    @GetMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
