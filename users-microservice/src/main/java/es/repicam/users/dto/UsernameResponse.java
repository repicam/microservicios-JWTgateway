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
public class UsernameResponse {

    private String username;
}
