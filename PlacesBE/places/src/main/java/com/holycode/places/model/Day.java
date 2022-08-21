package com.holycode.places.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day implements Serializable {

	private static final long serialVersionUID = 1L;

	private Days day;
	private List<WorkingHour> workingHours;

	public Day(Days day) {
		this.day = day;
		workingHours = new ArrayList<>();
	}

	public Day(Days day,List<WorkingHour> workingHours) {
		super();
		this.day = day;
		this.workingHours = workingHours;
	}

	public void addWorkingHour(WorkingHour workingHour) {
		workingHours.add(workingHour);
	}

	public List<WorkingHour> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHour> workingHours) {
		this.workingHours = workingHours;
	}

	public Days getDay() {
		return day;
	}

	public void setDay(Days day) {
		this.day = day;
	}

	@Override
	public String toString() {
		String s = day.toString() + " ";
		for (WorkingHour workingHour : workingHours) {
			s = s + workingHour;
		}
		return s;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		return Objects.equals(workingHours, other.workingHours);
	}
	
	

}
