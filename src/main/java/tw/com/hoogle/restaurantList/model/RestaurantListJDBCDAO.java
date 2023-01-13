package tw.com.hoogle.restaurantList.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class RestaurantListJDBCDAO implements RestaurantListDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO restaurantList (hotelId,restaurantId) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT restaurantListId,hotelId,restaurantId FROM restaurantList";
	private static final String GET_ONE_STMT = "SELECT restaurantListId,hotelId,restaurantId FROM restaurantList where restaurantListId = ?";
	private static final String DELETE = "DELETE FROM restaurantList where restaurantListId = ?";
	private static final String UPDATE = "UPDATE restaurantList set hotelId=?, restaurantId=? where restaurantListId = ?";

	@Override
	public void insert(RestaurantListVO restaurantListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, restaurantListVO.getHotelId());
			pstmt.setInt(2, restaurantListVO.getRestaurantId());


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
	public void update(RestaurantListVO restaurantListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, restaurantListVO.getRestaurantListId());
			pstmt.setInt(2, restaurantListVO.getHotelId());
			pstmt.setInt(3, restaurantListVO.getRestaurantId());

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
	public void delete(Integer restaurantListId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, restaurantListId);

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
	public RestaurantListVO findByPrimaryKey(Integer restaurantListId) {

		RestaurantListVO restaurantListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, restaurantListId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				restaurantListVO = new RestaurantListVO();
				restaurantListVO.setRestaurantListId(rs.getInt("restaurantListId"));
				restaurantListVO.setHotelId(rs.getInt("hotelId"));
				restaurantListVO.setRestaurantId(rs.getInt("restaurantId"));
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

		return restaurantListVO;

	}

	@Override
	public List<RestaurantListVO> getAll() {

		List<RestaurantListVO> list = new ArrayList<RestaurantListVO>();
		RestaurantListVO restaurantListVO = null;

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
				restaurantListVO = new RestaurantListVO();
				restaurantListVO.setRestaurantListId(rs.getInt("restaurantListId"));
				restaurantListVO.setHotelId(rs.getInt("hotelId"));
				restaurantListVO.setRestaurantId(rs.getInt("restaurantId"));
				list.add(restaurantListVO); // Store the row in the list
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

		RestaurantListJDBCDAO dao = new RestaurantListJDBCDAO();

		// 新增test
//		RestaurantListVO insertTest = new RestaurantListVO();
//		insertTest.setRestaurantListId(555555);
//		insertTest.setHotelId(279814904);
//		insertTest.restaurantId(21749812741);
//		dao.insert(insertTest);

		// 修改test
//		RestaurantListVO updateTest = new RestaurantListVO();
//		updateTest.setRestaurantListId(555555);
//		updateTest.setHotelId(1111111);
//		updateTest.restaurantId(2222222222);
//		dao.update(updateTest);

		// 刪除test
//		dao.delete(1);

		// 查詢單一項test
//		RestaurantListVO selectTest = dao.findByPrimaryKey(1);
//		System.out.println(selectTest.getRestaurantListId() + ",");
//		System.out.println(selectTest.getHotelId() + ",");
//		System.out.println(selectTest.getRestaurantId() + ",");
//		System.out.println("------------------");

		// 查詢總表

//		List<RestaurantListVO> list = dao.getAll();
//		for (RestaurantListVO selectAllTest : list) {
//		System.out.println(selectAllTest.getRestaurantListId() + ",");
//		System.out.println(selectAllTest.getHotelId() + ",");
//		System.out.println(selectAllTest.getRestaurantId() + ",");
//			System.out.println();
//		}

	}

}

