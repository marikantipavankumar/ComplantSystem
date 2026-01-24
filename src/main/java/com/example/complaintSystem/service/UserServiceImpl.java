package com.example.complaintSystem.service;

import com.example.complaintSystem.dto.CreateUserRequestDTO;
import com.example.complaintSystem.entity.User;
import com.example.complaintSystem.exception.BadRequestException;
import com.example.complaintSystem.exception.ResourceNotFoundException;
import com.example.complaintSystem.mapper.UserMapper;
import com.example.complaintSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserRequestDTO dto){

        User user = UserMapper.toEntity(dto);
        if(user.getName()==null || user.getName().isBlank()){
            throw new BadRequestException("User name is required");
        }

        if(user.getRole()==null || user.getRole().isBlank()){
            throw new BadRequestException("User Role is required");
        }
        String role=user.getRole().toUpperCase();
        if(!role.equals("USER") && !role.equals("ADMIN")){
            throw new BadRequestException("Role must be USER or ADMIN");
        }
        user.setRole(role);
        return userRepository.save(user);
    }



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found!"));
    }

    // updating the user details
    @Override
    public User updateUser(Long id, User updatedUser) {

        User existingUser=userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(updatedUser.getName()!=null && !updatedUser.getName().isBlank()){
            existingUser.setName(updatedUser.getName());
        }

        if(updatedUser.getRole()!=null && !updatedUser.getRole().isBlank()){
            existingUser.setRole(updatedUser.getRole());
        }
        return userRepository.save(existingUser);
    }


    // Delete the existing user
    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }


}
