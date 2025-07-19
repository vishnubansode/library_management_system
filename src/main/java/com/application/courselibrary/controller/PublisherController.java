package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    // ✅ List all publishers
    @GetMapping("/publishers")
    public String findAllPublishers(Model model) {
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }

    // ✅ Show form to add publisher
    @GetMapping("/add-publisher")
    public String showCreateForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "add-publisher";
    }

    // ✅ Save new publisher
    @PostMapping("/save-publisher")
    public String createPublisher(@ModelAttribute("publisher") Publisher publisher,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "add-publisher";
        }
        publisherService.createPublisher(publisher);
        return "redirect:/publishers";
    }

    // ✅ Show form to update publisher
    @GetMapping("/update-publisher/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.findPublisherById(id));
        return "update-publisher";
    }

    // ✅ Save updated publisher
    @PostMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable Long id,
                                  @ModelAttribute("publisher") Publisher publisher,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "update-publisher";
        }
        publisher.setId(id); // Ensure correct ID
        publisherService.updatePublisher(publisher);
        return "redirect:/publishers";
    }

    // ✅ Delete publisher
    @GetMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return "redirect:/publishers";
    }
}
