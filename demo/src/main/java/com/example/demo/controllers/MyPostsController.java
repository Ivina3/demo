package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.posts.PostService;
import com.example.demo.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyPostsController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/myPosts")
    public String myPosts(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        Long currentUserId = userService.findByUsername(currentUsername).getId();
        List<Post> posts = postService.findAllByAuthorId(currentUserId);
        model.addAttribute("posts",posts);
        return "myPosts";
    }
}
