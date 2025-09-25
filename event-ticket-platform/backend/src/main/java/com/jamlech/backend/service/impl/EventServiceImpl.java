package com.jamlech.backend.service.impl;

import com.jamlech.backend.domain.dto.CreateEventRequest;
import com.jamlech.backend.domain.entity.Event;
import com.jamlech.backend.domain.entity.TicketType;
import com.jamlech.backend.domain.entity.User;
import com.jamlech.backend.repository.EventRepository;
import com.jamlech.backend.repository.UserRepository;
import com.jamlech.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private  final EventRepository eventRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId).orElseThrow(
                ()->new UsernameNotFoundException(
                        String.format("User with id %s not found", organizerId)
                )
        );
        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream()
                .map(ticketType->{
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    return ticketTypeToCreate;
                }).toList();
        Event eventToCreate = new Event();
        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);
        return eventRepository.save(eventToCreate);
    }
}
