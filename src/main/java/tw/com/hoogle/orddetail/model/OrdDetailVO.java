package tw.com.hoogle.orddetail.model;

public class OrdDetailVO implements java.io.Serializable{
	
	private Integer orddetailId;
	private Integer ordId;
	private Integer roomAuto;
	private Integer roomNumber;
	private Integer nonreserved;
	private String roomName;
	
	
	
	@Override
	public String toString() {
//		return  "["+nonreserved+"]" ;
		return  ""+nonreserved+"" ;
	}
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getOrddetailId() {
		return orddetailId;
	}
	public void setOrddetailId(Integer orddetailId) {
		this.orddetailId = orddetailId;
	}
	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	public Integer getRoomAuto() {
		return roomAuto;
	}
	public void setRoomAuto(Integer roomAuto) {
		this.roomAuto = roomAuto;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getNonreserved() {
		return nonreserved;
	}
	public void setNonreserved(Integer nonreserved) {
		this.nonreserved = nonreserved;
	}

	
	
	
	
	
}
