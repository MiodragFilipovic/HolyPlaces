package com.holycode.places.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class WorkingHour implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalTime startHour;
	private LocalTime endHour;
	private Boolean open;

	public WorkingHour(LocalTime startHour, LocalTime endHour, Boolean open) {
		super();
		this.startHour = startHour;
		this.endHour = endHour;
		this.open = open;
	}

	public LocalTime getStartHour() {
		return startHour;
	}

	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}

	public LocalTime getEndHour() {
		return endHour;
	}

	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}

	public Boolean isOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "[" + startHour + "]-[" + endHour + "]-" + open;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkingHour other = (WorkingHour) obj;
		return Objects.equals(endHour, other.endHour) && Objects.equals(open, other.open)
				&& Objects.equals(startHour, other.startHour);
	}

}
