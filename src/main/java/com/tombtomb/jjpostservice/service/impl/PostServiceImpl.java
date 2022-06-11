package com.tombtomb.jjpostservice.service.impl;

import com.tombtomb.jjpostservice.dto.*;
import com.tombtomb.jjpostservice.model.Post;
import com.tombtomb.jjpostservice.model.Reply;
import com.tombtomb.jjpostservice.repository.PostRepository;
import com.tombtomb.jjpostservice.service.PostService;
import lombok.val;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final Logger logger = Logger.getLogger(PostServiceImpl.class.getName());

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostCreateDTO postCreateDTO) {
        logger.info("Creating post");
        Post post = Post.builder()
                .text(postCreateDTO.getText())
                .userId(postCreateDTO.getUserId())
                .build();
        logger.info("Post created= " + post.getId());
        return postRepository.save(post);
    }

    @Override
    public PostDTO getPost(UUID postId) {
        logger.info("Getting post: "+ postId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();

        val post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        logger.info("Got post: "+ post.getId());
        return PostDTO.builder()
                .id(post.getId())
                .text(post.getText())
                .userId(UUID.fromString(principal.getKeycloakSecurityContext().getToken().getId()))
                .build();
    }

    @Override
    public Page<PostDTO> getPostsFor(UUID userId, int pageNo, int pageSize) {
        logger.info("Getting post page for user: "+ userId);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        logger.info("Got "+ pageable.getPageSize() +" posts");
        return postRepository.findAllByUserId(userId, pageable)
                .map(this::mapToDTO);
    }

    @Override
    public PostDTO deletePost(UUID postId) {
        logger.info("Deleting post : "+ postId);
        val post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
        logger.info("Post "+ post.getId()+ "deleted");
        return PostDTO.builder()
                .id(post.getId())
                .text(post.getText())
                .userId(post.getUserId())
                .build();
    }

    @Override
    public List<PostDTO> getAllPosts() {
        logger.info("Getting all posts");
        return postRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO replyPost(UUID id, ReplyCreateDTO replyCreateDTO) {
        logger.info("Replying post "+ id);
        val post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        val reply = Reply.builder()
                .text(replyCreateDTO.getBody())
                .userId(replyCreateDTO.getUserId())
                .build();

        val replies = post.getReplies();
        replies.add(reply);

        val savedPost = postRepository.save(Post.builder()
                .id(post.getId())
                .text(post.getText())
                .userId(post.getUserId())
                .replies(replies)
                .build());
        logger.info("Post "+ savedPost.getId()+ "replied by user "+ reply.getUserId());
        return mapToDTO(savedPost);
    }

    private PostDTO mapToDTO(Post post){
        return PostDTO.builder()
                .id(post.getId())
                .text(post.getText())
                .userId(post.getUserId())
                .build();
    }

    private Post mapToEntity(PostDTO postDTO){
        return Post.builder()
                .id(postDTO.getId())
                .text(postDTO.getText())
                .userId(postDTO.getUserId())
                .build();
    }
}
