package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // List all authors
    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    // Show create author form
    @GetMapping("/add-author")
    public String showCreateAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add-author";
    }

    // Save new author
    @PostMapping("/save-author")
    public String saveAuthor(@ModelAttribute("author") Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "add-author";
        }
        authorService.createAuthor(author);
        return "redirect:/authors";
    }

    // Show update form
    @GetMapping("/update-author/{id}")
    public String showUpdateAuthorForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findAuthorById(id));
        return "update-author";
    }

    // Save updated author
    @PostMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id, @ModelAttribute("author") Author updatedAuthor, BindingResult result) {
        if (result.hasErrors()) {
            return "update-author";
        }
        authorService.updateAuthor(id, updatedAuthor); // ✅ Fixed logic: pass id for correct update
        return "redirect:/authors";
    }

    // Delete author
    @GetMapping("/remove-author/{id}")
    public String removeAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}
