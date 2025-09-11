package com.Digital.Library.Management.System.Digital.Library.Management.System.Service;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Exceptions.BookAlreadyExistsException;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Book;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Repository.BookRepository;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    public <S extends Book> S save(S entity) throws BookAlreadyExistsException {
        if (entity == null) {throw new RuntimeException("Invalid book");

        }
        if (bookRepository.findByIsbn(entity.getIsbn()).isPresent()) {throw new BookAlreadyExistsException("The books is in the database");

        }

        return bookRepository.save(entity);
    }

    public Optional<Book> findById(Long aLong) {
        if (aLong == null) { throw  new RuntimeException("The book is not available");

        }
        if (!bookRepository.existsById(aLong)) {System.out.println("No books found in the library.");

        }

        return bookRepository.findById(aLong);
    }

    public  Optional<Book> findByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);

        if (book.isPresent()) {
            System.out.println("A book with this ISBN already exists");

        } else {
            System.out.println("No book found, safe to save");
        }

        return bookRepository.findByIsbn(isbn);
    }

    public Page<Book> findByTitleContainingIgnoreCase(String q, Pageable p) {
        if (q == null || q.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }

        String cleanedQuery = q.trim();
        return bookRepository.findByTitleContainingIgnoreCase(cleanedQuery, p);
    }



    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {System.out.println("No books found in the library.");

        }
        return books;
    }

    public void deleteById(Long id) {
        // 1. Check if the book exists
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            System.out.println("Cannot delete: Book with ID " + id + " does not exist.");
            return;
        }

        bookRepository.deleteById(id);

        System.out.println("Book with ID " + id + " has been deleted.");
    }

}

