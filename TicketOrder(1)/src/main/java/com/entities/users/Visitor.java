package com.entities.users;

import com.entities.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table
@NoArgsConstructor
public class Visitor implements Serializable {

    @Id
    @Column(name = "visitor_id")
    private UUID id;

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
    private List<Order> sessions;

    public Visitor(String name, String email, int age) {
        this.name = name;
        this.age = age;
        this.email = email;
        id = UUID.randomUUID();
        boughtTickets = 0;
    }

    public void createOrder(Session session, int ticketsAmount, Type type) throws Exception {
        Order order = type == Type.BOOKING ? new Booking(session, this, ticketsAmount) : new Purchasing(session, this, ticketsAmount);
        //TODO Insert into the database.
    }

    public void buyNewTickets(int count) {
        boughtTickets += count;
    }
}