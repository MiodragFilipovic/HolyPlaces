package com.holycode.places.model;

import java.io.Serializable;
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
}
