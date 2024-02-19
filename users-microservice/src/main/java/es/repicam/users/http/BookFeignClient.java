package es.repicam.users.http;

import es.repicam.users.dto.BookFeign;
import es.repicam.users.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "books-microservice", url = "localhost:8002/api/book")
public interface BookFeignClient {

    @PostMapping
    public Book saveBook(@RequestBody BookFeign book);

    @GetMapping("/user/{userId}")
    public List<Book> getBooksByUserId(@PathVariable String userId);
}
