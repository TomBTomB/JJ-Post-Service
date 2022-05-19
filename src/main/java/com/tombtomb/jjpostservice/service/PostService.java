package com.tombtomb.jjpostservice.service;

import com.tombtomb.jjpostservice.dto.PostCreateDTO;
import com.tombtomb.jjpostservice.dto.PostDTO;
import com.tombtomb.jjpostservice.model.Post;
import com.tombtomb.jjpostservice.dto.PostPage;

import java.util.UUID;

public interface PostService {

    Post createPost(PostCreateDTO postCreateDTO);

    PostDTO getPost(UUID postId);

    PostPage getPostsFor(UUID userId, int pageNo, int pageSize);

    PostDTO deletePost(UUID postId);

}
