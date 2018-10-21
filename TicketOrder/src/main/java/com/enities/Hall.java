package com.enities;

import java.util.UUID;

public class Hall {
	public Hall() {
	}

	private UUID id;

	private int capacity;

	public Hall(int capacity) {
		this.capacity = capacity;
		id = UUID.randomUUID();
	}

	@Override
	public String toString(){
		return "Capacity : " + String.valueOf(capacity) + "\n";
	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public UUID getId()
	{
		return id;
	}
	
}