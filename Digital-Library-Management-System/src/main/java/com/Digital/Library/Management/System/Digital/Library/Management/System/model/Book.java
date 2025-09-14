package com.Digital.Library.Management.System.Digital.Library.Management.System.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column( nullable = false)
    private String title;


    private String author;


    private String publisher;

    @Column( length = 100)
    private String category;

    @Column( nullable = false)
    private int totalCopies;

    @Column( nullable = false)
    private int availableCopies;
}
