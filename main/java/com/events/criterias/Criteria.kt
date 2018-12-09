package com.events.criterias

import com.entities.Booking

/**
 * Describes the event model in the system. It has just one method check that returns true or false. All of the objects those implement this interface are singletone objects.
 */
interface Criteria {
    fun check(objecт: Any, booking: Booking): Boolean
}