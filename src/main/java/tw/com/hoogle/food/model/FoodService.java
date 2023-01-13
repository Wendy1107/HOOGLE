package tw.com.hoogle.food.model;

import java.util.List;

public class FoodService {	

		private FoodDAO_interface dao;

		public FoodService() {

			dao = new FoodJDBCDAO();
		}

		public FoodVO addFood( Integer restaurantId, byte[] foodPic, String foodName) {

			FoodVO foodVO = new FoodVO();
			
			//foodVO.setFoodPicid(foodPicid);
			foodVO.setRestaurantId(restaurantId);
			foodVO.setFoodPic(foodPic);
			foodVO.setFoodName(foodName);
			dao.insert(foodVO);

			return foodVO;
		}

		

		public FoodVO updateFood(Integer foodPicid ,Integer restaurantId, byte[] foodPic, String foodName) {


			FoodVO foodVO = new FoodVO();
			
			foodVO.setFoodPicid(foodPicid);
			foodVO.setRestaurantId(restaurantId);
			foodVO.setFoodPic(foodPic);
			foodVO.setFoodName(foodName);
			dao.update(foodVO);
			return foodVO;
			
		}
		public void deleteFood(Integer foodPicid) {
			dao.delete(foodPicid);
		}

		public FoodVO getOneFood(Integer foodPicid) {
			return dao.findByPrimaryKey(foodPicid);
		}

		public List<FoodVO> getAll() {
			return dao.getAll();
		}
	

		// 預留給 Struts 2 用的
		public void updateFood(FoodVO foodVO) {
			dao.update(foodVO);
		}
		}
//		
//		public void deleteFood(Integer foodPicid) {
//			dao.delete(foodPicid);
//		}
//		
//		public FoodVO getOneFood(Integer foodPicid) {
//			return dao.findByPrimaryKey(foodPicid);
//		}
//		
//		public List<FoodVO> getAll(){
//			return dao.getAll();
//		}
//
//	}


