package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}