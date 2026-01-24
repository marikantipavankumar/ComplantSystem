package com.example.complaintSystem.mapper;

import com.example.complaintSystem.dto.CreateUserRequestDTO;
import com.example.complaintSystem.dto.UserResponseDto;
import com.example.complaintSystem.entity.User;

public class UserMapper {

    //Request DTO->Entity
    public static User toEntity(CreateUserRequestDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        return user;
    }

    //Entity->Response DTO
    public static UserResponseDto toResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        return dto;
    }
}
