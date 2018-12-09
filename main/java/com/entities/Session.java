package com.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Getter(value = AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "session")
    private List<Booking> bookings;

    public Double getCost() {
        return cost;
    }

    @Basic
    @Column
    private Double cost;

    @Basic
    @Column(name = "remaining_capacity")

    private Integer remainingCapacity;

    public Session(Film film, Hall hall, Date time, Double cost, Integer remainingCapacity) {
        this.film = film;
        this.hall = hall;
        this.time = time;
        this.cost = cost;
        this.remainingCapacity = remainingCapacity;
    }

    public void minusCapacity(int value) {
        this.remainingCapacity -= value;
    }

    public Integer getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(Integer remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }
}