package com.holycode.places.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkingSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<WorkingHourGroup> workingHoursGroups;

	public WorkingSchedule(List<WorkingHourGroup> workingHourGroups) {
		super();
		this.workingHoursGroups = workingHourGroups;
	}

	public WorkingSchedule() {
		workingHoursGroups = new ArrayList<>();
	}

	public List<WorkingHourGroup> getWorkingHourGroups() {
		return workingHoursGroups;
	}

	public void setWorkingHourGroups(List<WorkingHourGroup> workingHourGroups) {
		this.workingHoursGroups = workingHourGroups;
	}
	
	public void addWorkingHoursGroup (WorkingHourGroup whg) {
		workingHoursGroups.add(whg);
	}
	
	public List<WorkingHour> getWorkingHourForDay(String day) {
		for (WorkingHourGroup workingHourGroup : workingHoursGroups) {
			if(workingHourGroup.getWorkingHourFromDay(day) != null) {
				return workingHourGroup.getWorkingHourFromDay(day);
			}
		}
		return List.of();
	}
	
	public LocalDateTime getNextOpeningHour(LocalDateTime currentDateTime) {
		String dayOfWeek = currentDateTime.getDayOfWeek().toString();
		LocalTime currentTime = currentDateTime.toLocalTime();
		
		
		
		for (WorkingHourGroup workingHourGroup : workingHoursGroups) {
			if(workingHourGroup.isDayPartOfInterval(dayOfWeek)) {
				for (WorkingHour wh : workingHourGroup.getWorkingHours()) {
					if(wh.getStartHour().isAfter(currentTime)) {
						return currentDateTime.with(wh.getStartHour());
					}
				}
				
			}
		}
		return LocalDateTime.now();
		
	} 
	
	
}
