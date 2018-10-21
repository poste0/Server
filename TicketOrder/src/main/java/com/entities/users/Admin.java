package com.entities.users;

import com.entities.Film;
import com.entities.Hall;
import com.entities.Rating;
import com.entities.Session;
import com.events.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Admin implements Serializable {

    private UUID id;

    private String name;

    private Date hireDate;

    private int age;

    public Admin(String name, Date hireDate, int age) {
        this.age = age;
        this.name = name;
        this.hireDate = hireDate;
        id = UUID.randomUUID();
    }

    public Hall createHall(int capacity) {
        return new Hall(capacity);
    }

    public Session createSession(Film film, Hall hall, Date time, double cost) {
        return new Session(film, hall, time, cost);
    }

    public Film createFilm(String title, String description, Rating rating , Date startDate) {
        return new Film(title, description, rating , startDate);
    }

    public Event createEvent(String condition) {
        return new Event(condition);
    }
}