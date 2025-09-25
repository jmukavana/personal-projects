package com.jamlech.backend.domain.dto;

import com.jamlech.backend.domain.enumeration.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    @NotBlank(message = "Venue is required")
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    @NotBlank(message = "Status is required")
    private EventStatusEnum status;
    @NotEmpty(message = "Ticket types are required")
    @Valid
    private List<CreateTicketTypeRequest> ticketTypes = new ArrayList<>();
}
