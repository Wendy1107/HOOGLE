package tw.com.hoogle.restaurant.model;

import java.util.List;

public class RestaurantService {
	
	private RestaurantDAO_interface dao;
	
	public RestaurantService() {
		dao = new RestaurantDAO();
	}
	
	
	
	public RestaurantVO addRestaurant( String restaurantName,String restaurantDes,String restaurantType,Integer restaurantPrice,byte[] restaurantPic,String restaurantAddress,String restaurantPhone) {

		RestaurantVO restaurantVO = new RestaurantVO();

		restaurantVO.setRestaurantName(restaurantName);
		restaurantVO.setRestaurantDes(restaurantDes);
		restaurantVO.setRestaurantType(restaurantType);
		restaurantVO.setRestaurantPrice(restaurantPrice);
		restaurantVO.setRestaurantPic(restaurantPic);
		restaurantVO.setRestaurantAddress(restaurantAddress);
		restaurantVO.setRestaurantPhone(restaurantPhone);
		dao.insert(restaurantVO);

		return restaurantVO;
	}

	

	public RestaurantVO updateRestaurant(Integer restaurantId,String restaurantName,String restaurantDes,String restaurantType,Integer restaurantPrice,byte[] restaurantPic,String restaurantAddress,String restaurantPhone) {

		RestaurantVO restaurantVO = new RestaurantVO();
		
		restaurantVO.setRestaurantId(restaurantId);
		restaurantVO.setRestaurantName(restaurantName);
		restaurantVO.setRestaurantDes(restaurantDes);
		restaurantVO.setRestaurantType(restaurantType);
		restaurantVO.setRestaurantPrice(restaurantPrice);
		restaurantVO.setRestaurantPic(restaurantPic);
		restaurantVO.setRestaurantAddress(restaurantAddress);
		restaurantVO.setRestaurantPhone(restaurantPhone);

		dao.update(restaurantVO);
		return restaurantVO;
	}

	
	
	public void deleteRestaurant(Integer restaurantId) {
		dao.delete(restaurantId);
	}
	
	
		
		public RestaurantVO getOneRestaurant(Integer restaurantId) {
			return dao.findByPrimaryKey(restaurantId);
		}
	

	public List<RestaurantVO> getAll() {
		return dao.getAll();
	}

		
	}



	