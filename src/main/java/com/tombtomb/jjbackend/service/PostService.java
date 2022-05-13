package com.tombtomb.jjbackend.service;

import com.tombtomb.jjbackend.dto.PostCreateDTO;
import com.tombtomb.jjbackend.dto.PostDTO;
import com.tombtomb.jjbackend.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post createPost(PostCreateDTO postCreateDTO);

    PostDTO getPost(UUID postId);

    List<PostDTO> getPostsFor(UUID userId);

    PostDTO deletePost(UUID postId);

}
