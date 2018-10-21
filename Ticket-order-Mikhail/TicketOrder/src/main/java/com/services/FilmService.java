package com.services;

import com.entities.Film;
import com.entities.Hall;
import com.entities.Rating;
import com.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@Service
@ComponentScan("com")
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional
    public Film someWork() {
        Rating rating = new Rating();
        rating.upgrade(8d);
        Film film = filmRepository.save(new Film("title", "description", rating , Date.from(Instant.parse("2018-10-02"))));
        return film;
    }
}
