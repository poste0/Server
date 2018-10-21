package com.events;

import com.entities.*;
import com.entities.users.Visitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;

import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventTest {
    private Session session;
    private Film film;
    private Hall hall;
    private Visitor visitor;
    private int ticketsAmount;
    private Rating rating;
    private String conditionGood;
    private String conditionBad;
    private Event goodEvent;
    private Event badEvent;

    @BeforeAll
    void init() {
        rating = new Rating();
        rating.upgrade(5);
        rating.upgrade(2);
        film = new Film("test", "test", rating , new Date(0));
        hall = new Hall(40);
        visitor = new Visitor("test", "test", 32);
        ticketsAmount = 20;
        session = new Session(film, hall, Date.valueOf("2018-09-27"), 2000d);
        conditionBad = "AGEE> 20,150,#";
        conditionGood = "AGE> 30,1500,!";
        goodEvent = new Event(conditionGood);
        badEvent = new Event(conditionBad);
    }

    @org.junit.jupiter.api.Test
    void reduce() throws Exception {
        Order order = new Booking(session, visitor, ticketsAmount);
        assertEquals(goodEvent.reduce(order), 500);
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                badEvent.reduce(order);
            }
        });
    }

}