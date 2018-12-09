package com.entities

import org.springframework.stereotype.Component
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Component
class Admin : Serializable {

    private val _name: String = "Adam"
    private val _age: Int = 40
    private val _hireDate: LocalDateTime = LocalDateTime.of(
            2015,
            11,
            30,
            14,
            0
    )

    public fun createHall(capacity: Int): Hall {
        return Hall(capacity)
    }

    public fun createHalls(hall: MutableList<Hall>): MutableList<Hall> {
        return hall
    }

    public fun createSession(film: Film, hall: Hall, time: java.util.Date, cost: Double, remainingCapacity: Int): Session {
        return Session(film, hall, time, cost, remainingCapacity)
    }

    public fun createFilm(title: String, description: String, date: Date, rating: Rating, genre: Genres): Film {
        return Film(title, description, date, rating, genre)
    }

    public fun createFilms(films: MutableList<Film>): MutableList<Film> {
        return films
    }

    public fun createEvent(condition: String , date: Date): Event {
        return Event(condition , date)
    }

    public fun createEvents(events: MutableList<Event>): MutableList<Event> {
        return events
    }

    val name get() = _name
    val age get() = _age
    val hireDate get() = _hireDate
}