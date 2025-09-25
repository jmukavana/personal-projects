package com.jamlech.auth_service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String > {
    Optional<User> findByEmailIgnoreCase(String email);
}
