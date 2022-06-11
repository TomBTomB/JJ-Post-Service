package com.tombtomb.jjpostservice.controller;

import com.tombtomb.jjpostservice.dto.PostCreateDTO;
import com.tombtomb.jjpostservice.dto.PostDTO;
import com.tombtomb.jjpostservice.dto.ReplyCreateDTO;
import com.tombtomb.jjpostservice.service.PostService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@Timed("post_controller_time")
public class PostController {
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateDTO postCreateDTO) {
        val post = postService.createPost(postCreateDTO);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        val posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPostFor(
            @PathVariable UUID userId,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        val post = postService.getPostsFor(userId, pageNo, pageSize);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable UUID id) {
        val post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable UUID id) {
        val post = postService.deletePost(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> replyPost(@PathVariable UUID id, @RequestBody ReplyCreateDTO replyCreateDTO) {
        PostDTO post = postService.replyPost(id, replyCreateDTO);
        return ResponseEntity.ok(post);
    }

}
