package com.Digital.Library.Management.System.Digital.Library.Management.System.Repository;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);
    Page<Book> findByTitleContainingIgnoreCase(String q, Pageable p);
}
