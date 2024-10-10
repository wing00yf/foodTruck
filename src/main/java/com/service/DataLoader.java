package com.service;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.model.*;

@Service
public class DataLoader {
	@Value("${localFile.path}")
	private String csvFile;
	
	
	public List<FoodTruck> loadFoodTrucks(String csvFile) throws Exception {
        List<FoodTruck> trucks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                FoodTruck truck = new FoodTruck();
                truck.setApplicant(line[1]);
                truck.setLocationDescription(line[4]);
                truck.setAddress(line[5]);
                truck.setStatus(line[10]);
                truck.setFoodItems(line[11]);
                List<FoodItem> foodList = getFoodItems(line[11]);
                truck.setFoodList(foodList);
                truck.setX(Double.parseDouble(line[12]));
                truck.setY(Double.parseDouble(line[13]));
                truck.setLatitude(Double.parseDouble(line[14]));
                truck.setLongitude(Double.parseDouble(line[15]));
                trucks.add(truck);
            }
        }
        return trucks;
    }
	
	private List<FoodItem> getFoodItems(String items){
		String delimiter = ":";
		List<FoodItem> itemlist = new ArrayList<FoodItem>();
		int index = items.indexOf(delimiter);
		if(index != -1) {
			String[] parts = items.split(delimiter);
			for(String p: parts) {
				FoodItem fi = new FoodItem();
				fi.setName(p.trim().toLowerCase());
				itemlist.add(fi);
			}
		}else {
			FoodItem fi = new FoodItem();
			fi.setName(items.trim().toLowerCase());
			itemlist.add(fi);
		}
		return itemlist;
	}

	
}
