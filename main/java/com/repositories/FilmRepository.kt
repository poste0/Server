package com.repositories

import com.entities.Film
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface FilmRepository : CrudRepository<Film, Long> {
    fun findAllByTitleStartingWith(pageable: Pageable, part: String): List<Film>

    @Query("select film from Film film order by film.rating desc")
    fun findAllOrderByRatingDesc(pageable: Pageable): List<Film>
}
