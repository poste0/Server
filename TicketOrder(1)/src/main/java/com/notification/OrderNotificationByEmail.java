package com.notification;
import com.entities.Order;
import com.repositories.FilmRepository;

import java.io.FileNotFoundException;

public class OrderNotificationByEmail extends AbstractNotificationByEmail
{
	public OrderNotificationByEmail()
	{
	}

	protected Order order;



	public OrderNotificationByEmail(Order order , String email){
		this.order = order;
		this.email = email;
	}

	@Override
	public void notifyUser() throws FileNotFoundException
	{
		message = "Hello. You have an order : ";
		subject = "Order : ";
		subject += order.getSession().getFilm().getTitle();
		message += subject;
		emailService.sendSimpleMessage(email , subject , message);
	}
}