package com.example.demo.services.posts;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public void update(Post newPost, long id) {
        Post updatablePost = postRepository.findById(id).orElse(null);

        if (updatablePost!=null){
            updatablePost.setText(newPost.getText());
            updatablePost.setTitle(newPost.getTitle());
            postRepository.save(updatablePost);
        }
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> findAllByAuthorId(Long authorId) {
        return postRepository.findAllByAuthorId(authorId);
    }
}
