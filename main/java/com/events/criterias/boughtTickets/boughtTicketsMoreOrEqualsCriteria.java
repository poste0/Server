package com.events.criterias.boughtTickets;

import com.entities.Booking;
import com.events.criterias.Criteria;

public class boughtTicketsMoreOrEqualsCriteria implements Criteria {
	private boughtTicketsMoreOrEqualsCriteria() {
	}

	private static Criteria instance = null;


	@Override
	public boolean check(Object object, Booking booking)
	{
		int countOfBoughtTickets = (int) object;
		return booking.getVisitor().getBoughtTickets() >= countOfBoughtTickets;
	}

	public static Criteria getInstance(){return instance == null ? new boughtTicketsMoreOrEqualsCriteria() : instance;}
}