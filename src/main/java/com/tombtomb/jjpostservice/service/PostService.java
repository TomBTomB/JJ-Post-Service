package com.tombtomb.jjpostservice.service;

import com.tombtomb.jjpostservice.dto.*;
import com.tombtomb.jjpostservice.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post createPost(PostCreateDTO postCreateDTO);

    PostDTO getPost(UUID postId);

    Page<PostDTO> getPostsFor(UUID userId, int pageNo, int pageSize);

    PostDTO deletePost(UUID postId);

    List<PostDTO> getAllPosts();

    PostDTO replyPost(UUID id, ReplyCreateDTO replyCreateDTO);
}
