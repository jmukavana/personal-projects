package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}