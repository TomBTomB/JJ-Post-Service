package com.tombtomb.jjbackend.service.impl;

import com.tombtomb.jjbackend.dto.PostCreateDTO;
import com.tombtomb.jjbackend.dto.PostDTO;
import com.tombtomb.jjbackend.model.Post;
import com.tombtomb.jjbackend.repository.PostRepository;
import com.tombtomb.jjbackend.service.PostService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostCreateDTO postCreateDTO) {
        Post post = Post.builder()
                .body(postCreateDTO.getBody())
                .build();
        return postRepository.save(post);
    }

    @Override
    public PostDTO getPost(UUID postId) {
        val post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostDTO.builder()
                .id(post.getId())
                .body(post.getBody())
                .build();
    }

    @Override
    public List<PostDTO> getPostsFor(UUID userId) {
        return null;
    }

    @Override
    public PostDTO deletePost(UUID postId) {
        return null;
    }
}
