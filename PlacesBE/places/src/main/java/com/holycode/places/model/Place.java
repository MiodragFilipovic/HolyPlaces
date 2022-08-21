package com.holycode.places.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Place implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String displayedWhat;
	private String displayedWhere;
	private FeedbackSummary feedbackSummary;
	private Address address;
	private List<WorkingHour> days;
	private List<Category> categories;
	
	public Place() {
		days = new ArrayList<>();
		categories = new ArrayList<>();
	}
	
	

}
