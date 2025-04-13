package com.estsoft.demo.book.dto;

import com.estsoft.demo.book.Book;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddBookRequest {
    private String id;
    private String name;
    private String author;

    public Book toEntity() {
        return new Book(id, name, author);
    }
}
