package com.tombtomb.jjpostservice.service.impl;

import com.tombtomb.jjpostservice.dto.*;
import com.tombtomb.jjpostservice.model.Post;
import com.tombtomb.jjpostservice.model.Reply;
import com.tombtomb.jjpostservice.repository.PostRepository;
import com.tombtomb.jjpostservice.service.PostService;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
                .userId(postCreateDTO.getUserId())
                .build();
        return postRepository.save(post);
    }

    @Override
    public PostDTO getPost(UUID postId) {
        val post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostDTO.builder()
                .id(post.getId())
                .body(post.getBody())
                .userId(post.getUserId())
                .build();
    }

    @Override
    public Page<PostDTO> getPostsFor(UUID userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return postRepository.findAllByUserId(userId, pageable)
                .map(this::mapToDTO);
    }

    @Override
    public PostDTO deletePost(UUID postId) {
        val post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
        return PostDTO.builder()
                .id(post.getId())
                .body(post.getBody())
                .userId(post.getUserId())
                .build();
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO replyPost(UUID id, ReplyCreateDTO replyCreateDTO) {
        val post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        val reply = Reply.builder()
                .body(replyCreateDTO.getBody())
                .userId(replyCreateDTO.getUserId())
                .build();

        val replies = post.getReplies();
        replies.add(reply);

        val savedPost = postRepository.save(Post.builder()
                .id(post.getId())
                .body(post.getBody())
                .userId(post.getUserId())
                .replies(replies)
                .build());
        return mapToDTO(savedPost);
    }

    private PostDTO mapToDTO(Post post){
        return PostDTO.builder()
                .id(post.getId())
                .body(post.getBody())
                .userId(post.getUserId())
                .build();
    }

    private Post mapToEntity(PostDTO postDTO){
        return Post.builder()
                .id(postDTO.getId())
                .body(postDTO.getBody())
                .userId(postDTO.getUserId())
                .build();
    }
}
