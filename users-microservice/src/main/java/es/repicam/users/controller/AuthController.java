package es.repicam.users.controller;

import es.repicam.users.dto.TokenDto;
import es.repicam.users.dto.UserRequest;
import es.repicam.users.service.AuthService;
import es.repicam.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login (@RequestBody UserRequest userRequest){
        TokenDto token = authService.login(userRequest);
        if (token == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate (@RequestParam String tokenParam){
        TokenDto token = authService.validate(tokenParam);
        if (token == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDto> create (@RequestBody UserRequest userRequest){
        TokenDto token = authService.save(userRequest);
        if (token == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(token);
    }


}
