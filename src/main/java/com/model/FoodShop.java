package com.model;

public class FoodShop {

	private FoodTruck ft;
	private double distance;
	private String name;
	
	public FoodShop(FoodTruck ft){
		this.ft = ft;
		this.name = ft.getApplicant();
	}
	
	public FoodShop(String name) {
		this.name = name;
	}
	
	public FoodTruck getFoodTruck() {
		return ft;
	}
	
	public String getName() {
		return name;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}
