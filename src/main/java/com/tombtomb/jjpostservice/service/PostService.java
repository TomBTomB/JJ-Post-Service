package com.tombtomb.jjpostservice.service;

import com.tombtomb.jjpostservice.dto.PostCreateDTO;
import com.tombtomb.jjpostservice.dto.PostDTO;
import com.tombtomb.jjpostservice.model.Post;
import com.tombtomb.jjpostservice.dto.PostPage;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PostService {

    Post createPost(PostCreateDTO postCreateDTO);

    PostDTO getPost(UUID postId);

    Page<PostDTO> getPostsFor(UUID userId, int pageNo, int pageSize);

    PostDTO deletePost(UUID postId);

}
