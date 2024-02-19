package es.repicam.users.service;

import es.repicam.users.dto.UserRequest;
import es.repicam.users.dto.UserResponse;
import es.repicam.users.entity.User;
import es.repicam.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAll() {
        return UserResponse.buildByEntityList(userRepository.findAll());
    }

    public UserResponse getById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return new UserResponse();

        //llamada otra info (book y film)
        return UserResponse.builder().
                username(user.getUsername()).
                build();
    }

    public UserResponse save(UserRequest userRequest) {

        try {
            userRepository.save(User.buildByRequest(userRequest));
            return UserResponse.builder().
                    username(userRequest.getUsername()).
                    build();
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }
}
