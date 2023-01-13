package tw.com.hoogle.hotelpic.model;


public class HotelpicVO implements java.io.Serializable{
	private Integer hotelpicId;
	private Integer hotelId;
	private byte[] hotelpicNo;
	private String hotelpicName;
	
	public Integer getHotelpicId() {
		return hotelpicId;
	}
	public void setHotelpicId(Integer hotelpicId) {
		this.hotelpicId = hotelpicId;
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public byte[] getHotelpicNo() {
		return hotelpicNo;
	}
	public void setHotelpicNo(byte[] hotelpicNo) {
		this.hotelpicNo = hotelpicNo;
	}
	public String getHotelpicName() {
		return hotelpicName;
	}
	public void setHotelpicName(String hotelpicName) {
		this.hotelpicName = hotelpicName;
	}
	
	
	

}
