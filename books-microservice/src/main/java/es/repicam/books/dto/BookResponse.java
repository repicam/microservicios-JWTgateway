package es.repicam.books.dto;

import es.repicam.books.entity.Book;
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
public class BookResponse {

    private Long id;
    private String title;
    private String author;

    public static List<BookResponse> buildByEntityList(List<Book> bookList) {
        if (bookList.isEmpty())
            return new ArrayList<>();

        List<BookResponse> dtoList = new ArrayList<>();
        for (Book book : bookList) {
            dtoList.add(BookResponse.builder().
                    id(book.getId()).
                    title(book.getTitle()).
                    author(book.getAuthor()).
                    build());
        }

        return dtoList;
    }
}
