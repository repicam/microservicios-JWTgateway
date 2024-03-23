package es.repicam.users.controller;

import es.repicam.users.dto.UserResponse;
import es.repicam.users.dto.UserRequest;
import es.repicam.users.dto.UserResponseFallback;
import es.repicam.users.model.Book;
import es.repicam.users.model.Film;
import es.repicam.users.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        UserResponse newUser = userService.save(userRequest);
        if (newUser == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userList = userService.getAll();
        if (userList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userList);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<UserResponse> getById(@PathVariable String id) {
        UserResponse user = userService.getById(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    @CircuitBreaker(name = "books", fallbackMethod = "fallBackSaveBooks")
    @PostMapping("/{userId}/book")
    public @ResponseBody ResponseEntity<UserResponse> saveBook(@PathVariable String userId, @RequestBody Book book) {
        UserResponse userResponse = userService.saveBook(userId, book);
        if (userResponse == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userResponse);
    }

    @CircuitBreaker(name = "films", fallbackMethod = "fallBackSaveFilms")
    @PostMapping("/{userId}/film")
    public @ResponseBody ResponseEntity<UserResponse> saveFilm(@PathVariable String userId, @RequestBody Film film) {
        UserResponse userResponse = userService.saveFilm(userId, film);
        if (userResponse == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponseFallback> fallBackGetAll(String id, Throwable th) {
        UserResponseFallback user = userService.getByIdFallback(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<UserResponseFallback> fallBackSaveBooks(String userId, @RequestBody Book book, Throwable th) {
        UserResponseFallback user = userService.getByIdFallback(userId);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<UserResponseFallback> fallBackSaveFilms(String userId, @RequestBody Film film, Throwable th) {
        UserResponseFallback user = userService.getByIdFallback(userId);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }
}
