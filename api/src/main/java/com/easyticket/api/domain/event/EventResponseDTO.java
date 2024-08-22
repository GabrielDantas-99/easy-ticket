package com.easyticket.api.domain.event;

import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record EventResponseDTO(
        UUID id,
        String title,
        String description,
        Date date,
        String city,
        String uf,
        Boolean remote,
        String eventUrl,
        String imgUrl)
{
    public static List<EventResponseDTO> pageToDTOList(Page<Event> eventsPage){
        return eventsPage.map(event -> new EventResponseDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getDate(),
                        event.getAddress() != null ? event.getAddress().getCity() : "",
                        event.getAddress() != null ? event.getAddress().getUf() : "",
                        event.getRemote(),
                        event.getEventUrl(),
                        event.getImgUrl())
                )
                .stream().toList();
    }
}
