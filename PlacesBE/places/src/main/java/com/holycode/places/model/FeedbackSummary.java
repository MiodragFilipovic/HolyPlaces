package com.holycode.places.model;

import java.io.Serializable;

public class FeedbackSummary implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private Integer ratingCount;
	private Double averageRating;
	public Integer getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	
	
}
