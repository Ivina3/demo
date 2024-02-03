package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.posts.PostService;
import com.example.demo.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);
        return "index";
    }
}
