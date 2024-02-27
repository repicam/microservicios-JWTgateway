package es.repicam.users.service;

import es.repicam.users.dto.UserRequest;
import es.repicam.users.dto.UserResponse;
import es.repicam.users.entity.User;
import es.repicam.users.model.Book;
import es.repicam.users.model.Film;
import es.repicam.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private FilmService filmService;

    public List<UserResponse> getAll() {
        return UserResponse.buildByEntityList(userRepository.findAll());
    }

    public UserResponse getById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return new UserResponse();

        return refillUserByEntity(user);
    }

    public UserResponse save(UserRequest userRequest) {

        try {
            User user = userRepository.save(User.buildByRequest(userRequest));
            return UserResponse.builder().
                    username(userRequest.getUsername()).
                    id(user.getId()).
                    build();
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }

    public UserResponse saveBook(String userId, Book book) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return null;

        bookService.save(book, userId);

        return refillUserByEntity(user);
    }

    public UserResponse saveFilm(String userId, Film film) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return null;

        filmService.save(film, userId);

        return refillUserByEntity(user);
    }

    private UserResponse refillUserByEntity(User user){

        try {
            List<Book> bookList = bookService.getByUserId(user.getId());
            List<Film> filmList = filmService.getByUserId(user.getId());

            return UserResponse.builder().
                    username(user.getUsername()).
                    id(user.getId()).
                    books(bookList).
                    films(filmList).
                    build();
        } catch (Exception exc) {
            return UserResponse.builder().
                    username(user.getUsername()).
                    id(user.getId()).
                    build();
        }
    }
}
