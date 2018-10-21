package com.events.criterias.age;

import com.entities.Order;
import com.events.criterias.Criteria;

public class ageMoreCriteria implements Criteria {
	private ageMoreCriteria() {
	}

	private static Criteria instance = null;

	@Override
	public boolean check(Object object , Order order) {
		int age = (int)object;
		return order.getVisitor().getAge() > age;

	}

	public static Criteria getInstance(){
		return instance == null ? new ageMoreCriteria() : instance;
	}
}