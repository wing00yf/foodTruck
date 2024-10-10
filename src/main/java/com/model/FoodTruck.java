package com.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodTruck {
	private String applicant;
	private String locationDescription;
	private String address;
	private String status;
	private String foodItems;
	private Double x;
	private Double y;
	private Double latitude;
	private Double longitude;
	
	private List<FoodItem> foodList;
	
}
