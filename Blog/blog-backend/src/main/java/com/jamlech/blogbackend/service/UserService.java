package com.jamlech.blogbackend.service;

import com.jamlech.blogbackend.dto.request.UserRequest;
import com.jamlech.blogbackend.dto.response.UserResponse;
import com.jamlech.blogbackend.mapper.UserMapper;
import com.jamlech.blogbackend.model.User;
import com.jamlech.blogbackend.repository.UserRepository;
import com.jamlech.blogbackend.repository.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserResponse createUser(UserRequest request){
        var userRolesList = userRoleRepository.findAllById(request.roleId());

        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .bio(request.bio())
                .profilePictureUrl(request.profilePictureUrl())
                .roles(userRolesList)
                .build();
        var response = userRepository.save(user);
        return mapper.mapToResponse(response);
    }
    public UserResponse updateUser(Long id, User userDetails) {
        User user = getUserById(id);

        user.setFirstName();
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setBio(userDetails.getBio());
        user.setProfilePictureUrl(userDetails.getProfilePictureUrl());

        return userRepository.save(user);
    }
}
