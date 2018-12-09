package com.services

import com.entities.Booking
import com.entities.Visitor
import com.notification.AbstractNotificationByEmail
import com.notification.OrderNotification
import com.repositories.BookingRepository
import com.repositories.VisitorsRepository
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service

@Service
@ComponentScan(basePackages = ["com.notification"])
class VisitorService(
        private val visitorsRepository: VisitorsRepository,
        private val bookingRepository: BookingRepository,
        private val sessionService: SessionService,
        private val eventService: EventService,
        private val orderNotification: OrderNotification
) {

    fun findAll(): MutableIterable<Visitor> {
        return visitorsRepository.findAll()
    }

    private fun meet(visitor: Visitor): Visitor {
        visitorsRepository.save(visitor)
        return visitor
    }

    fun makeChoice(visitor: Visitor, sessionId: Long, tickets: Int): Booking {
        val session = sessionService.findById(sessionId)
        meet(visitor)
        val order: Booking = visitor.createOrder(session, tickets) as Booking
        sessionService.save(session)
        val events = eventService.findAllEvents()

        var totalCost = session.getCost()

        events.forEach {
            val temp = it.reduce(order)
            if (temp < totalCost)
                totalCost = temp
        }

        order.cost = totalCost

        orderNotification.setBooking(order)
        orderNotification.setEmail(visitor.getEmail())
        orderNotification.notifyUser()

        return bookingRepository.save(order)
    }

}