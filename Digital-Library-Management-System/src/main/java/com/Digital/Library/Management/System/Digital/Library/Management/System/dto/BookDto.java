package com.Digital.Library.Management.System.Digital.Library.Management.System.dto;

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
