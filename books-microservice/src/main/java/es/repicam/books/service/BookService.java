package es.repicam.books.service;

import es.repicam.books.dto.BookRequest;
import es.repicam.books.dto.BookResponse;
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

    public List<BookResponse> getAll() {
        return BookResponse.buildByEntityList(bookRepository.findAll());
    }

    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null)
            return new BookResponse();

        return BookResponse.builder().
                id(id).
                title(book.getTitle()).
                author(book.getAuthor()).
                build();
    }

    public BookResponse save(BookRequest bookRequest) {

        try {
             Book newBook = bookRepository.save(Book.buildByRequest(bookRequest));
            return BookResponse.builder().
                    id(newBook.getId()).
                    title(newBook.getTitle()).
                    author(newBook.getAuthor()).
                    build();
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }

    public List<BookResponse> getByUserId(String userId){
        return BookResponse.buildByEntityList(bookRepository.findByUserId(userId));
    }
}
