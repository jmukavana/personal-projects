package com.jamlech.backend.service;

import com.jamlech.backend.domain.dto.CreateEventRequest;
import com.jamlech.backend.domain.entity.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
