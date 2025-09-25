package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

}