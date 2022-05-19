package com.tombtomb.jjpostservice.repository;

import com.tombtomb.jjpostservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
