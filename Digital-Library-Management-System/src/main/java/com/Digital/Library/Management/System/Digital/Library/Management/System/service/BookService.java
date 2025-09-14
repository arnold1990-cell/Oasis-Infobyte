package com.Digital.Library.Management.System.Digital.Library.Management.System.service;

import com.Digital.Library.Management.System.Digital.Library.Management.System.exceptions.BookAlreadyExistsException;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Book;
import com.Digital.Library.Management.System.Digital.Library.Management.System.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Book> searchByTitleSimple(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }

        String cleanedQuery = query.trim().toLowerCase();

        List<Book> allBooks = bookRepository.findAll();

        List<Book> matchedBooks = new ArrayList<Book>();
        for (Book book : allBooks) {
            if (book.getTitle() != null && book.getTitle().toLowerCase().contains(cleanedQuery)) {
                matchedBooks.add(book);
            }
        }

        if (matchedBooks.isEmpty()) {
            System.out.println("No books found for query: " + cleanedQuery);
        }

        return matchedBooks;


}



    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {System.out.println("No books found in the library.");

        }
        return books;
    }

    public void deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            System.out.println("Cannot delete: Book with ID " + id + " does not exist.");
            return;
        }

        bookRepository.deleteById(id);

        System.out.println("Book with ID " + id + " has been deleted.");
    }

}

