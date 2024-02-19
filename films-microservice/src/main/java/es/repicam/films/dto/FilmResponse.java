package es.repicam.films.dto;

import es.repicam.films.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmResponse {

    private Long id;
    private String title;
    private Long year;

    public static List<FilmResponse> buildByEntityList(List<Film> filmList) {
        if (filmList.isEmpty())
            return new ArrayList<>();

        List<FilmResponse> dtoList = new ArrayList<>();
        for (Film film : filmList) {
            dtoList.add(FilmResponse.builder().
                    id(film.getId()).
                    title(film.getTitle()).
                    year(film.getYear()).
                    build());
        }

        return dtoList;
    }
}
