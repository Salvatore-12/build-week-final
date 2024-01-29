package Team3.buildweekfinal.controls;

import Team3.buildweekfinal.Entities.User;
import Team3.buildweekfinal.services.UserService;
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

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping
        public List<User> getUser() {
            return userService.getAllUser();
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable UUID idUser) {
            Optional<User> user = UserService.getUserById(idUser);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User savedUser = UserService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }

        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable UUID idUser, @RequestBody User updatedUser) {
            Optional<User> user = UserService.updateUser(idUser, updatedUser);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable UUID idUser) {
            UserService.deleteUser(idUser);
            return ResponseEntity.noContent().build();
        }
}
