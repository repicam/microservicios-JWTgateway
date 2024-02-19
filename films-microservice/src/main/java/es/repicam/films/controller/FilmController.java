package es.repicam.films.controller;

import es.repicam.films.dto.FilmRequest;
import es.repicam.films.dto.FilmResponse;
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
    public @ResponseBody ResponseEntity<FilmResponse> saveFilm(@RequestBody FilmRequest filmRequest) {
        FilmResponse newFilm = filmService.save(filmRequest);
        if (newFilm == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newFilm);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<FilmResponse>> getAll() {
        List<FilmResponse> filmList = filmService.getAll();
        if (filmList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(filmList);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<FilmResponse> getById(@PathVariable Long id) {
        FilmResponse film = filmService.getById(id);
        if (film == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(film);
    }

    @GetMapping("/user/{userId}")
    public @ResponseBody ResponseEntity<List<FilmResponse>> getByUserId(@PathVariable String userId) {
        List<FilmResponse> userFilmList = filmService.getByUserId(userId);
        if (userFilmList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userFilmList);
    }
}
