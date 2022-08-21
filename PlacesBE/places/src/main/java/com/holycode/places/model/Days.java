package com.holycode.places.model;

public enum Days {

	MONDAY("monday"), TUESDAY("tuesday"), WEDNESDAY("wednesday"), THURSDAY("thursday"), FRIDAY("friday"),
	SATURADAY("saturday"), SUNDAY("sunday");

	public final String day;

	private Days(String day) {
		this.day = day;
	}
	
	@Override
    public String toString() {
        return day;
    }
	

}
