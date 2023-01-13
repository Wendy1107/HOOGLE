package tw.com.hoogle.search.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")

public class SearchBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId")
	private Integer userId;

	@Column(name = "userEmail")
	private String userEmail;
	
	@Column(name = "userPassword")
	private String userPassword;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userPhone")
	private String userPhone;
	
	@Column(name = "userIdentity")
	private String userIdentity;
	
	@Column(name = "userBirthday")
	private java.util.Date userBirthday;
	
	@Column(name = "userRegistration")
	private java.util.Date userRegistration;

	@Override
	public String toString() {
		return "SearchBean [userId=" + userId + ", userEmail=" + userEmail + ", userPassword=" + userPassword
				+ ", userName=" + userName + ", userPhone=" + userPhone + ", userIdentity=" + userIdentity
				+ ", userBirthday=" + userBirthday + ", userRegistration=" + userRegistration + "]";
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	public java.util.Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(java.util.Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public java.util.Date getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(java.util.Date userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	
}
