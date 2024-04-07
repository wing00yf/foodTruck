package com.service;
import java.util.List;
import com.opencsv.CSVReader;
import com.model.*;

public class DataLoader {
	public List<FoodTruck> loadFoodTrucks(String csvFile) throws Exception {
        List<FoodTruck> trucks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                FoodTruck truck = new FoodTruck();
                truck.setName(line[0]);
                truck.setLocation(line[1]);
                truck.setFoodType(line[2]);
                trucks.add(truck);
            }
        }
        return trucks;
    }


}
