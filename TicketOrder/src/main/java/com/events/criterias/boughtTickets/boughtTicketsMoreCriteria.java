package com.events.criterias.boughtTickets;

import com.entities.Order;
import com.events.criterias.Criteria;

public class boughtTicketsMoreCriteria implements Criteria {
	private boughtTicketsMoreCriteria() {
	}

	private static Criteria instance = null;

	@Override
	public boolean check(Object object, Order order) {
		int countOfBoughtTickets = (int) object;
		return order.getVisitor().getBoughtTickets() > countOfBoughtTickets;
	}

	public static Criteria getInstance(){return instance == null ? new boughtTicketsMoreCriteria() : instance;}
}