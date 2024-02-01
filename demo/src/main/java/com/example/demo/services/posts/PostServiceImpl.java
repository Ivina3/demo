package com.example.demo.services.posts;

import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void update(Post newPost) {
        Post updatablePost = postRepository.findById(newPost.getId()).orElse(null);
        if (updatablePost != null){
            updatablePost.setText(newPost.getText());
            updatablePost.setTitle(newPost.getTitle());
            postRepository.save(updatablePost);
        }
    }
}
