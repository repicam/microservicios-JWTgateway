package es.repicam.users.dto;

import es.repicam.users.entity.User;
import es.repicam.users.model.Book;
import es.repicam.users.model.Film;
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
public class UserResponse {

    private String username;
    private List<Film> films;
    private List<Book> books;

    public static List<UserResponse> buildByEntityList(List<User> userList) {
        if (userList.isEmpty())
            return new ArrayList<>();

        List<UserResponse> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(UserResponse.builder().
                    username(user.getUsername()).
                    build());
        }

        return dtoList;
    }
}
