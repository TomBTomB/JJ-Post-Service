package com.tombtomb.jjbackend.service;

import com.tombtomb.jjbackend.dto.PostCreateDTO;
import com.tombtomb.jjbackend.dto.PostDTO;
import com.tombtomb.jjbackend.model.Post;
import com.tombtomb.jjbackend.model.PostResponse;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post createPost(PostCreateDTO postCreateDTO);

    PostDTO getPost(UUID postId);

    PostResponse getPostsFor(UUID userId, int pageNo, int pageSize);

    PostDTO deletePost(UUID postId);

}
