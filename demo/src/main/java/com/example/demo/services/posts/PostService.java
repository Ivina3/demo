package com.example.demo.services.posts;

import com.example.demo.models.Post;

import java.util.List;

public interface PostService {
    void save(Post post);
    void deleteById(long id);
    void update(Post post, long id);

    List<Post> findAll();
    Post findById(long id);
}
