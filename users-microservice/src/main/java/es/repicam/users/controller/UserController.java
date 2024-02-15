package es.repicam.users.controller;

import es.repicam.users.entity.User;
import es.repicam.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        if (newUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.getAll();
        if (userList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<User> getById(@PathVariable String id) {
        User user = userService.getById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }
}
