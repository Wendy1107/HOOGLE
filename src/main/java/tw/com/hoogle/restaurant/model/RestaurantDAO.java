package tw.com.hoogle.restaurant.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class RestaurantDAO implements RestaurantDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO restaurant (restaurantName,restaurantDes,restaurantType,restaurantPrice,restaurantPic,restaurantAddress,restaurantPhone) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT restaurantId,restaurantName,restaurantDes,restaurantType,restaurantPrice,restaurantPic,restaurantAddress,restaurantPhone FROM restaurant";
	private static final String GET_ONE_STMT = "SELECT restaurantId,restaurantName,restaurantDes,restaurantType,restaurantPrice,restaurantPic,restaurantAddress,restaurantPhone FROM restaurant where restaurantId = ?";
	private static final String DELETE = "DELETE FROM restaurant where restaurantId = ?";
	private static final String UPDATE = "UPDATE user set restaurantName=?, restaurantDes=?, restaurantType=?, restaurantPrice=?, restaurantPic=?, restaurantAddress=?, restaurantPhone=? where restaurantId = ?";

	@Override
	public void insert(RestaurantVO restaurantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, restaurantVO.getRestaurantName());
			pstmt.setString(2, restaurantVO.getRestaurantDes());
			pstmt.setString(3, restaurantVO.getRestaurantType());
			pstmt.setInt(4, restaurantVO.getRestaurantPrice());
			pstmt.setBytes(5, restaurantVO.getRestaurantPic());
			pstmt.setString(6, restaurantVO.getRestaurantAddress());
			pstmt.setString(7, restaurantVO.getRestaurantPhone());
			


			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

	@Override
	public void update(RestaurantVO restaurantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restaurantVO.getRestaurantName());
			pstmt.setString(2, restaurantVO.getRestaurantDes());
			pstmt.setString(3, restaurantVO.getRestaurantType());
			pstmt.setInt(4, restaurantVO.getRestaurantPrice());
			pstmt.setBytes(5, restaurantVO.getRestaurantPic());
			pstmt.setString(6, restaurantVO.getRestaurantAddress());
			pstmt.setString(7, restaurantVO.getRestaurantPhone());
			pstmt.setInt(8, restaurantVO.getRestaurantId());


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public void delete(Integer restaurantId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, restaurantId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public RestaurantVO findByPrimaryKey(Integer restaurantId) {
		
		RestaurantVO restaurantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, restaurantId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantId(rs.getInt("restaurantId"));
				restaurantVO.setRestaurantName(rs.getString("restaurantName"));
				restaurantVO.setRestaurantDes(rs.getString("restaurantDes"));
				restaurantVO.setRestaurantType(rs.getString("restaurantType"));
				restaurantVO.setRestaurantPrice(rs.getInt("restaurantPrice"));
				restaurantVO.setRestaurantPic(rs.getBytes("restaurantPic"));
				restaurantVO.setRestaurantAddress(rs.getString("restaurantAddress"));
				restaurantVO.setRestaurantPhone(rs.getString("restaurantPhone"));
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
		
		return restaurantVO;
	}

	@Override
	public List<RestaurantVO> getAll() {
		
		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
		RestaurantVO restaurantVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantId(rs.getInt("restaurantId"));
				restaurantVO.setRestaurantName(rs.getString("restaurantName"));
				restaurantVO.setRestaurantDes(rs.getString("restaurantDes"));
				restaurantVO.setRestaurantType(rs.getString("restaurantType"));
				restaurantVO.setRestaurantPrice(rs.getInt("restaurantPrice"));
				restaurantVO.setRestaurantPic(rs.getBytes("restaurantPic"));
				restaurantVO.setRestaurantAddress(rs.getString("restaurantAddress"));
				restaurantVO.setRestaurantPhone(rs.getString("restaurantPhone"));
				list.add(restaurantVO); // Store the row in the list
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
}


