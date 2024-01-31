package com.example.demo.services.users;

import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findByUsername(String username);

    List<User> getAllUsers();

}
