package com.enities.users;

import com.enities.Film;
import com.enities.Hall;
import com.enities.Rating;
import com.enities.Session;
import com.events.Event;

import java.util.Date;
import java.util.UUID;

public class Admin {
	public Admin() {
	}

	private UUID id;

	private String name;

	private Date hireDate;

	private int age;

	public Admin(String name , Date hireDate , int age){
		this.age = age;
		this.name = name;
		this.hireDate = hireDate;
		id = UUID.randomUUID();
	}

	public Hall createHall(int capacity){
		return new Hall(capacity);
	}

	public Session createSession(Film film , Hall hall , java.sql.Date time , double cost){
		return new Session(film , hall , time , cost);
	}

	public Film createFilm(String title , String description , Rating rating){
		return new Film(title , description , rating);
	}

	public Event createEvent(String condition){
		return new Event(condition);
	}
}