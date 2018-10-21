package com.events;

import com.entities.Order;
import com.events.criterias.Criteria;
import com.events.criterias.age.*;
import com.events.criterias.boughtTickets.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	 * Example : AGE<,40,500,!
	 */
	private String condition;

	private static List<Event> events = new LinkedList<>();

	public Event(String condition){
		this.condition = condition;
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
		for(int i = 0 ; i < parsed.length ; i++){
			 subCondition = parsed[i].split(" ");
			 criteria = conditions.get(subCondition[0]);
			 if(criteria != null){
			 	status = criteria.check(Integer.parseInt(subCondition[1]) , order);
			 }
			 if(!status) return false;
		}
		return true;
	}

	public static List<Event> findAllEvents(){
		return events;
	}

	public static void addEvent(Event event){
		events.add(event);
	}


}