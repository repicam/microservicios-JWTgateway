package es.repicam.books.entity;

import es.repicam.books.dto.BookRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    @Column(name = "user_id")
    private String userId;

    public static Book buildByRequest(BookRequest request) {
        if (request == null)
            return null;

        return Book.builder().
                title(request.getTitle()).
                author(request.getAuthor()).
                userId(request.getUserId()).
                build();
    }
}
