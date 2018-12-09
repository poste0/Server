package com.controllers;

import com.entities.*;
import com.services.EventService;
import com.services.FilmService;
import com.services.SessionService;
import com.services.VisitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/external")
@CrossOrigin
public class SessionController {

    private final FilmService filmService;
    private final VisitorService visitorService;
    private final SessionService sessionService;
    private final EventService eventService;
    SessionController(FilmService filmService, VisitorService visitorService, SessionService sessionService, EventService eventService) {
        this.filmService = filmService;
        this.visitorService = visitorService;
        this.sessionService = sessionService;
        this.eventService = eventService;
    }

    @ResponseBody
    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public List<Film> films(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return filmService.list(page, size);
    }

    @ResponseBody
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public List<Session> sessions(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return sessionService.list(page, size);
    }

    @ResponseBody
    @RequestMapping(value = "/films/{titlePart}", method = RequestMethod.GET)
    public List<Film> findAllByTitlePart(
            @PathVariable String titlePart,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return filmService.findByTitlePart(titlePart, page, size);
    }

    @ResponseBody
    @RequestMapping(value = "films/rating/update", method = RequestMethod.PUT)
    public Film updateRating(
            @RequestParam("filmId") Long id,
            @RequestBody Rating rating
    ) {
        return filmService.upgradeRating(id, rating);
    }

    @ResponseBody
    @RequestMapping(value = "order/", method = RequestMethod.POST)
    public Booking ordering(
            @RequestBody Visitor visitor,
            @RequestParam Long sessionId,
            @RequestParam Integer ticketsCount
    ) {
        System.out.println("asdasf");
        return visitorService.makeChoice(visitor, sessionId, ticketsCount);
    }

    @ResponseBody
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> getEvents(){
        return eventService.findAllEvents();
    }
}
