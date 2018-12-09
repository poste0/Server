package com.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
public class Visitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id")
    private Long id;

    @Basic
    @Column
    private String name;

    @Basic
    @Column
    private String email;

    @Basic
    @Column
    private Integer age;

    @Basic
    @Column
    private Integer boughtTickets;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "visitor")
    private List<Booking> sessions;

    public Visitor(String name, String email, int age) {
        this.name = name;
        this.age = age;
        this.email = email;
        boughtTickets = 0;
    }

    public Booking createOrder(Session session, int ticketsAmount) throws Exception {
        return new Booking(session, this, ticketsAmount);
    }

    public void buyNewTickets(int count) {
        boughtTickets += count;
    }

    public String getEmail() {
        return email;
    }
}