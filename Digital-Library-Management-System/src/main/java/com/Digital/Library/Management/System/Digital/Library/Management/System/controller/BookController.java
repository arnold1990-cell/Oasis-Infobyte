package com.Digital.Library.Management.System.Digital.Library.Management.System.controller;

import com.Digital.Library.Management.System.Digital.Library.Management.System.exceptions.BookAlreadyExistsException;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Book;
import com.Digital.Library.Management.System.Digital.Library.Management.System.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.save(book);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> book = bookService.findByIsbn(isbn);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = bookService.searchByTitleSimple(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>("Delete operation completed", HttpStatus.OK);
    }

}
