package com.repositories

import com.entities.Booking
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*

@Repository
@Transactional
interface BookingRepository : CrudRepository<Booking, Long> {

    @Query("select ord from Booking ord where ord.visitor = :visitorId")
    fun findOrderByVisitor(pageable: Pageable, visitorId: Long): List<Booking>

    @Query(value = "SELECT hall.capacity - sum(booking.tickets_amount) AS available FROM hall , booking  " +
            "WHERE  (hall.id , booking.session_id) IN " +
            "(SELECT session.hall_id , session.id  FROM Session , Film  " +
            "WHERE session.film_id = :film_id AND session.time = :time)  " + //mb bad comparing
            "GROUP BY hall.id , booking.session_id", nativeQuery = true)
    fun findAvailableTicketsCount(
            @Param("film_id") filmId: Long,
            @Param("time") dateTime: Date = Date.from(Instant.now())): Long

}
