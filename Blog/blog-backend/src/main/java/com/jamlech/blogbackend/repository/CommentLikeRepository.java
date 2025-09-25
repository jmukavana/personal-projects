package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
}