package es.repicam.books.controller;

import es.repicam.books.entity.Book;
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
    public @ResponseBody ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookService.save(book);
        if (newBook == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<Book>> getAll() {
        List<Book> bookList = bookService.getAll();
        if (bookList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Book> getById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        if (book == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(book);
    }

    @GetMapping("/user/{userId}")
    public @ResponseBody ResponseEntity<List<Book>> getByUserId(@PathVariable String userId) {
        List<Book> userBookList = bookService.getByUserId(userId);
        if (userBookList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userBookList);
    }
}
