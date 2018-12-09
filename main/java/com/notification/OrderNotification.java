package com.notification;

import com.entities.Booking;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class OrderNotification extends AbstractNotificationByEmail {

    public OrderNotification() {
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    protected Booking booking;


    @Override
    public void notifyUser() throws FileNotFoundException {
        message = "Hello. You have an booking : ";
        subject = "Booking : ";
        subject += booking.getSession().getFilm().getTitle();
        message += subject;
        emailService.sendSimpleMessage(email, subject, message);
    }
}