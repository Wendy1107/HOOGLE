package tw.com.hoogle.userForBackEnd.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;


public class UserForBackEndDAO implements UserForBackEndDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = "SELECT userId,userEmail,userPassword,userName,userPhone,userIdentity,userBirthday,userRegistration FROM user";
	private static final String GET_ONE_STMT = "SELECT userId,userEmail,userPassword,userName,userPhone,userIdentity,userBirthday,userRegistration FROM user where userId = ?";
	private static final String GET_EMAILbyUser_STMT = "select userId,userEmail,userPassword,userName,userPhone,userIdentity,userBirthday,userRegistration from user where userEmail = ?";

	
	

	@Override
	public UserVO findByPrimaryKey(Integer userId) {
		
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("userId"));
				userVO.setUserEmail(rs.getString("userEmail"));
				userVO.setUserPassword(rs.getString("userPassword"));
				userVO.setUserName(rs.getString("userName"));
				userVO.setUserPhone(rs.getString("userPhone"));
				userVO.setUserIdentity(rs.getString("userIdentity"));
				userVO.setUserBirthday(rs.getDate("userBirthday"));
				userVO.setUserRegistration(rs.getDate("userRegistration"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return userVO;
	}

	@Override
	public List<UserVO> getAll() {
		
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO userVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("userId"));
				userVO.setUserEmail(rs.getString("userEmail"));
				userVO.setUserPassword(rs.getString("userPassword"));
				userVO.setUserName(rs.getString("userName"));
				userVO.setUserPhone(rs.getString("userPhone"));
				userVO.setUserIdentity(rs.getString("userIdentity"));
				userVO.setUserBirthday(rs.getDate("userBirthday"));
				userVO.setUserRegistration(rs.getDate("userRegistration"));
				list.add(userVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}



	@Override
	public UserVO findByUserEmail(String userEmail) {
		
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMAILbyUser_STMT);

			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("userId"));
				userVO.setUserEmail(rs.getString("userEmail"));
				userVO.setUserPassword(rs.getString("userPassword"));
				userVO.setUserName(rs.getString("userName"));
				userVO.setUserPhone(rs.getString("userPhone"));
				userVO.setUserIdentity(rs.getString("userIdentity"));
				userVO.setUserBirthday(rs.getDate("userBirthday"));
				userVO.setUserRegistration(rs.getDate("userRegistration"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return userVO;
	}

}

