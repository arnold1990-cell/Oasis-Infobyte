package com.Digital.Library.Management.System.Digital.Library.Management.System.repository;

import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);
}
