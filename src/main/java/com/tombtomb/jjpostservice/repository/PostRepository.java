package com.tombtomb.jjpostservice.repository;

import com.tombtomb.jjpostservice.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Page<Post> findAllByUsername(String username, Pageable pageable);
}
