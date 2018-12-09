package com.repositories

import com.entities.Session
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Repository
@Transactional
interface SessionRepository : CrudRepository<Session, Long> {

    fun findAllByTimeAfterOrderByTime(pageable: Pageable, date: Date = Date.from(Instant.now())): List<Session>
    @Query("select '*' from Session session where session.film = :filmId")
    fun findAllByFilm(@Param(value = "filmId") filmId: Long): List<Session>

    fun deleteAllByTimeBefore(date: LocalDateTime = LocalDateTime.now())
}
