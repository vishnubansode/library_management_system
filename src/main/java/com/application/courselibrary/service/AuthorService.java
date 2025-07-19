package com.application.courselibrary.service;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Get all authors
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    // Get author by ID
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    // Create new author
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Update existing author
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existingAuthor = findAuthorById(id);
        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setDescription(updatedAuthor.getDescription());
        return authorRepository.save(existingAuthor);
    }

    // Delete author by ID
    public void deleteAuthor(Long id) {
        Author author = findAuthorById(id); // throws if not found
        authorRepository.delete(author);
    }
}
