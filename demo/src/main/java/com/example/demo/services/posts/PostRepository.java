package com.example.demo.services.posts;

import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findAllByAuthorId(Long authorId);
}
