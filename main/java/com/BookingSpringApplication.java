package com;

import com.entities.*;
import com.notification.OrderNotification;
import com.repositories.*;
import com.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
@EntityScan("com.entities")
@EnableJpaRepositories("com.repositories")
@EnableCaching
@EnableAutoConfiguration(
        exclude = {
                HibernateJpaAutoConfiguration.class,
        }
)
class BookingSpringApplication {
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    VisitorsRepository visitorsRepository;

    @Qualifier("orderNotification")
    @Autowired
    OrderNotification notification;
    public static void main(String[] args) throws Exception {
        ApplicationContext application = SpringApplication.run(BookingSpringApplication.class);
        BookingSpringApplication springApplication = application.getBean(BookingSpringApplication.class);
        springApplication.add();
        System.out.println("A");
    }
    void add() throws Exception {
        for(int i = 0 ; i < 10 ; i++){
        Film film = new Film("New film11sad"+i , "Good11asdasd" , Date.from(Instant.now().plusMillis(10000000)) , new Rating() , Genres.COMEDY);
        Hall hall = new Hall(5);
        Session session = new Session(film , hall , Date.from(Instant.now().plusMillis(10000000)) , 100d , hall.getCapacity());
        Visitor visitor = new Visitor("test111asdasd" , "79372009578@yandex.ru" , 20);
        Booking booking = new Booking(session , visitor , 4);
        filmRepository.save(film);
        hallRepository.save(hall);
        sessionRepository.save(session);
        visitorsRepository.save(visitor);
        bookingRepository.save(booking);
        }

    }
}