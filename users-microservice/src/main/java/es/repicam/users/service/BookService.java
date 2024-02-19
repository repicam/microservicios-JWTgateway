package es.repicam.users.service;

import es.repicam.users.dto.BookFeign;
import es.repicam.users.http.BookFeignClient;
import es.repicam.users.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookFeignClient bookFeignClient;

    public Book save(BookFeign book) {

        try {
            return bookFeignClient.saveBook(book);
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }

    public List<Book> getByUserId(String userId) {
        List<Book> bookList = bookFeignClient.getBooksByUserId(userId);
        if (bookList == null)
            return new ArrayList<>();

        return bookList;
    }
}
