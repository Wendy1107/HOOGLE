package tw.com.hoogle.userForBackEnd.model;

import java.util.*;


public interface UserForBackEndDAO_interface {

		public UserVO findByPrimaryKey(Integer userId);
		public UserVO findByUserEmail(String userEmail);
		public List<UserVO> getAll();

}

