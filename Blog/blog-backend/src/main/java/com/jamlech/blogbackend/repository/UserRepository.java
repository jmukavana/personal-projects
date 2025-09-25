package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}