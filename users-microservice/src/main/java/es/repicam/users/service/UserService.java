package es.repicam.users.service;

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

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {

        try {
            return userRepository.save(user);
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }
}
