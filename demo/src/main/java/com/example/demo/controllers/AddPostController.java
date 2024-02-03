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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddPostController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @GetMapping("/addPost")
    public String showPageAdd(Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("isAdd", true);
        return "addPost";
    }

    @GetMapping("/updatePost")
    public String showPageUpdate(@RequestParam(name = "postId", required = false) Long postId, Model model){
        if (postId != null) {
            Post existingPost = postService.findById(postId);
            model.addAttribute("post", existingPost);
            model.addAttribute("isAdd", false);
            model.addAttribute("postId", postId);
        }
        else {
            model.addAttribute("post", new Post());
            model.addAttribute("isAdd", true);
        }
        return "addPost";
    }

    @PostMapping("/addNewPost")
    public String addPost(Post post){
        if (!post.getTitle().equals("")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Проверить, что пользователь авторизован
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                // Получить username текущего пользователя (или другой идентификатор, если есть)
                String currentUsername = userDetails.getUsername();

                // Теперь у вас есть username текущего пользователя, вы можете использовать его
                // Например, если ваш пользователь имеет поле id, вы можете получить его так:
                Long currentUserId = userService.findByUsername(currentUsername).getId();

                // Установить идентификатор пользователя в ваш объект Post
                post.setAuthor_id(currentUserId);
                postService.save(post);
            }
            return "redirect:index";
        }
        else {
            return "addPost";
        }
    }

    @PostMapping("/updateNewPost{postId}")
    public String updatePost(@PathVariable long postId, Post post){
        if (!post.getTitle().equals("")){
            postService.update(post, postId);
            return "redirect:index";
        }
        else {
            return "addPost";
        }
    }
}
