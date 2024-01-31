package com.example.demo.services.users;

import com.example.demo.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);
    User findByUsername(String username);

    List<User> getAllUsers();

}
