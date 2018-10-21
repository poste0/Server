package com.enities;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

public class Session {
	public Session() {
	}

	private UUID id;

	private Film film;

	private Hall hall;

	private Date time;

	private double cost;

	private int remaingCapacity;

	public Session(Film film , Hall hall , Date time , double cost){
		this.film = film;
		this.hall = hall;
		this.time = time;
		this.cost = cost;
		remaingCapacity = hall.getCapacity();
		id = UUID.randomUUID();
	}


	@Override
	public String toString(){
		return film.toString() + hall.toString() + "Time : " + time.toString() + "\n" + "Cost : " + cost + "\n" + "Remaining Capacity : " + remaingCapacity;
	}

	public UUID getId() {
		return id;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public Film getFilm()
	{
		return film;
	}

	public void setFilm(Film film)
	{
		this.film = film;
	}

	public Hall getHall()
	{
		return hall;
	}

	public void setHall(Hall hall)
	{
		this.hall = hall;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public int getRemaingCapacity()
	{
		return remaingCapacity;
	}

	public void setRemaingCapacity(int remaingCapacity)
	{
		this.remaingCapacity = remaingCapacity;
	}

	public void minusCapacity(int value){
		this.remaingCapacity -= value;
	}
}