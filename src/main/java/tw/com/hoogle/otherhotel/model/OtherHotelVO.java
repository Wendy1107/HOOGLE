package tw.com.hoogle.otherhotel.model;

public class OtherHotelVO implements java.io.Serializable {
	private Integer hotelId;
	private String hotelEmail;
	private String hotelPassword;
	private String hotelName;
	private String hotelPhone;
	private String hotelPrincipal;
	private String hotelTaxid;
	private String hotelCounty;
	private String hotelAddress;
	private String hotelType;
	private String hotelNotice;
	private String hotelQa;
	private String hotelIntroduction;
	private Integer hotelState;
	@Override
	public String toString() {
		return "OtherHotelVO [hotelId=" + hotelId + ", hotelEmail=" + hotelEmail + ", hotelPassword=" + hotelPassword
				+ ", hotelName=" + hotelName + ", hotelPhone=" + hotelPhone + ", hotelPrincipal=" + hotelPrincipal
				+ ", hotelTaxid=" + hotelTaxid + ", hotelCounty=" + hotelCounty + ", hotelAddress=" + hotelAddress
				+ ", hotelType=" + hotelType + ", hotelNotice=" + hotelNotice + ", hotelQa=" + hotelQa
				+ ", hotelIntroduction=" + hotelIntroduction + ", hotelState=" + hotelState + "]";
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelEmail() {
		return hotelEmail;
	}
	public void setHotelEmail(String hotelEmail) {
		this.hotelEmail = hotelEmail;
	}
	public String getHotelPassword() {
		return hotelPassword;
	}
	public void setHotelPassword(String hotelPassword) {
		this.hotelPassword = hotelPassword;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelPhone() {
		return hotelPhone;
	}
	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}
	public String getHotelPrincipal() {
		return hotelPrincipal;
	}
	public void setHotelPrincipal(String hotelPrincipal) {
		this.hotelPrincipal = hotelPrincipal;
	}
	public String getHotelTaxid() {
		return hotelTaxid;
	}
	public void setHotelTaxid(String hotelTaxid) {
		this.hotelTaxid = hotelTaxid;
	}
	public String getHotelCounty() {
		return hotelCounty;
	}
	public void setHotelCounty(String hotelCounty) {
		this.hotelCounty = hotelCounty;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public String getHotelNotice() {
		return hotelNotice;
	}
	public void setHotelNotice(String hotelNotice) {
		this.hotelNotice = hotelNotice;
	}
	public String getHotelQa() {
		return hotelQa;
	}
	public void setHotelQa(String hotelQa) {
		this.hotelQa = hotelQa;
	}
	public String getHotelIntroduction() {
		return hotelIntroduction;
	}
	public void setHotelIntroduction(String hotelIntroduction) {
		this.hotelIntroduction = hotelIntroduction;
	}
	public Integer getHotelState() {
		return hotelState;
	}
	public void setHotelState(Integer hotelState) {
		this.hotelState = hotelState;
	}
	
	
}