package com.enities;
/**
 * There will be the rating system.
 */
public class Rating {
	public Rating() {
		sum = 0;
		count = 0;
	}

	private double value;
	private int count;
	private double sum;

	@Override
	public String toString(){
		return String.valueOf(value) + "\n";
	}

	public void upgrade(double value){
		if(value > 10 || value < 0) throw new IllegalArgumentException("The rate cannot be more than 10 and less than 0");
		count++;
		sum += value;
		this.value = sum/count;


	}
}