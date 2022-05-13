package com.tombtomb.jjbackend.controller;

import com.tombtomb.jjbackend.dto.PostCreateDTO;
import com.tombtomb.jjbackend.service.PostService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateDTO postCreateDTO) {
         val post = postService.createPost(postCreateDTO);
         return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable UUID id) {
        val post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

}
