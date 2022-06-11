package com.tombtomb.jjpostservice.service.impl;

import com.tombtomb.jjpostservice.dto.*;
import com.tombtomb.jjpostservice.model.Post;
import com.tombtomb.jjpostservice.model.Reply;
import com.tombtomb.jjpostservice.model.User;
import com.tombtomb.jjpostservice.repository.PostRepository;
import com.tombtomb.jjpostservice.service.PostService;
import com.tombtomb.jjpostservice.service.UserService;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final Logger logger = Logger.getLogger(PostServiceImpl.class.getName());

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public Post createPost(PostCreateDTO postCreateDTO) {
        logger.info("Creating post");
        Post post = Post.builder()
                .text(postCreateDTO.getText())
                .user(userService.getLoggedUser())
                .replies(List.of())
                .build();
        logger.info("Post created= " + post.getId());
        return postRepository.save(post);
    }

    @Override
    public PostDTO getPost(UUID postId) {
        logger.info("Getting post: "+ postId);
        val post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        logger.info("Got post: "+ post.getId());
        return mapToDTO(post);
    }

    @Override
    public Page<PostDTO> getPostsFor(UUID userId, int pageNo, int pageSize) {
        logger.info("Getting post page for user: "+ userId);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        logger.info("Got "+ pageable.getPageSize() +" posts");
        return postRepository.findAllByUser(userService.getUser(userId), pageable)
                .map(this::mapToDTO);
    }

    @Override
    public PostDTO deletePost(UUID postId) {
        logger.info("Deleting post : "+ postId);
        val post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
        logger.info("Post "+ post.getId()+ "deleted");
        return mapToDTO(post);
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
                .text(replyCreateDTO.getText())
                .user(userService.getLoggedUser())
                .build();

        val replies = post.getReplies();
        replies.add(reply);

        val savedPost = postRepository.save(Post.builder()
                .id(post.getId())
                .text(post.getText())
                .user(post.getUser())
                .replies(replies)
                .build());
        logger.info("Post "+ savedPost.getId()+ "replied by user "+ reply.getUserId());
        return mapToDTO(savedPost);
    }

    private PostDTO mapToDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .text(post.getText())
                .user(
                        mapToDTO(post.getUser())
                )
                .thread(post.getReplies().stream().map(this::mapToDTO).collect(Collectors.toList()))
                .build();
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .id(user.getId())
                .avatar(user.getAvatar())
                .bio(user.getBio())
                .build();
    }

    private ReplyDTO mapToDTO(Reply reply) {
        return ReplyDTO.builder()
                .id(reply.getId())
                .text(reply.getText())
                .user(
                        mapToDTO(reply.getUser())
                )
                .build();
    }
}
