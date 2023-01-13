package tw.com.hoogle.dao;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import tw.com.hoogle.domain.UserBean;

public interface UserDAO {

	public abstract UserBean findByUserEmail(String userEamil);
	
	public abstract List<UserBean> getAll();
	
	public abstract UserBean insert(UserBean bean);
	
	public abstract UserBean update(UserBean bean);
	
	public abstract boolean delete(Integer userId);
	

	
}
