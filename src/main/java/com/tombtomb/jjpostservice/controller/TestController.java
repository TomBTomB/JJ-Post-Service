package com.tombtomb.jjpostservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/anonymous")
    public ResponseEntity<?> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok("Hello All User");
    }

}
