package com.events;

import com.entities.Order;
import com.events.criterias.Criteria;
import com.events.criterias.age.*;
import com.events.criterias.boughtTickets.boughtTicketsMoreCriteria;
import com.events.criterias.boughtTickets.boughtTicketsMoreOrEqualsCriteria;
import com.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Event works before every booking. If there are events created and the user is valid for them then the ticket cost is reducing.
 * @see criterias There are all criterias for filtering users
 */
public class Event {
	/**
	 * The command which come here from UI Where an admin selected criterias for the event.
	 * The command pattern: FieldCompare, digit , ... , reduce , ! or %
	 * ! - reducing by the digit
	 * % - reducing by percents
	 * Example : AGE< 40,500,!
	 */
	private String condition;

	private UUID id;

	private static EventRepository repository;

	@Autowired
	public void setRepository(EventRepository repository){
		Event.repository = repository;}
	private static List<Event> events = new LinkedList<>();

	public Event(String condition){
		this.condition = condition;
		id = UUID.randomUUID();
	}

	/**
	 * Calculates the cost of the ticket.
	 * @param order User's order that must be checked before this finish
	 * @return The ticket cost
	 */
	public double reduce(Order order){
		 if(parse(order)){
		 	double result = order.getSession().getCost();
		 	String[] parsed = condition.split(",");
		 	if(Double.parseDouble(parsed[parsed.length - 2]) < 0){
		 		throw new IllegalArgumentException("cannot be less than 0");
			}
		 	switch(parsed[parsed.length - 1]){
				case "!":
					result -= Double.parseDouble(parsed[parsed.length - 2]);
					break;
				case "%":
					if(Double.parseDouble(parsed[parsed.length - 2]) > 100) throw new IllegalArgumentException("Cannot be more than 100 %");
					result -= (result*Double.parseDouble(parsed[parsed.length - 2]))/100;
					break;
					default:
						throw new IllegalArgumentException("The command is created wrong. Find the error in creating of the command.");
			}
			return result;
		 }
		 return order.getSession().getCost();
	}

	/**
	 * Map for containing criterias as text and criterias as objects those can check the criterias
	 */
	private static final Map<String , Criteria> conditions = new HashMap<String, Criteria>(){{
		put("AGE<" , ageLessCriteria.getInstance());
		put("AGE>" , ageMoreCriteria.getInstance());
		put("AGE==" , ageEqualsCriteria.getInstance());
		put("AGE!=" , ageNotEqualsCriteria.getInstance());
		put("AGE<=" , ageLessOrEqualsCriteria.getInstance());
		put("AGE>=" , ageMoreOrEqualsCriteria.getInstance());
		put("TOTAL>" , boughtTicketsMoreCriteria.getInstance());
		put("TOTAL>=" , boughtTicketsMoreOrEqualsCriteria.getInstance());
	}};

	/**
	 * Parses the condition and checks all criterias in the system. If there is something available the return true. Otherwise returns false
	 * @param order User's order
	 * @return true if all of the criterias return true. Otherwise returns false
	 */
	private boolean parse(Order order){
		String[] parsed = condition.split(",");
		String[] subCondition;
		Criteria criteria;
		boolean status = true;
		subCondition = parsed[0].split(" ");
		criteria = conditions.get(subCondition[0]);
		if(criteria != null){
			status = criteria.check(Long.parseLong(subCondition[1]) , order);
		}
		else{
			throw new IllegalArgumentException("The command is created wrong. Find the error in creating of the command.");
		}
		if(!status) return false;
		return true;
	}

	public static List<Event> findAllEvents(){
		return events;
	}

	public static void addEvent(Event event){
		events.add(event);
	}


}