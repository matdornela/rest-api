package com.randoli.restapi.controller;

import com.randoli.restapi.exception.EntityException;
import com.randoli.restapi.model.Event;
import com.randoli.restapi.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/events"})
@Api(value = "REST API Randoli")
@CrossOrigin(origins = "*")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @ApiOperation(value = "Add a new event")
    @PostMapping
    public Event create(@RequestBody Event event) {
        return service.create(event);
    }

    @ApiOperation(value = "Find all events")
    @GetMapping
    public List<Event> findAll() {
        List<Event> listEvent;
        try {
            listEvent = service.getEvents();
        } catch (EntityException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return listEvent;
    }

    @ApiOperation(value = "Find an event by ID (UUID) ")
    @GetMapping(path = {"/{eventId}"})
    public Object findById(@PathVariable UUID eventId) {
        Optional<Event> event;
        try {
            event = service.getEventById(eventId);
        } catch (EntityException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return event;
    }

    @ApiOperation(value = "Updates an event ")
    @PutMapping(value = "/{eventId}")
    public Event update(@PathVariable("eventId") UUID eventId,
                        @RequestBody Event event) {
        Event eventUpdated;
        try {
            eventUpdated = service.update(eventId, event);
        } catch (EntityException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return eventUpdated;

    }

    @ApiOperation(value = "Deletes an event")
    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable("id") UUID eventId) {
        try {
            service.delete(eventId);
        } catch (EntityException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @ApiOperation(value = "Import events into Database using a JSON payload")
    @PostMapping("/import")
    public List<Event> post() throws IOException {
        return service.insertEventJson();
    }
}

