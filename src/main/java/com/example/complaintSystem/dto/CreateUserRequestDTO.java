package com.example.complaintSystem.dto;

import jakarta.validation.constraints.NotBlank;
public class CreateUserRequestDTO {

    @NotBlank(message="User name is Required")
    private String name;

    @NotBlank(message = "User role is required")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
