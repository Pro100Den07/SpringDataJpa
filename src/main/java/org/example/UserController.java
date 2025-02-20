package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user.getName(), user.getEmail()), HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> findUserByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
    }

    @GetMapping("/domain/{domain}")
    public ResponseEntity<List<User>> findUsersByDomain(@PathVariable String domain) {
        return new ResponseEntity<>(userService.findUsersByDomain(domain), HttpStatus.OK);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<Post>> findPostsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findPostsByUserId(userId), HttpStatus.OK);
    }
}
