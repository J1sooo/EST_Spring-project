package com.estsoft.demo.book;

import com.estsoft.demo.book.dto.AddBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    // 책 목록 전체 조회
    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    // 책 정보 저장
    public Book saveBook(AddBookRequest request) {
        Book book = request.toEntity();
        return bookRepository.save(book);
    }

    // 책 정보 단건 조회
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("no exists id: " + id));
    }
}
