package com.application.courselibrary.service;

import com.application.courselibrary.entity.Book;
import com.application.courselibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    // ✅ Update book by keeping ID intact
    public void updateBook(Long id, Book updatedBook) {
        Book existingBook = findBookById(id);
        updatedBook.setId(existingBook.getId()); // ensure ID remains same
        bookRepository.save(updatedBook);
    }

    public void deleteBook(Long id) {
        Book book = findBookById(id);
        bookRepository.deleteById(book.getId());
    }
}
