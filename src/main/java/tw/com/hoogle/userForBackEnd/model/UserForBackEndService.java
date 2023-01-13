package tw.com.hoogle.userForBackEnd.model;

import java.util.*;
import java.util.stream.Collectors;


import java.sql.*;

public class UserForBackEndService {

	private UserForBackEndDAO_interface dao;

	public UserForBackEndService() {

		dao = new UserForBackEndDAO();
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
	
	
	public UserVO findByUserEmail(String userEmail) {
		return dao.findByUserEmail(userEmail);
	}
	

}
