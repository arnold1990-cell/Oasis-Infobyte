package com.Digital.Library.Management.System.Digital.Library.Management.System.DTO;

import jakarta.persistence.Column;

public record BookDto(
        Long id,
        String isbn,
String title,
String author,
 String publisher,
 String category,
 int availableCopies
) {
}
