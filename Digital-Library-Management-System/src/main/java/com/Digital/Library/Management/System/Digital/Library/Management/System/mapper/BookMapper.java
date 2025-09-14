package com.Digital.Library.Management.System.Digital.Library.Management.System.mapper;

import com.Digital.Library.Management.System.Digital.Library.Management.System.dto.BookDto;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Book;

public class BookMapper {
    public BookDto bookDto(Book book){
        if (book == null) {return  null;

        }
        return  new BookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getCategory(),
                book.getAvailableCopies()
        );
    }
    public  Book toEntity(BookDto bookDto){
        if (bookDto == null) { return  null;

        }
        Book book=new Book();
       book.setId(bookDto.id());
       book.setIsbn(bookDto.isbn());
       book.setTitle(bookDto.title());
       book.setAuthor(bookDto.author());
       book.setPublisher(bookDto.publisher());
       book.setCategory(bookDto.category());
       book.setAvailableCopies(bookDto.availableCopies());

        return book;
    }
}
