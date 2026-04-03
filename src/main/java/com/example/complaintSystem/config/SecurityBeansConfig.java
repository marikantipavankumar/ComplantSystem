package com.example.complaintSystem.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityBeansConfig {

    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
