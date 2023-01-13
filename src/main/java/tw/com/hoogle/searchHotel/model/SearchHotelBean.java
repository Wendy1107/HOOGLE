package tw.com.hoogle.searchHotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotel")

public class SearchHotelBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "hotelId")
	private Integer hotelId;

	@Column(name = "hotelEmail")
	private String hotelEmail;
	
	@Column(name = "hotelPassword")
	private String hotelPassword;
	
	@Column(name = "hotelName")
	private String hotelName;
	
	@Column(name = "hotelPhone")
	private String hotelPhone;
	
	@Column(name = "hotelPrincipal")
	private String hotelPrincipal;
	
	@Column(name = "hotelTaxid")
	private String hotelTaxid;
	
	@Column(name = "hotelCounty")
	private String hotelCounty;
	
	@Column(name = "hotelAddress")
	private String hotelAddress;
	
	@Column(name = "hotelType")
	private String hotelType;
	
	@Column(name = "hotelNotice")
	private String hotelNotice;

	@Column(name = "hotelQa")
	private String hotelQa;
	
	@Column(name = "hotelIntroduction")
	private String hotelIntroduction;
	
	@Column(name = "hotelState")
	private Integer hotelState;

	
	
	@Override
	public String toString() {
		return "SearchHotelBean [hotelId=" + hotelId + ", hotelEmail=" + hotelEmail + ", hotelPassword=" + hotelPassword
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
