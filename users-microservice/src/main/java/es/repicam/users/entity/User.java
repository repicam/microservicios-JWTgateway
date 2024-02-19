package es.repicam.users.entity;

import es.repicam.users.dto.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;

    public static User buildByRequest(UserRequest request) {
        if (request == null)
            return null;

        return User.builder().
                username(request.getUsername()).
                password(request.getPassword()).
                build();
    }
}
