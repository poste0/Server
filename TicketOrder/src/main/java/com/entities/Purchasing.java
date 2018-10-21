package com.entities;

import com.entities.users.Visitor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor
public class Purchasing extends Order implements Serializable {
    public Purchasing(Session session, Visitor visitor, int ticketsAmount) throws Exception {
        super(session, visitor, ticketsAmount);

        visitor.buyNewTickets(ticketsAmount);
    }
}