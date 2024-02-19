package es.repicam.films.service;

import es.repicam.films.dto.FilmRequest;
import es.repicam.films.dto.FilmResponse;
import es.repicam.films.entity.Film;
import es.repicam.films.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private Logger logger = LoggerFactory.getLogger(FilmService.class);

    @Autowired
    private FilmRepository filmRepository;

    public List<FilmResponse> getAll() {
        return FilmResponse.buildByEntityList(filmRepository.findAll());
    }

    public FilmResponse getById(Long id) {
        Film film = filmRepository.findById(id).orElse(null);
        if (film == null)
            return new FilmResponse();

        return FilmResponse.builder().
                id(id).
                title(film.getTitle()).
                year(film.getYear()).
                build();
    }

    public FilmResponse save(FilmRequest filmRequest) {

        try {
            Film newFilm = filmRepository.save(Film.buildByRequest(filmRequest));
            return FilmResponse.builder().
                    id(newFilm.getId()).
                    title(newFilm.getTitle()).
                    year(newFilm.getYear()).
                    build();
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }

    public List<FilmResponse> getByUserId(String userId){
        return FilmResponse.buildByEntityList(filmRepository.findByUserId(userId));
    }
}
