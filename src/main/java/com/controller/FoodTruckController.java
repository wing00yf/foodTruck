package com.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.DataLoader;
import com.service.FoodTrucks;
import com.model.*;
@RestController
@RequestMapping("/foodTrucks")
public class FoodTruckController {

	@Autowired
	private FoodTrucks foodTrucks;

	/*
	 * load all food truck information from csv
	 * 
	 */
	@GetMapping("/loadFoodTrucks")
	public List<FoodTruck> getAllFoodTrucks() {
		return foodTrucks.loadFoodTrucks();
	}  

	/*
	 * show food shop information
	 * 
	 */
    @GetMapping("/getAllFoodShops")
	public List<FoodShop> getAllFoodShops() {
    	List<FoodTruck>re = foodTrucks.getAllFoodTrucks();
    	return foodTrucks.getFoodShops(re);
	}
	
    /*
	 *  query food shops by different food
	 * 
	 */
    @GetMapping("/getFoodShopsByFood")
    public List<FoodShop> getShopsByFood(String foodName){
    	List<FoodTruck>re =  foodTrucks.getFoodTruckByFood(foodName);
    	return foodTrucks.getFoodShops(re);
    }

    /*
	 *  query food shops by distance from your location
	 *  
	 *  @distance: how far from you
	 *  @X: your location's latitude
	 *  @y: your location's longitude
	 *  @limit: how many shop will list
	 *  
	 * 
	 */
    @GetMapping("/getFoodShopsByDistance")
    public List<FoodShop> getAllFoodShopByDistance(Integer distance, Double x, Double y,int limit){
    	String typeId = "all";
    	foodTrucks.loadGeoTruckData(foodTrucks.getAllFoodTrucks(), typeId);
    	return foodTrucks.queryFoodShop(typeId, distance, x, y, limit);
    }
    
    /*
	 *  query food shops by food & distance from your location
	 *  
	 *  @foodName: food item's name for the food shop have
	 *  @distance: how far from you
	 *  @X: your location's latitude
	 *  @y: your location's longitude
	 *  @limit: how many shop will list
	 *  
	 * 
	 */
    @GetMapping("/getFoodShopsByFoodAndDistance")
    public List<FoodShop> getFoodShopsByFoodAndDistance(String foodName, String typeId, Integer distance, Double x, Double y,int limit){
    	foodTrucks.loadGeoTruckData(foodTrucks.getFoodTruckByFood(foodName), foodName);
    	return foodTrucks.queryFoodShop(foodName, distance, x, y, limit);
    }
}
