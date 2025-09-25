package com.jamlech.backend.mapper;

import com.jamlech.backend.domain.dto.CreateEventRequest;
import com.jamlech.backend.domain.dto.CreateEventRequestDto;
import com.jamlech.backend.domain.dto.CreateEventResponseDto;
import com.jamlech.backend.domain.dto.CreateTicketTypeRequest;
import com.jamlech.backend.domain.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto(CreateEventRequestDto dto);
    CreateEventResponseDto toDto(Event event);
}
