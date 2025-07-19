package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Book;
import com.application.courselibrary.service.AuthorService;
import com.application.courselibrary.service.BookService;
import com.application.courselibrary.service.CategoryService;
import com.application.courselibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorService authorService;

    // Show all books
    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    // Show single book details
    @GetMapping("/book/{id}")
    public String showBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "list-book";
    }

    // Show create book form
    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        populateDropdowns(model);
        return "add-book";
    }

    // Save new book
    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            populateDropdowns(model);
            return "add-book";
        }
        bookService.createBook(book);
        return "redirect:/books";
    }

    // Show update book form
    @GetMapping("/update-book/{id}")
    public String showUpdateBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        populateDropdowns(model);
        return "update-book";
    }

    // Save updated book
    @PostMapping("/save-update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book updatedBook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            populateDropdowns(model);
            return "update-book";
        }
        bookService.updateBook(id, updatedBook);  // ✅ Fix logic: use ID
        return "redirect:/books";
    }

    // Delete book
    @GetMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    // Utility: Load dropdowns
    private void populateDropdowns(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
    }
}
