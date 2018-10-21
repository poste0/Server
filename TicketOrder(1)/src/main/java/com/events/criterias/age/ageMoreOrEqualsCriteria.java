package com.events.criterias.age;

import com.entities.Order;
import com.events.criterias.Criteria;

public class ageMoreOrEqualsCriteria implements Criteria
{
	private ageMoreOrEqualsCriteria() {
	}

	private static Criteria instance = null;

	@Override
	public boolean check(Object object, Order order)
	{
		long age = (long)object;
		return order.getVisitor().getAge() >= age;
	}

	public static Criteria getInstance(){return instance == null ? new ageMoreOrEqualsCriteria() : instance;}
}