package com.tombtomb.jjbackend.service;

import com.tombtomb.jjbackend.dto.PostCreateDTO;
import com.tombtomb.jjbackend.dto.PostDTO;
import com.tombtomb.jjbackend.model.Post;
import com.tombtomb.jjbackend.dto.PostPage;

import java.util.UUID;

public interface PostService {

    Post createPost(PostCreateDTO postCreateDTO);

    PostDTO getPost(UUID postId);

    PostPage getPostsFor(UUID userId, int pageNo, int pageSize);

    PostDTO deletePost(UUID postId);

}
