package com.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table
public class Session implements Serializable {

    @Id
    @Column(name = "session_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column
    @Temporal(TemporalType.DATE)
    private Date time;

    @Getter(value = AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "session")
    private List<Order> orders;

    @Transient
    private Double cost;

    @Transient
    private Integer remainingCapacity;

    public Session(Film film, Hall hall, Date time, Double cost) {
        this.film = film;
        this.hall = hall;
        this.time = time;
        this.cost = cost;// is dynamic - mb it property should be filled by another, more pragmatic way?
        remainingCapacity = hall.getCapacity();
        id = UUID.randomUUID();
    }

    public void minusCapacity(int value) {
        this.remainingCapacity -= value;
    }
}