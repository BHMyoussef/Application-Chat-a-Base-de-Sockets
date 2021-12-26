package com.chatApp.chatBackEnd.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/chat")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<UserApp> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody UserApp user) {
        this.userService.addNewUser(user);
    }
    @GetMapping(path = "/users/{userId}")
    public Optional<UserApp> getById(@PathVariable("userId") long userId) {
        return this.userService.getById(userId);
    }
}
