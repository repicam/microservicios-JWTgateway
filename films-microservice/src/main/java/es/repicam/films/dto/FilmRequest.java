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
public class FilmRequest {

    private String title;
    private Long year;
    private String userId;
}
