package com.entities;

import com.entities.users.Visitor;
import com.events.Event;
import com.notification.Notification;
import com.notification.OrderNotificationByEmail;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public abstract class Order {

    @Id
    @Column
    @Getter
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Column
    @Basic
    private Integer ticketsAmount;

    @Column
    @Basic
    private Double cost;

    public Order(Session session, Visitor visitor, int ticketsAmount) throws Exception {
        if ((session.getRemainingCapacity() - ticketsAmount) < 0) throw new Exception("No free space");
        id = UUID.randomUUID();
        this.session = session;
        this.visitor = visitor;
        this.ticketsAmount = ticketsAmount;
        this.session.minusCapacity(ticketsAmount);
        cost = session.getCost();
        List<Event> eventList = Event.findAllEvents();
        for (Event event : eventList) {
            cost = event.reduce(this);
            break;
        }
        //visitor.buyNewTickets(ticketsAmount);
    }
}