package com.services

import com.entities.Event
import com.repositories.EventRepository
import org.springframework.stereotype.Service

@Service
data class EventService(private val eventRepository: EventRepository) {

    fun findAllEvents(): MutableList<Event> {
        return eventRepository.findAllByEndDateAfter()
    }

    fun create(event: Event): Event {
        return eventRepository.save(event)
    }
}