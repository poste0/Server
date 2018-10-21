package com.enities.users;
import com.enities.*;

import java.util.UUID;

public class Visitor {
	public Visitor() {
	}

	private UUID id;

	private String name;

	private String email;

	private int age;

	private int boughtTickets;

	public Visitor(String name , String email , int age){
		this.name = name;
		this.age = age;
		this.email = email;
		id = UUID.randomUUID();
		boughtTickets = 0;
	}

	public void createOrder(Session session, int ticketsAmount, Type type) throws Exception {
		Order order = type == Type.BOOKING ? new Booking(session , this , ticketsAmount) : new Purchasing(session , this , ticketsAmount);
		//TODO Insert into the database.
	}

	@Override
	public String toString(){
		return "Name : " + name + "\n" + "Email : " + email + "\n" + "BoughtTickets : " + boughtTickets;
	}

	public void buyNewTickets(int count){
		boughtTickets += count;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public int getBoughtTickets()
	{
		return boughtTickets;
	}
	
	public UUID getId()
	{
		return id;
	}
}