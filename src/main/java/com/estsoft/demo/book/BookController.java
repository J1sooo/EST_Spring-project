package com.estsoft.demo.book;

import com.estsoft.demo.book.dto.AddBookRequest;
import com.estsoft.demo.book.dto.BookViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public String books(Model model) {
        List<BookViewResponse> bookList = bookService.getBookList()
                .stream().map(BookViewResponse::new).toList();
        model.addAttribute("bookList", bookList);
        return "bookManage";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/{id}")
    public String book(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", new BookViewResponse(book));
        return "bookDetail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public String addBook(@ModelAttribute AddBookRequest request) {
        bookService.saveBook(request);
        return "redirect:/books";
    }
}
