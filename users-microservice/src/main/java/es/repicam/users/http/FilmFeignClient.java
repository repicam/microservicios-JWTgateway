package es.repicam.users.http;

import es.repicam.users.dto.FilmFeign;
import es.repicam.users.model.Film;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "films-microservice", url = "localhost:8003/api/film")
public interface FilmFeignClient {

    @PostMapping
    public Film saveFilm(@RequestBody FilmFeign film);

    @GetMapping("/user/{userId}")
    public List<Film> getFilmsByUserId(@PathVariable String userId);
}
