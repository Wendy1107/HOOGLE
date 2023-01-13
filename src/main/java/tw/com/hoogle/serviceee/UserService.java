package tw.com.hoogle.serviceee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.hoogle.dao.UserDAO;
import tw.com.hoogle.domain.UserBean;

@Service
@Transactional //整個類別都由Spring控管交易 commit&rollback機制
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public UserBean login(String userEmail, String userPassword) {
		UserBean userbean = userDAO.findByUserEmail(userEmail);
		if(userbean != null) {
			if(userPassword != null && userPassword.length() != 0) {
				String pass = userbean.getUserPassword();
				 if(userPassword.equals(pass)) {
					 return userbean;
				 }
			}
		}
		return null;
	}
	
	
}
