package com.tombtomb.jjbackend.repository;

import com.tombtomb.jjbackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
