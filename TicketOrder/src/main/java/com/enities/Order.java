package com.enities;
import com.enities.users.Visitor;
import com.events.Event;

import java.util.List;
import java.util.UUID;

public abstract class Order {
	private UUID id;

	private Visitor visitor;

	private Session session;

	private int ticketsAmount;

	private double cost;

	public  Order(Session session , Visitor visitor , int ticketsAmount) throws Exception
	{
		if((session.getRemaingCapacity() - ticketsAmount) < 0) throw new Exception("No free space");
		id = UUID.randomUUID();
		this.session = session;
		this.visitor = visitor;
		this.ticketsAmount = ticketsAmount;
		this.session.minusCapacity(ticketsAmount);
		cost = session.getCost();
		List<Event> eventList = Event.findAllEvents();
		for(Event event : eventList){
			cost = event.reduce(this);
			break;
		}
		visitor.buyNewTickets(ticketsAmount);
		
	}

	public Order(){}

	@Override
	public String toString(){
		return "[\n" + "User :\n" + visitor.toString() + "\n" + "Session :\n" + session.toString() + "\n" + "The final cost : " + cost +"\n]";
	}

	public int getTicketsAmount()
	{
		return ticketsAmount;
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public Visitor getVisitor()
	{
		return visitor;
	}

	public void setVisitor(Visitor visitor)
	{
		this.visitor = visitor;
	}

	public Session getSession()
	{
		return session;
	}

	public void setSession(Session session)
	{
		this.session = session;
	}
}