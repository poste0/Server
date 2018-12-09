package com.services

import com.entities.Film
import com.entities.Rating
import com.repositories.FilmRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class FilmService(
        private val filmRepository: FilmRepository,
        private val visitorService: VisitorService
) {

    fun findByTitlePart(titlePart: String, page: Int, size: Int): List<Film> {
        return filmRepository.findAllByTitleStartingWith(PageRequest.of(page - 1, size), titlePart)
    }

    fun list(page: Int, size: Int): List<Film> {
        return filmRepository.findAllOrderByRatingDesc(PageRequest.of(page - 1, size))
    }

    @Throws(EntityNotFoundException::class)
    fun upgradeRating(filmId: Long, rating: Rating): Film {
        val film = filmRepository.findById(filmId).orElseThrow {
            EntityNotFoundException(
                    "Film with id : $filmId does`t found"
            )
        }
        film.getRating().upgrade(rating.getValue())
        return filmRepository.save(film)
    }
}
