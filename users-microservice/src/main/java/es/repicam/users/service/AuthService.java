package es.repicam.users.service;

import es.repicam.users.dto.TokenDto;
import es.repicam.users.dto.UserRequest;
import es.repicam.users.entity.User;
import es.repicam.users.repository.UserRepository;
import es.repicam.users.security.JWTProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public TokenDto save(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user != null)
            return null;

        userRequest.setPassword(encodePassword(userRequest.getPassword()));
        try {
            User newUser = userRepository.save(User.buildByRequest(userRequest));
            return TokenDto.builder().
                    token(jwtProvider.createToken(newUser)).
                    build();
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }

        return null;
    }

    public TokenDto login(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null)
            return null;

        if (!isPasswordCorrect(userRequest.getPassword(), user.getPassword()))
            return null;

        return TokenDto.builder().
                token(jwtProvider.createToken(user)).
                build();
    }

    public TokenDto validate(String token) {
        if(!jwtProvider.isValid(token))
            return null;

        String email = jwtProvider.getUsernameFromToken(token);
        User user = userRepository.findByUsername(email);
        if (user == null)
            return null;

        return TokenDto.builder().
                token(token).
                build();
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private boolean isPasswordCorrect(String password, String hash){
        return passwordEncoder.matches(password, hash);
    }


}
