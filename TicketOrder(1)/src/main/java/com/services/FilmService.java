package com.services;

import com.entities.Film;
import com.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
@ComponentScan("com")
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film getById(UUID id ){
        return filmRepository.findById(id).get();
    }

    public Set getAll(){
        return (Set)filmRepository.findAll();
    }

    public Set getUntilDate(){
        return filmRepository.findPremiers();
    }

}
