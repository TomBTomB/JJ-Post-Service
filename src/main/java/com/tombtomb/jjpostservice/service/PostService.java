package com.tombtomb.jjpostservice.service;

import com.tombtomb.jjpostservice.dto.PostCreateDTO;
import com.tombtomb.jjpostservice.dto.PostDTO;
import com.tombtomb.jjpostservice.dto.ReplyCreateDTO;
import com.tombtomb.jjpostservice.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post createPost(PostCreateDTO postCreateDTO);

    PostDTO getPost(UUID postId);

    Page<PostDTO> getPostsFor(String username, int pageNo, int pageSize);

    PostDTO deletePost(UUID postId);

    List<PostDTO> getAllPosts();

    PostDTO replyPost(UUID id, ReplyCreateDTO replyCreateDTO);
}
