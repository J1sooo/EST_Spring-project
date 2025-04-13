package com.estsoft.demo.book.dto;

import com.estsoft.demo.book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class BookViewResponse {
    private String id;
    private String name;
    private String author;

    public BookViewResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
    }
}
