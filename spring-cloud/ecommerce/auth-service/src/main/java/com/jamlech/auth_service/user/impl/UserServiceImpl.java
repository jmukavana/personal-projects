package com.jamlech.auth_service.user.impl;

import com.jamlech.auth_service.user.User;
import com.jamlech.auth_service.user.UserMapper;
import com.jamlech.auth_service.user.UserRepository;
import com.jamlech.auth_service.user.UserService;
import com.jamlech.auth_service.user.request.ChangePasswordRequest;
import com.jamlech.auth_service.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not with email::"+userEmail));

    }
    @Override
    public void updateProfileInfo(ProfileUpdateRequest request, String userId) {
        User savedUser = this.userRepository.findById(userId)
                .orElseThrow();


    }

    @Override
    public void changePassword(ChangePasswordRequest request, String userId) {

    }

    @Override
    public void deactivateAccount(String userId) {

    }

    @Override
    public void reactivateAccount(String userId) {

    }

    @Override
    public void deleteAccount(String userId) {

    }


}
