package com.services

import com.entities.Session
import com.repositories.SessionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class SessionService(private val sessionRepository: SessionRepository) {

    @Throws(EntityNotFoundException::class)
    fun findById(id: Long): Session {
        return sessionRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Session with id : $id does`t found")
        }
    }

    fun list(page: Int, size: Int) : List<Session> {
        return sessionRepository.findAllByTimeAfterOrderByTime(PageRequest.of(page - 1, size))
    }

    fun save(session: Session): Session {
        sessionRepository.save(session)
        return session
    }
}