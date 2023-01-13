package tw.com.hoogle.administrator.model;
import java.sql.Date;

public class AdministratorVO implements java.io.Serializable{
	private Integer administratorId;			// 管理者編號
	private String administratorName;			// 管理者姓名
	private String administratorAccount;		// 管理者帳號
	private String administratorPassword;		// 管理者密碼
	private Boolean administratorDominate;		// 管理者權限_管理者相關
	private Boolean newsDominate;				// 管理者權限_上下架最新消息
	private Boolean hotelDominate;				// 管理者權限_飯店相關
	private Boolean userDominate;				// 管理者權限_旅客相關
	private Date administratorHiredate;		// 雇用日期
	public Integer getAdministratorId() {
		return administratorId;
	}
	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}
	public String getAdministratorName() {
		return administratorName;
	}
	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}
	public String getAdministratorAccount() {
		return administratorAccount;
	}
	public void setAdministratorAccount(String administratorAccount) {
		this.administratorAccount = administratorAccount;
	}
	public String getAdministratorPassword() {
		return administratorPassword;
	}
	public void setAdministratorPassword(String administratorPassword) {
		this.administratorPassword = administratorPassword;
	}
	public Boolean getAdministratorDominate() {
		return administratorDominate;
	}
	public void setAdministratorDominate(Boolean administratorDominate) {
		this.administratorDominate = administratorDominate;
	}
	public Boolean getNewsDominate() {
		return newsDominate;
	}
	public void setNewsDominate(Boolean newsDominate) {
		this.newsDominate = newsDominate;
	}
	public Boolean getHotelDominate() {
		return hotelDominate;
	}
	public void setHotelDominate(Boolean hotelDominate) {
		this.hotelDominate = hotelDominate;
	}
	public Boolean getUserDominate() {
		return userDominate;
	}
	public void setUserDominate(Boolean userDominate) {
		this.userDominate = userDominate;
	}
	public Date getAdministratorHiredate() {
		return administratorHiredate;
	}
	public void setAdministratorHiredate(Date administratorHiredate) {
		this.administratorHiredate = administratorHiredate;
	}
	
	
	
	
}

