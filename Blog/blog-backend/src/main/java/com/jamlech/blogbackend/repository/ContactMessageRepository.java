package com.jamlech.blogbackend.repository;

import com.jamlech.blogbackend.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}