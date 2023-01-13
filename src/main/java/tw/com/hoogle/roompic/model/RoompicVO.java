package tw.com.hoogle.roompic.model;

public class RoompicVO implements java.io.Serializable {
	private Integer roompicId;
	private Integer roomAuto;
	private String roomType;
	private byte[] roompicPic;

	public Integer getRoompicId() {
		return roompicId;
	}

	public void setRoompicId(Integer roompicId) {
		this.roompicId = roompicId;
	}

	public Integer getRoomAuto() {
		return roomAuto;
	}

	public void setRoomAuto(Integer roomAuto) {
		this.roomAuto = roomAuto;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public byte[] getRoompicPic() {
		return roompicPic;
	}

	public void setRoompicPic(byte[] roompicPic) {
		this.roompicPic = roompicPic;

	}

}
