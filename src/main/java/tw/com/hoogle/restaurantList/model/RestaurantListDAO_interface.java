package tw.com.hoogle.restaurantList.model;

import java.util.List;

public interface RestaurantListDAO_interface {
	
	public void insert(RestaurantListVO restaurantListVO);
	public void update(RestaurantListVO restaurantListVO);
	public void delete(Integer restaurantListId);
	public RestaurantListVO findByPrimaryKey(Integer restaurantListId);
	public List<RestaurantListVO> getAll();

}
