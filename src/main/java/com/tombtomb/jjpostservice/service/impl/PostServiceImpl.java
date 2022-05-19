package com.tombtomb.jjpostservice.service.impl;

import com.tombtomb.jjpostservice.dto.PostCreateDTO;
import com.tombtomb.jjpostservice.dto.PostDTO;
import com.tombtomb.jjpostservice.model.Post;
import com.tombtomb.jjpostservice.dto.PostPage;
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
    public PostPage getPostsFor(UUID userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();

        List<PostDTO> content= listOfPosts.stream()
                .filter(post -> post.getUserId().equals(userId))
                .map(this::mapToDTO).collect(Collectors.toList());

        return PostPage.builder()
                .content(content)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
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
