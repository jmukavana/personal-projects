package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}