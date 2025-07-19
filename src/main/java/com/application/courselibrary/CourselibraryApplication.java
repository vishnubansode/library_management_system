package com.application.courselibrary;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.entity.Book;
import com.application.courselibrary.entity.Category;
import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourselibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourselibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialCreate(BookService bookService) {
        return (args) -> {
            // Book 1
            Book book1 = new Book("Book-101", "The Startup Way", "A guide to building innovative companies");
            Author author1 = new Author("Eric Ries", "Entrepreneur and author of The Lean Startup");
            Category category1 = new Category("Business");
            Publisher publisher1 = new Publisher("Crown Publishing Group");
            book1.addAuthor(author1);
            book1.addCategory(category1);
            book1.addPublisher(publisher1);
            bookService.createBook(book1);

            // Book 2
            Book book2 = new Book("Book-102", "A Brief History of Time", "Cosmology from the Big Bang to Black Holes");
            Author author2 = new Author("Stephen Hawking", "Renowned physicist and cosmologist");
            Category category2 = new Category("Science");
            Publisher publisher2 = new Publisher("Bantam Books");
            book2.addAuthor(author2);
            book2.addCategory(category2);
            book2.addPublisher(publisher2);
            bookService.createBook(book2);

            // Book 3
            Book book3 = new Book("Book-103", "To Kill a Mockingbird", "A novel of racial injustice and childhood");
            Author author3 = new Author("Harper Lee", "Pulitzer Prize-winning author");
            Category category3 = new Category("Fiction");
            Publisher publisher3 = new Publisher("J.B. Lippincott & Co.");
            book3.addAuthor(author3);
            book3.addCategory(category3);
            book3.addPublisher(publisher3);
            bookService.createBook(book3);
        };
    }
}
