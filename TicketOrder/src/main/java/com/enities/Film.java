package com.enities;

import java.util.UUID;

public class Film {
	public Film()
	{
		title = "";
		description = "";
		rating = new Rating();
		id = UUID.randomUUID();
	}

	private UUID id;

	private String title;

	private String description;

	private Rating rating;

	public Film(String title , String description , Rating rating)
	{
		this.title = title;
		this.description = description;
		this.rating = rating;
		id = UUID.randomUUID();
	}

	@Override
	public String toString(){
		return "Title : " + title + "\n" + "Description : " + description + "\n" + "Rating : " + rating.toString();
	}

	public UUID getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Rating getRating()
	{
		return rating;
	}

	public void setRating(Rating rating)
	{
		this.rating = rating;
	}
}