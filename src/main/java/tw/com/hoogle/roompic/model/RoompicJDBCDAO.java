package tw.com.hoogle.roompic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoompicJDBCDAO implements RoompicDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO roompic (roomAuto,roompicPic) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT roompicId,roomAuto,roompicPic FROM roompic";
	private static final String GET_ONE_STMT = "SELECT roompicId,roomAuto,roompicPic FROM roompic where roompicId = ?";
	private static final String DELETE = "DELETE FROM roompic where roompicId = ?";
	private static final String UPDATE = "UPDATE roompic set roomAuto=?,  roompicPic=? where roompicId = ?";
	
	@Override
	public void insert(RoompicVO roompicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, roompicVO.getRoomAuto());
			
			pstmt.setBytes(2, roompicVO.getRoompicPic());

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
	public void update(RoompicVO roompicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roompicVO.getRoomAuto());
		
			pstmt.setBytes(2, roompicVO.getRoompicPic());
			pstmt.setInt(3, roompicVO.getRoompicId());

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
	public void delete(Integer roompicId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roompicId);

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
	public RoompicVO findByPrimaryKey(Integer roompicId) {

		RoompicVO roompicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roompicId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				roompicVO = new RoompicVO();
				roompicVO.setRoompicId(rs.getInt("roompicId"));
				roompicVO.setRoomAuto(rs.getInt("roomAuto"));
			
				roompicVO.setRoompicPic(rs.getBytes("roompicPic"));
			}

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

		return roompicVO;

	}

	@Override
	public List<RoompicVO> getAll() {

		List<RoompicVO> list = new ArrayList<RoompicVO>();
		RoompicVO roompicVO = null;

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
				roompicVO = new RoompicVO();
				roompicVO.setRoompicId(rs.getInt("roompicId"));
				roompicVO.setRoomAuto(rs.getInt("roomAuto"));
				
				roompicVO.setRoompicPic(rs.getBytes("roompicPic"));

				list.add(roompicVO); // Store the row in the list
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

	@Override
	public int updatePicByRoomAuto(RoompicVO vo) {
		final String sql = "update roompic set roompicPic = ? where roomauto = ?";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setBytes(1, vo.getRoompicPic());
			pstmt.setInt(2, vo.getRoomAuto());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	public static void main(String[] args) {

		RoompicJDBCDAO dao = new RoompicJDBCDAO();

		// 新增test
//		RoompicVO insertTest = new RoompicVO();
//		insertTest.setRoomAuto(4001);
//		insertTest.setRoomType("單人房");
////		insertTest.setRoompicPic(); //不知道怎麼弄
//		dao.insert(insertTest);
//		System.out.println("新增成功");
		
		// 修改test
		RoompicVO updateTest = new RoompicVO();
		updateTest.setRoompicId(14001);
		updateTest.setRoomAuto(4001);
		updateTest.setRoomType("123");
////		updateTest.setRoompicPic();
		dao.update(updateTest);
		System.out.println("修改成功");

		// 刪除test
//		dao.delete(14001);
//		System.out.println("刪除成功");

		// 查詢單一項test
//		RoompicVO selectTest = dao.findByPrimaryKey(14001);
//		System.out.println(selectTest.getRoompicId() + ",");
//		System.out.println(selectTest.getRoomAuto() + ",");
//		System.out.println(selectTest.getRoomType() + ",");
//		System.out.println(selectTest.getRoompicPic() + ",");
//		System.out.println("------------------");

		// 查詢總表

//		List<RoompicVO> list = dao.getAll();
//		for (RoompicVO selectAllTest : list) {
//			System.out.println(selectAllTest.getRoompicId() + ",");
//			System.out.println(selectAllTest.getRoomAuto() + ",");
//			System.out.println(selectAllTest.getRoomType() + ",");
//			System.out.println(selectAllTest.getRoompicPic() + ",\t");
//			System.out.println();
//		}

	}

}
