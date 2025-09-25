package com.jamlech.auth_service.user;

import com.jamlech.auth_service.user.request.ProfileUpdateRequest;
import com.jamlech.auth_service.user.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private  final PasswordEncoder passwordEncoder;




    public User toUser(RegistrationRequest request){
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .enabled(true)
                .locked(false)
                .expired(true)
                .emailVerified(false)
                .credentialsExpired(false)
                .phoneNumberVerified(false)
                .dateOfBirth(LocalDate.now())
                .build();

    }

    public void mergerInfo(ProfileUpdateRequest request,User user){
        if (StringUtils.isNotBlank(request.firstName())
        && !user.getFirstName().equals(request.firstName())){
            user.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())
                && !user.getLastName().equals(request.lastName())
        ) {
            user.setLastName(request.lastName());
        }
        if (request.dateOfBirth() != null
                && !user.getDateOfBirth().equals(request.dateOfBirth())
        ) {
            user.setDateOfBirth(request.dateOfBirth());
        }
    }
}
