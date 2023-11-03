package com.oscar.web.repositories;

import com.oscar.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e from Event e where e.eventName LIKE concat('%', :query ,'%') ")
    List<Event> searchEvents(String query);

}
