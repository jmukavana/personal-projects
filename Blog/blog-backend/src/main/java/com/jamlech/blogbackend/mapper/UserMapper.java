package com.jamlech.blogbackend.mapper;

import com.jamlech.blogbackend.dto.response.UserResponse;
import com.jamlech.blogbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse mapToResponse(User response) {
        return UserResponse.builder().build();
    }
}
