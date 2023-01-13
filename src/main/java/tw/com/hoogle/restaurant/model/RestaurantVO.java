package tw.com.hoogle.restaurant.model;



public class RestaurantVO implements java.io.Serializable{
	private Integer restaurantId;
	private String restaurantName;
	private String restaurantDes;
	private String restaurantType;
	private Integer restaurantPrice;
	private byte[] restaurantPic;
	private String restaurantAddress;
	private String restaurantPhone;
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantDes() {
		return restaurantDes;
	}
	public void setRestaurantDes(String restaurantDes) {
		this.restaurantDes = restaurantDes;
	}
	public String getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}
	public Integer getRestaurantPrice() {
		return restaurantPrice;
	}
	public void setRestaurantPrice(Integer restaurantPrice) {
		this.restaurantPrice = restaurantPrice;
	}
	public byte[] getRestaurantPic() {
		return restaurantPic;
	}
	public void setRestaurantPic(byte[] restaurantPic) {
		this.restaurantPic = restaurantPic;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getRestaurantPhone() {
		return restaurantPhone;
	}
	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}
	

}
