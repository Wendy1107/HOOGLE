package tw.com.hoogle.ordHibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ord")

public class OrdBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ordId")
	private Integer ordId;

	@Column(name = "userId")
	private Integer userId;

	@Column(name = "hotelId")
	private Integer hotelId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "hotelName")
	private String hotelName;

	@Column(name = "ordDate")
	private java.util.Date ordDate;

	@Column(name = "ordCheckin")
	private java.util.Date ordCheckin;

	@Column(name = "ordCheckout")
	private java.util.Date ordCheckout;

	@Column(name = "ordNights")
	private Integer ordNights;

	@Column(name = "ordRemark")
	private String ordRemark;

	@Override
	public String toString() {
		return "OrdBean [ordId=" + ordId + ", userId=" + userId + ", hotelId=" + hotelId + ", userName=" + userName
				+ ", hotelName=" + hotelName + ", ordDate=" + ordDate + ", ordCheckin=" + ordCheckin + ", ordCheckout="
				+ ordCheckout + ", ordNights=" + ordNights + ", ordRemark=" + ordRemark + "]";
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public java.util.Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(java.util.Date ordDate) {
		this.ordDate = ordDate;
	}

	public java.util.Date getOrdCheckin() {
		return ordCheckin;
	}

	public void setOrdCheckin(java.util.Date ordCheckin) {
		this.ordCheckin = ordCheckin;
	}

	public java.util.Date getOrdCheckout() {
		return ordCheckout;
	}

	public void setOrdCheckout(java.util.Date ordCheckout) {
		this.ordCheckout = ordCheckout;
	}

	public Integer getOrdNights() {
		return ordNights;
	}

	public void setOrdNights(Integer ordNights) {
		this.ordNights = ordNights;
	}

	public String getOrdRemark() {
		return ordRemark;
	}

	public void setOrdRemark(String ordRemark) {
		this.ordRemark = ordRemark;
	}

}
