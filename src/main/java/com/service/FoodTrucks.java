package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.redis.core.RedisTemplate;

import com.model.FoodShop;
import com.model.FoodTruck;

@Service
public class FoodTrucks {
	
	private String name = "foodTrucks";
	
	@Value("${localFile.path}")
	private String csvFile;
	
	@Autowired
	private DataLoader loader;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	
	public List<FoodTruck> loadFoodTrucks() {
		List<FoodTruck> foodTrucks = loader.loadFoodTrucks(csvFile);
		redisTemplate.opsForValue().set(name, foodTrucks);
		return foodTrucks;
	}
	
	public List<FoodTruck> getAllFoodTrucks(){
		return	redisTemplate.opsForValue().get(name);
	}
	
	public List<FoodTruck>  getFoodTruckByFood(String foodName){
		List<FoodTruck>  re = new ArrayList<>();
		if(foodName == null) {
			return re;
		}
		for(FoodTruck ft : getAllFoodTrucks()) {
			String foodItems = ft.getFoodItems();
			if(foodItems != null && foodItems.toLowerCase().contains(foodName.trim().toLowerCase())) {
				re.add(ft);
			}
		}
		return re;
	}
	
	public List<FoodShop> getFoodShops(List<FoodTruck> list){
		List<FoodShop> re = new ArrayList<>(list.size());
		for(FoodTruck ft : list) {
			re.add(new FoodShop(ft));
		}
		return re;
	}
	
	public void loadGeoTruckData(List<FoodTruck> list, String typeId) {
		List<RedisGeoCommands.GeoLocation<String>> locations = new ArrayList<>(list.size());
		String key = RedisConstants.SHOP_GEO_KEY + typeId;
		for(FoodTruck ft : list) {
			locations.add(new RedisGeoCommands.GeoLocation<>(ft.getApplicant(), new Point(ft.getLatitude(),ft.getLongitude())) )	;	
		}
		redisTemplate.opsForGeo().add(key,locations);
	}
	
	public List<FoodShop> queryFoodShop(String typeId, Integer distance, Double x, Double y,int limit){
		GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
				.search(
						typeId,
						GeoReference.fromCoordinate(x,y),
						new Distance(distance == 0? 5000:distance),
						RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeDistance().limit(limit)
				  );
		if(results == null) {
			return null;
		}
		List<GeoResults<RedisGeoCommands.GeoLocation<String>>> list = results.getContent();
		List<FoodShop> re = new ArrayList<>(list.size());
		for(GeoResults<RedisGeoCommands.GeoLocation<String>> ret: list) {
			String name = ret.getContent().getName();
			FoodShop fs = getFoodTruckByApplicant(name);
			fs.setDistance(ret.getDistance().getValue());
			re.add(fs);
		}
		return re;
	}
	
	private FoodShop getFoodTruckByApplicant(String name) {
		for(FoodTruck ft : foodTrucks) {
			String applicant = ft.getApplicant();
			if(applicant.trim().equalsIgnoreCase(name.trim())) {
				return new FoodShop(ft);
			}
		}
		return null;	
	}
	
}
