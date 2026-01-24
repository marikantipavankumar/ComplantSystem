package com.example.complaintSystem.controller;

import com.example.complaintSystem.dto.CreateUserRequestDTO;
import com.example.complaintSystem.dto.UserResponseDto;
import com.example.complaintSystem.entity.User;
import com.example.complaintSystem.mapper.UserMapper;
import com.example.complaintSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody CreateUserRequestDTO dto){
       User user = userService.createUser(dto);
        return UserMapper.toResponseDto(user);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id){
        return UserMapper.toResponseDto(userService.getUserById(id));
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody User user
    ){
        return UserMapper.toResponseDto(userService.updateUser(id,user));
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
