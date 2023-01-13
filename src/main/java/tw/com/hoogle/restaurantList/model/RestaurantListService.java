package tw.com.hoogle.restaurantList.model;

import java.util.List;

public class RestaurantListService {

	
		
		private RestaurantListDAO_interface dao;
		
		public RestaurantListService() {
			dao = new RestaurantListDAO();
		}
		
		
		
		public RestaurantListVO addRestaurantList( Integer hotelId, Integer restaurantId) {

			RestaurantListVO restaurantlistVO = new RestaurantListVO();

			restaurantlistVO.setHotelId(hotelId);
			restaurantlistVO.setRestaurantId(restaurantId);
				
			dao.insert(restaurantlistVO);

			return restaurantlistVO;
		}

		

		public RestaurantListVO updateRestaurantList(Integer restaurantListId, Integer hotelId, Integer restaurantId) {

			RestaurantListVO restaurantlistVO = new RestaurantListVO();
			
			restaurantlistVO.setRestaurantListId(restaurantListId);
			restaurantlistVO.setHotelId(hotelId);
			restaurantlistVO.setRestaurantId(restaurantId);

			dao.update(restaurantlistVO);
			return restaurantlistVO;
		}

		
		
		public void deleteRestaurantList(Integer restaurantListId) {
			dao.delete(restaurantListId);
		}
		
		
			
			public RestaurantListVO getOneRestaurantList(Integer restaurantListId) {
				return dao.findByPrimaryKey(restaurantListId);
			}
		

		public List<RestaurantListVO> getAll() {
			return dao.getAll();
		}

			
		}



		