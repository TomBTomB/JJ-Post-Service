package com.tombtomb.jjpostservice.repository;

import com.tombtomb.jjpostservice.model.Post;
import com.tombtomb.jjpostservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Page<Post> findAllByUser(User userId, Pageable pageable);
}
