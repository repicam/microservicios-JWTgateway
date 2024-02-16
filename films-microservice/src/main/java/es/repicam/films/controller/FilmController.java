package es.repicam.films.controller;

import es.repicam.films.entity.Film;
import es.repicam.films.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping
    public @ResponseBody ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film newFilm = filmService.save(film);
        if (newFilm == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(newFilm);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<Film>> getAll() {
        List<Film> filmList = filmService.getAll();
        if (filmList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(filmList);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Film> getById(@PathVariable Long id) {
        Film film = filmService.getById(id);
        if (film == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(film);
    }

    @GetMapping("/user/{userId}")
    public @ResponseBody ResponseEntity<List<Film>> getByUserId(@PathVariable String userId) {
        List<Film> userFilmList = filmService.getByUserId(userId);
        if (userFilmList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userFilmList);
    }
}
