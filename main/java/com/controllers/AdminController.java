package com.controllers;

import com.entities.*;
import com.notification.PremiersNotification;
import com.repositories.EventRepository;
import com.repositories.FilmRepository;
import com.repositories.HallRepository;
import com.services.VisitorService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/internal")
@CrossOrigin
public class AdminController {

    private final HallRepository hallRepository;
    private final FilmRepository filmRepository;
    private final EventRepository eventRepository;
    private final VisitorService visitorService;
    private final PremiersNotification premiersNotificationByEmail;
    private final Admin admin;

    public AdminController(
            HallRepository hallRepository,
            FilmRepository filmRepository,
            EventRepository eventRepository,
            VisitorService visitorService,
            Admin admin,
            PremiersNotification premiersNotificationByEmail) {
        this.hallRepository = hallRepository;
        this.filmRepository = filmRepository;
        this.eventRepository = eventRepository;
        this.premiersNotificationByEmail = premiersNotificationByEmail;
        this.admin = admin;
        this.visitorService = visitorService;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

    @ResponseBody
    @RequestMapping(value = "/halls", method = RequestMethod.POST)
    public Iterable<Hall> createHall(@RequestBody List<Hall> hall) {
        return hallRepository.saveAll(
                admin.createHalls(hall)
        );
    }

    @ResponseBody
    @RequestMapping(value = "/films", method = RequestMethod.POST)
    public Iterable<Film> createFilm(@RequestBody List<Film> films) {
        List<Visitor> customers = (List<Visitor>) visitorService.findAll();
        premiersNotificationByEmail
                .setCustomers(
                        customers.stream()
                                .map(Visitor::getEmail)
                                .collect(Collectors.toList())
                );
        premiersNotificationByEmail.setFilms(films);
        premiersNotificationByEmail.notify();
        return filmRepository.saveAll(admin.createFilms(films));
    }

    @ResponseBody
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public Event createEvent(@RequestParam String event , @RequestParam String date) throws ParseException {
        Date endDate = Date.from(Instant.ofEpochMilli(Long.valueOf(date)));
        return eventRepository.save(
                admin.createEvent(event , endDate)
        );
    }
}
