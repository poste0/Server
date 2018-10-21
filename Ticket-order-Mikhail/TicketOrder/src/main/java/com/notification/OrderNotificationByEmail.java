package com.notification;
import com.entities.Order;
import com.repositories.FilmRepository;

public class OrderNotificationByEmail extends AbstractNotificationByEmail
{
	public OrderNotificationByEmail()
	{
	}

	private Order order;



	public OrderNotificationByEmail(Order order , String email){
		this.order = order;
		this.email = email;
	}

	@Override
	public void notifyUser() {
		message = "Hello. You have an order : ";
		subject = "Order : ";
		subject += order.getSession().getFilm().getName();
		message += subject;
		emailService.sendSimpleMessage(email , subject , message);
	}
}