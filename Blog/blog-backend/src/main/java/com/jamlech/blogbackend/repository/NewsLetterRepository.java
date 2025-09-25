package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.NewsLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsLetterRepository extends JpaRepository<NewsLetter, Long> {
}