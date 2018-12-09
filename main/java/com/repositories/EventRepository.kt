package com.repositories

import com.entities.Event
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*

@Repository
@Transactional
interface EventRepository : CrudRepository<Event, Long> {
    @Cacheable("events")
    fun findAllByEndDateAfter(date: Date = Date.from(Instant.now())): MutableList<Event>
}
