package com.enities;

import com.enities.users.Visitor;

public class Purchasing extends Order
{

	public Purchasing(){}

	public Purchasing(Session session , Visitor visitor , int ticketsAmount) throws Exception {
		super(session , visitor , ticketsAmount);
	}
}