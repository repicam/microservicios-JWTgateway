package es.repicam.films.entity;

import es.repicam.films.dto.FilmRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "films")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long year;
    @Column(name = "user_id")
    private String userId;

    public static Film buildByRequest(FilmRequest request) {
        if (request == null)
            return null;

        return Film.builder().
                title(request.getTitle()).
                year(request.getYear()).
                userId(request.getUserId()).
                build();
    }
}
