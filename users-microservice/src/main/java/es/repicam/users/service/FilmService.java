package es.repicam.users.service;

import es.repicam.users.dto.FilmFeign;
import es.repicam.users.http.FilmFeignClient;
import es.repicam.users.model.Book;
import es.repicam.users.model.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    private Logger logger = LoggerFactory.getLogger(FilmService.class);

    @Autowired
    private FilmFeignClient filmFeignClient;

    public Film save(Film film, String userId) {
        FilmFeign filmFeign = FilmFeign.builder().
                title(film.getTitle()).
                year(film.getYear()).
                userId(userId).
                build();

        return filmFeignClient.saveFilm(filmFeign);
    }

    public List<Film> getByUserId(String userId) {
        List<Film> filmList = filmFeignClient.getFilmsByUserId(userId);
        return filmList != null ? filmList : new ArrayList<>();
    }
}
