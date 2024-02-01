package com.example.demo.controllers;

import com.example.demo.models.User;

import com.example.demo.services.users.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User()); // Создаем объект User для формы входа
        return "login";
    }

    @PostMapping("/submitlog")
    public String login(@ModelAttribute("user") User user, Model model) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        if (userDetails != null) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
            );
            return "redirect:/index"; // Перенаправляем на главную страницу
        } else {
            model.addAttribute("passwordError", true);
            return "login";
        }
    }
}
