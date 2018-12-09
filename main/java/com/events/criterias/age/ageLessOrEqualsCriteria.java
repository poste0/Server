package com.events.criterias.age;

import com.entities.Booking;
import com.events.criterias.Criteria;

public class ageLessOrEqualsCriteria implements Criteria {
	private ageLessOrEqualsCriteria() {
	}

	private static Criteria instance = null;

	@Override
	public boolean check(Object object, Booking booking) {
		int age = (int) object;
		return booking.getVisitor().getAge() <= age;
	}

	public static Criteria getInstance(){
		return instance == null ? new ageLessOrEqualsCriteria() : instance;
	}
}