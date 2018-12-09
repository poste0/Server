package com.notification;

import com.entities.Film;
import com.services.EmailService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class PremiersNotification extends AbstractNotificationByEmail {

    private final String TITLE = "Now available : \n";

    private final EmailService emailService;
    private List<String> customers;
    private List<Film> films;
    public PremiersNotification(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser() {
        StringBuilder message;
        for (String email : customers) {
            message = new StringBuilder(TITLE);
            for (Film film : films) {
                message.append(createMessage(film));
            }
            emailService.sendSimpleMessage(email, "Premiers!", message.toString());
        }
    }

    private String createMessage(Film film) {
        return
                film.getTitle() + '\n' +
                        film.getDescription() + '\n';
    }

    public void setCustomers(List<String> customers) {
        this.customers = customers;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}