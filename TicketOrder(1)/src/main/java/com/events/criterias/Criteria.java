package com.events.criterias;

import com.entities.Order;

/**
 * Describes the event model in the system. It has just one method check that returns true or false. All of the objects those implement this interface are singletone objects.
 */
public interface Criteria {

	boolean check(Object object , Order order);

}