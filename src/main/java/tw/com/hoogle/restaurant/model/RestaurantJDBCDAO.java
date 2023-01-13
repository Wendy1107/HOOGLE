package tw.com.hoogle.restaurant.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantJDBCDAO implements RestaurantDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restaurantVO.getRestaurantName());
			pstmt.setString(2, restaurantVO.getRestaurantDes());
			pstmt.setString(3, restaurantVO.getRestaurantType());
			pstmt.setInt(4, restaurantVO.getRestaurantPrice());
			pstmt.setBytes(5, restaurantVO.getRestaurantPic());
			pstmt.setString(6, restaurantVO.getRestaurantAddress());
			pstmt.setString(7, restaurantVO.getRestaurantPhone());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(RestaurantVO restaurantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, restaurantId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public RestaurantVO findByPrimaryKey(Integer restaurantId) {

		RestaurantVO restaurantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) {

		RestaurantJDBCDAO dao = new RestaurantJDBCDAO();

		// 新增test
//		RestaurantVO insertTest = new RestaurantVO();
//		insertTest.setRestaurantName("testcom");
//		insertTest.setRestaurantDes("美食介紹......");
//		insertTest.setRestaurantType("在地小吃");
//		insertTest.setRestaurantPrice(50);
//		insertTest.setRestaurantPic("C:\Users\Tibame_T14\Desktop\HOOGLE\美食照片\bf31260422585113.jpg");
//		insertTest.setRestaurantAddress("台中市.....");
//		insertTest.setRestaurantPhone("04379847382");
//		dao.insert(insertTest);

		// 修改test
//		RestaurantVO updateTest = new RestaurantVO();
//		updateTest.setRestaurantId(1);
//		updateTest.setRestaurantName("testcom");
//		updateTest.setRestaurantDes("美食介紹......");
//		updateTest.setRestaurantType("在地小吃");
//		updateTest.setRestaurantPrice(50);
//		updateTest.setRestaurantPic("C:\Users\Tibame_T14\Desktop\HOOGLE\美食照片\bf31260422585113.jpg");
//		updateTest.setRestaurantAddress("台中市.....");
//		updateTest.setRestaurantPhone("04379847382");
//		dao.update(updateTest);

		// 刪除test
//		dao.delete(1);

		// 查詢單一項test
//		RestaurantVO selectTest = dao.findByPrimaryKey(1);
//		System.out.println(selectTest.getRestaurantId() + ",");
//		System.out.println(selectTest.getRestaurantName() + ",");
//		System.out.println(selectTest.getRestaurantDes() + ",");
//		System.out.println(selectTest.getRestaurantType() + ",");
//		System.out.println(selectTest.getRestaurantPrice() + ",");
//		System.out.println(selectTest.getRestaurantPic() + ",");
//		System.out.println(selectTest.getRestaurantAddress() + ",");
//		System.out.println(selectTest.getRestaurantPhone() + ",");
//		System.out.println("------------------");

		// 查詢總表

//		List<RestaurantVO> list = dao.getAll();
//		for (RestaurantVO selectAllTest : list) {
//			System.out.println(selectAllTest.getRestaurantId() + ",");
//		System.out.println(selectAllTest.getRestaurantName() + ",");
//		System.out.println(selectAllTest.getRestaurantDes() + ",");
//		System.out.println(selectAllTest.getRestaurantType() + ",");
//		System.out.println(selectAllTest.getRestaurantPrice() + ",");
//		System.out.println(selectAllTest.getRestaurantPic() + ",");
//		System.out.println(selectAllTest.getRestaurantAddress() + ",");
//			System.out.println(selectAllTest.getRestaurantPhone() + ",\t");
//			System.out.println();
//		}

	}

}
