package com.controllers;

import com.entities.Film;
import com.services.FilmService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
@ComponentScan("com")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity createFilm() {
        Film film = filmService.someWork();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(film);
    }
}
