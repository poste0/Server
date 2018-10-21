package com;

import com.enities.*;
import com.enities.users.Admin;
import com.enities.users.Visitor;
import com.events.Event;

import java.sql.Date;

public class Main
{
	public Main()
	{
	}

	public static void main(String[] args) throws Exception
	{
		Visitor visitor = new Visitor("Max" , "Max@gmail.com" , 35);
		Film film = new Film("Super Film" , "Come on" , new Rating());
		film.getRating().upgrade(9);
		film.getRating().upgrade(4);
		Hall hall = new Hall(50);

		Session session = new Session(film , hall , Date.valueOf("2018-09-27") , 1500);
		Admin admin = new Admin("Alex" , Date.valueOf("2000-01-01") , 31);
		Event event = admin.createEvent("AGE!= 28,TOTAL> 4,50,%");
		Event.addEvent(event);
		Order order = new Booking(session , visitor , 5);
		System.out.println(order);
		Order order1 = new Booking(session , visitor , 4);
		System.out.println(order1);
	}
}