package es.repicam.films.service;

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

    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    public Film getById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public Film save(Film film) {

        try {
            return filmRepository.save(film);
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }

    public List<Film> getByUserId(String userId){
        return filmRepository.findByUserId(userId);
    }
}
