package tw.com.hoogle.food.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodJDBCDAO implements FoodDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO food (restaurantId,foodPic,foodName) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT foodPicid,restaurantId,foodPic,foodName FROM food";
	private static final String GET_ONE_STMT = "SELECT foodPicid,restaurantId,foodPic,foodName FROM food where foodPicid = ?";
	private static final String DELETE = "DELETE FROM food where foodPicid = ?";
	private static final String UPDATE = "UPDATE food set restaurantId=?, foodPic=?, foodName=? where foodPicid = ?";

	@Override
	public void insert(FoodVO foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, foodVO.getRestaurantId());
			pstmt.setBytes(2, foodVO.getFoodPic());
			pstmt.setString(3, foodVO.getFoodName());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(FoodVO foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, foodVO.getRestaurantId());
			pstmt.setBytes(2, foodVO.getFoodPic());
			pstmt.setString(3, foodVO.getFoodName());
			pstmt.setInt(4, foodVO.getFoodPicid());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer foodPicid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, foodPicid);

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
	public FoodVO findByPrimaryKey(Integer foodPicid) {

		FoodVO foodVO  = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, foodPicid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				foodVO = new FoodVO();
				
				foodVO.setFoodPicid(rs.getInt("foodPicid"));
				foodVO.setRestaurantId(rs.getInt("restaurantId"));
				foodVO.setFoodPic(rs.getBytes("foodPic"));
				foodVO.setFoodName(rs.getString("foodName"));
							
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return foodVO ;
	}
	@Override
	public List<FoodVO> getAll() {

		List<FoodVO> list = new ArrayList<FoodVO>();
		FoodVO foodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// foodVO 也稱為 Domain objects
				foodVO = new FoodVO();
				foodVO.setFoodPicid(rs.getInt("foodPicid"));
				foodVO.setRestaurantId(rs.getInt("restaurantId"));
				foodVO.setFoodPic(rs.getBytes("foodPic"));
				foodVO.setFoodName(rs.getString("foodName"));
				list.add(foodVO); // Store the row in the list
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

		FoodJDBCDAO dao = new FoodJDBCDAO();
byte[] pic = new byte[10];
		
		
		// 新增test
	FoodVO insertTest = new FoodVO();
		insertTest.setRestaurantId(1);
		insertTest.setFoodPic(pic);
		insertTest.setFoodName("e");
		dao.insert(insertTest);

		
		// 修改test //////////////////失敗
//		FoodVO updateTest = new FoodVO();
//		
//		updateTest.setFoodPicid(14011);
//		updateTest.setRestaurantId(4);
//		updateTest.setFoodPic(pic);
//		updateTest.setFoodName("qqqwertyu");
//		
//		dao.update(updateTest);
//修改upd////////////////失敗
//		FoodVO upd = new FoodVO();
//		upd.setFoodPicid(14003);
//		upd.setRestaurantId(1);
//		upd.setFoodPic(pic);
//		upd.setFoodName("aaaaaaa");
//		dao.update(upd);
		

		// 刪除test
		//dao.delete(14024);

		// 查詢單一項test
//		FoodVO selectTest = dao.findByPrimaryKey(14005);
//		
//		System.out.println(selectTest.getFoodPicid() + ",");
//		System.out.println(selectTest.getRestaurantId() + ",");
//		System.out.println(selectTest.getFoodPic() + ",");
//		System.out.println(selectTest.getFoodName() + ",");
//		System.out.println("------------------");

		// 查詢總表

//		List<FoodVO> list = dao.getAll();
//		
//		for (FoodVO selectAllTest : list) {
//			System.out.println(selectAllTest.getFoodPicid() + ",");
//			System.out.println(selectAllTest.getRestaurantId() + ",");
//			System.out.println(selectAllTest.getFoodPic() + ",");
//			System.out.println(selectAllTest.getFoodName() + ",");
//			System.out.println();
//		}
//
//	}


}}