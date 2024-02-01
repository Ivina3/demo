package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.services.users.UserService;
import com.example.demo.services.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;


    @GetMapping("/reg")
    public String showPage(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("passwordAnother", "");
        return "reg";
    }

    @PostMapping("/submit")
    public String reg(@ModelAttribute("user") User user,
                      @RequestParam(name = "passwordAnother", required = false) String passwordAnother,
                      Model model){
        if (!user.getPassword().equals(passwordAnother)){
            model.addAttribute("passwordMismatch", true);
            return "reg";
        }

        else if (userService.findByUsername(user.getUsername())!=null){
            model.addAttribute("userIsExists", true);
            return "reg";
        }
        else {
            userService.save(user);
            return "redirect:index";
        }
    }
}
