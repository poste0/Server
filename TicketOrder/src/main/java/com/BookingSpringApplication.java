package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.entities")
@EnableJpaRepositories("com.repositories")
@EnableAutoConfiguration(
        exclude = {
                HibernateJpaAutoConfiguration.class
        }
)
public class BookingSpringApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BookingSpringApplication.class);
    }
}