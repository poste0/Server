package com.events.criterias;

import com.entities.*;
import com.entities.users.Visitor;
import com.events.criterias.age.*;
import com.events.criterias.boughtTickets.boughtTicketsMoreCriteria;
import com.events.criterias.boughtTickets.boughtTicketsMoreOrEqualsCriteria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CriteriaTest {

    private Session session;
    private Film film;
    private Hall hall;
    private Visitor visitor;
    private int ticketsAmount;
    private Rating rating;
    private Criteria criteria;
    private static final int AGE = 42;

    @BeforeAll
    void init() {
        rating = new Rating();
        rating.upgrade(5);
        rating.upgrade(2);
        film = new Film("test", "test", rating);
        hall = new Hall(40);
        visitor = new Visitor("test", "test", AGE);
        ticketsAmount = 20;
        session = new Session(film, hall, Date.valueOf("2018-09-27"), 2000d);
    }

    @Test
    void check() throws Exception {
        Object o = AGE;
        Object o1 = AGE - 1;
        Order order = new Booking(session, visitor, ticketsAmount);


        criteria = ageEqualsCriteria.getInstance();
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));

        criteria = ageLessCriteria.getInstance();
        o = AGE + 1;
        o1 = AGE;
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));

        criteria = ageLessOrEqualsCriteria.getInstance();
        o = AGE + 1;
        o1 = AGE - 1;
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));
        o = AGE;
        assertTrue(criteria.check(o, order));

        criteria = ageMoreCriteria.getInstance();
        o = AGE - 1;
        o1 = AGE;
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));

        criteria = ageMoreOrEqualsCriteria.getInstance();
        o = AGE - 1;
        o1 = AGE + 1;
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));
        o = AGE;
        assertTrue(criteria.check(o, order));

        criteria = ageNotEqualsCriteria.getInstance();
        o = AGE + 1;
        o1 = AGE;
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));

        criteria = boughtTicketsMoreCriteria.getInstance();
        o = order.getVisitor().getBoughtTickets() - 1;
        o1 = order.getVisitor().getBoughtTickets();
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));

        criteria = boughtTicketsMoreOrEqualsCriteria.getInstance();
        o = order.getVisitor().getBoughtTickets() - 1;
        o1 = order.getVisitor().getBoughtTickets() + 1;
        assertTrue(criteria.check(o, order));
        assertFalse(criteria.check(o1, order));
        o = order.getVisitor().getBoughtTickets();
        assertTrue(criteria.check(o, order));

    }
}