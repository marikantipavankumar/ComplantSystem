package com.example.complaintSystem.service;

import com.example.complaintSystem.dto.CreateUserRequestDTO;
import com.example.complaintSystem.entity.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserRequestDTO dto);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id,User updatedUser);
    void deleteUser(Long id);

//    String register(RegisterRequest request);
//    String login(LoginRequest request);
//
}
