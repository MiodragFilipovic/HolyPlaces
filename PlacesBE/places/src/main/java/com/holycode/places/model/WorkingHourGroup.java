package com.holycode.places.model;

import java.io.Serializable;
import java.util.List;

public class WorkingHourGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	private String interval;
	private List<WorkingHour> workingHours;

	public WorkingHourGroup(String interval, List<WorkingHour> workingHours) {
		super();
		this.interval = interval;
		this.workingHours = workingHours;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public List<WorkingHour> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHour> workingHours) {
		this.workingHours = workingHours;
	}

	public void addDayToInterval(String dayName) {
		interval = interval + " - " + dayName;
	}

	public void trimInterval() {
		String[] intervals = interval.split("-");
		if (intervals.length > 1) {
			interval = intervals[0] + "-" + intervals[intervals.length - 1];
		}
	}

	public List<WorkingHour> getWorkingHourFromDay(String day) {
		if (isDayPartOfInterval(day))
			return getWorkingHours();
		return null;

	}

	public boolean isDayPartOfInterval(String day) {
		if (interval.contains(day.toLowerCase())) {
			return true;
		}
		return false;
	}

}
