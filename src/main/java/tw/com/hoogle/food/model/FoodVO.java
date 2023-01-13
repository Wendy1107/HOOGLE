package tw.com.hoogle.food.model;



public class FoodVO implements java.io.Serializable{
	private Integer foodPicid;
	private Integer restaurantId;
	private byte[] foodPic;
	private String foodName;
	
	public Integer getFoodPicid() {
		return foodPicid;
	}
	public void setFoodPicid(Integer foodPicid) {		
		this.foodPicid = foodPicid;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public byte[] getFoodPic() {
		return foodPic;
	}
	public void setFoodPic(byte[] foodPic) {
		this.foodPic = foodPic;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	

}
