package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/utente")
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(UsersService userService) {
        this.usersService = userService;
    }

    @GetMapping
    public List<User> getUser() {
        return usersService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID idUser) {
        Optional<User> user = UsersService.getUserById(idUser);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = UsersService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID idUser, @RequestBody User updatedUser) {
        Optional<User> user = UsersService.updateUser(idUser, updatedUser);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID idUser) {
        UsersService.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }
}
