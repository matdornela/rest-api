package com.randoli.restapi.service;

import com.randoli.restapi.exception.EntityException;
import com.randoli.restapi.model.Event;
import com.randoli.restapi.repository.EventRepository;
import com.randoli.restapi.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Optional<Event> getEventById(UUID id) {
        var optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            throw new EntityException();
        }
        return optionalEvent;
    }

    public List<Event> getEvents() {
        var listEvent = eventRepository.findAll();
        if (listEvent.isEmpty()) {
            throw new EntityException();
        }
        return eventRepository.findAll();
    }

    public Event update(UUID eventId, Event event) {
        var eventById = getEventById(eventId);
        if (eventById.isPresent()) {
            event.setEventId(eventId);
        }
        return eventRepository.save(event);
    }

    public void delete(UUID id) {
        Optional<Event> eventById;
        try {
            eventById = getEventById(id);
        } catch (Exception e) {
            throw new EntityException(e);
        }
        eventById.ifPresent(eventRepository::delete);
    }

    public List<Event> insertEventJson() throws IOException {
        var batch = JsonParser.ConvertJsonToPojo();
        var eventsCreated = new ArrayList<Event>();
        for (var record : batch.records) {
            var eventList = record.getEvent();
            for (var event : eventList) {
                event.setTransId(record.getTransId());
                event.setTransTms(record.getTransTms());
                event.setClientId(record.getTransId());
                create(event);
                eventsCreated.add(event);
            }
        }
        return eventsCreated;
    }

}

