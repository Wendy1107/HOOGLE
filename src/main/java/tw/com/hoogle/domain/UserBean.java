package tw.com.hoogle.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserBean {
	@Id
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
	private Date userBirthday;
	@Column(name = "userRegistration")
	private Date userRegistration;
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userEmail=" + userEmail + ", userPassword=" + userPassword
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
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public Date getUserRegistration() {
		return userRegistration;
	}
	public void setUserRegistration(Date userRegistration) {
		this.userRegistration = userRegistration;
	}


}
