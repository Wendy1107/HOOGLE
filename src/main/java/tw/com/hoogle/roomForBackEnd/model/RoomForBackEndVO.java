package tw.com.hoogle.roomForBackEnd.model;

public class RoomForBackEndVO implements java.io.Serializable{
	private Integer hotelId;
	private String hotelName;
	private Integer roomAuto;
	private String roomName;
	private Integer roomPrice;
	private Integer roomTotal;
	private Integer nonreserved;
	
	
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public Integer getRoomAuto() {
		return roomAuto;
	}
	public void setRoomAuto(Integer roomAuto) {
		this.roomAuto = roomAuto;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getRoomTotal() {
		return roomTotal;
	}
	public void setRoomTotal(Integer roomTotal) {
		this.roomTotal = roomTotal;
	}
	public Integer getNonreserved() {
		return nonreserved;
	}
	public void setNonreserved(Integer nonreserved) {
		this.nonreserved = nonreserved;
	}
	
		
}


