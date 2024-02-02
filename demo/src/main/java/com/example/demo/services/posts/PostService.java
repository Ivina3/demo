package com.example.demo.services.posts;

import com.example.demo.models.Post;

public interface PostService {
    void save(Post post);
    void deleteById(long id);
    void update(Post post, long id);

    Post findById(long id);
}
