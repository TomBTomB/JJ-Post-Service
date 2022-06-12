package com.tombtomb.jjpostservice.controller;

import com.tombtomb.jjpostservice.service.UserService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@Timed("user_controller_time")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Timed
    public ResponseEntity<?> getLoggedUser() {
        val user = userService.getLoggedUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        val user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search/{searchString}")
    @Timed
    public ResponseEntity<?> getUser(@PathVariable String searchString) {
        val users = userService.searchUser(searchString);
        return ResponseEntity.ok(users);
    }

}
