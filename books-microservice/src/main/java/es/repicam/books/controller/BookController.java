package es.repicam.books.controller;

import es.repicam.books.dto.BookResponse;
import es.repicam.books.dto.BookRequest;
import es.repicam.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public @ResponseBody ResponseEntity<BookResponse> saveBook(@RequestBody BookRequest bookRequest) {
        BookResponse newBook = bookService.save(bookRequest);
        if (newBook == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<BookResponse>> getAll() {
        List<BookResponse> bookList = bookService.getAll();
        if (bookList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        BookResponse book = bookService.getById(id);
        if (book == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(book);
    }

    @GetMapping("/user/{userId}")
    public @ResponseBody ResponseEntity<List<BookResponse>> getByUserId(@PathVariable String userId) {
        List<BookResponse> userBookList = bookService.getByUserId(userId);
        if (userBookList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userBookList);
    }
}
