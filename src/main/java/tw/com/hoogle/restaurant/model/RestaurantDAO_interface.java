package tw.com.hoogle.restaurant.model;

import java.util.List;

public interface RestaurantDAO_interface {
	
	public void insert(RestaurantVO restaurantVO);
	public void update(RestaurantVO restaurantVO);
	public void delete(Integer restaurantId);
	public RestaurantVO findByPrimaryKey(Integer restaurantId);
	public List<RestaurantVO> getAll();

}
