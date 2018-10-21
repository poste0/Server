package com.controllers;

import com.entities.Film;
import com.entities.Rating;
import com.repositories.FilmRepository;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/film")
@ComponentScan("com")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    private FilmRepository repository;

    @Autowired
    public void setRepository(FilmRepository repository){this.repository = repository;}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity createFilm() {
        Film film = filmService.someWork();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(film);
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ResponseEntity findAllFilms(){
        List films = new ArrayList();
        for(int i = 0 ; i  < 100 ; i++){
            films.add(new Film("Title " + i , "Decription " + i , new Rating() , new Date()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(films);
    }
}
