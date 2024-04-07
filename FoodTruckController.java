package com.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.DataLoader;
import com.model.*;
@RestController
@RequestMapping("/foodtrucks")
public class FoodTruckController {


	       private final List<FoodTruck> foodTrucks;

	       public FoodTruckController() {
	           DataLoader loader = new DataLoader();
	           this.foodTrucks = loader.loadFoodTrucks("path/to/Mobile_Food_Facility_Permit.csv");
	       }

	       @GetMapping
	       public List<FoodTruck> getAllFoodTrucks() {
	           return foodTrucks;
	       }
	   }

}
