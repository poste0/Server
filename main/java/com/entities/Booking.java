package com.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Table(name = "booking")
@Entity
public class Booking {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Column(name = "tickets_amount")
    @Basic
    private Integer ticketsAmount;

    @Column
    @Basic
    private Double cost;

    public Booking(Session session, Visitor visitor, int ticketsAmount) throws Exception {
        if ((session.getRemainingCapacity() - ticketsAmount) < 0)
            throw new Exception("No free space");
        this.session = session;
        this.visitor = visitor;
        this.ticketsAmount = ticketsAmount;
        this.session.minusCapacity(ticketsAmount);
        cost = session.getCost();
        visitor.buyNewTickets(ticketsAmount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Integer getTicketsAmount() {
        return ticketsAmount;
    }

    public void setTicketsAmount(Integer ticketsAmount) {
        this.ticketsAmount = ticketsAmount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}