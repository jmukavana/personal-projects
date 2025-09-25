package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.PostView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
}