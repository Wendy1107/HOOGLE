package tw.com.hoogle.user.model;

import java.util.*;
import java.util.stream.Collectors;
import java.sql.*;

public class UserService {

	private UserDAO_interface dao;

	public UserService() {

		dao = new UserDAO();
	}

	public UserVO addUser(String userEmail, String userPassword, String userName, String userPhone, String userIdentity,
			java.sql.Date userBirthday, java.sql.Date userRegistration) {

		UserVO userVO = new UserVO();

		userVO.setUserEmail(userEmail);
		userVO.setUserPassword(userPassword);
		userVO.setUserName(userName);
		userVO.setUserPhone(userPhone);
		userVO.setUserIdentity(userIdentity);
		userVO.setUserBirthday(userBirthday);
		userVO.setUserRegistration(userRegistration);
		dao.insert(userVO);

		return userVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public UserVO addUser(UserVO userVO) {
		java.util.Date date = new java.util.Date();
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		userVO.setUserRegistration(dateSql);
		dao.insert(userVO);
		return userVO;
	}

	public UserVO updateUser(Integer userId, String userEmail, String userPassword, String userName, String userPhone,
			String userIdentity, java.sql.Date userBirthday, java.sql.Date userRegistration) {

		UserVO userVO = new UserVO();

		userVO.setUserId(userId);
		userVO.setUserEmail(userEmail);
		userVO.setUserPassword(userPassword);
		userVO.setUserName(userName);
		userVO.setUserPhone(userPhone);
		userVO.setUserIdentity(userIdentity);
		userVO.setUserBirthday(userBirthday);
		userVO.setUserRegistration(userRegistration);

		dao.update(userVO);
		return userVO;
	}

	// 預留給 Struts 2 用的
	public UserVO updateUser(UserVO userVO) {
		System.out.println("### updateUser service");
		dao.update(userVO);
		return userVO;
	}
	
	public void deleteUser(Integer userId) {
		dao.delete(userId);
	}
	
	public UserVO getOneUser(Integer userId) {
//		return dao.findByPrimaryKey(userId);
		return dao.getAll().stream()
				.filter(e -> e.getUserId().equals(userId))
				.findAny().orElse(null);
	}
	
	public List<UserVO> getAll(){
		return dao.getAll();
	}
	
	public List<UserVO> getUserEmails(String userEmail){
		return dao.getAll().stream()
				.filter(e -> e.getUserEmail().equals(userEmail))
				.collect(Collectors.toList());
	}
	
	public UserVO findByUserEmail(String userEmail) {
		return dao.findByUserEmail(userEmail);
	}
	
	public String pwdhash(String password) {
		return dao.pwdhash(password);
	}
}
