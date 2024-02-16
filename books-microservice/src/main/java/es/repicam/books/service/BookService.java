package es.repicam.books.service;

import es.repicam.books.entity.Book;
import es.repicam.books.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {

        try {
            return bookRepository.save(book);
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }

    public List<Book> getByUserId(String userId){
        return bookRepository.findByUserId(userId);
    }
}
