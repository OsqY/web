package com.oscar.web.services;

import com.oscar.web.dto.ClubDto;
import com.oscar.web.dto.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();
    List<EventDto> searchEvents(String query);

    EventDto findByEventId(long eventId);

    void updateEvent(EventDto eventDto);

    void deleteByEventId(long eventId);
}
