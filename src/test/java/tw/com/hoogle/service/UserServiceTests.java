package tw.com.hoogle.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.hoogle.dao.UserDAO;
import tw.com.hoogle.domain.UserBean;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserDAO userdao;
	
	@Test
	public void testFindbyUserEmail() {
		UserBean userbean = userdao.findByUserEmail("user@gmail.com");
		System.out.println("userbean = " + userbean);
	}
	
}
