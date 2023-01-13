package tw.com.hoogle.hotelpic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tw.com.hoogle.servicelist.model.ServiceListVO;

public class HotelpicJDBCDAO implements HotelpicDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO hotelpic (hotelId,hotelpicNo,hotelpicName) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT hotelpicId,hotelId,hotelpicNo,hotelpicName FROM hotelpic";
	private static final String GET_ONE_STMT = "SELECT hotelpicId,hotelId,hotelpicNo,hotelpicName FROM hotelpic where hotelpicId = ?";
	private static final String DELETE = "DELETE FROM hotelpic where hotelpicId = ?";
	private static final String UPDATE = "UPDATE hotelpic set hotelId=?, hotelpicNo=?, hotelpicName=? where hotelpicId = ?";

	@Override
	public void insert(HotelpicVO hotelpicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, hotelpicVO.getHotelId());
			pstmt.setBytes(2, hotelpicVO.getHotelpicNo());
			pstmt.setString(3, hotelpicVO.getHotelpicName());

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
	public void update(HotelpicVO hotelpicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, hotelpicVO.getHotelId());
			pstmt.setBytes(2, hotelpicVO.getHotelpicNo());
			pstmt.setString(3, hotelpicVO.getHotelpicName());
			pstmt.setInt(4, hotelpicVO.getHotelpicId());

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
	public void delete(Integer hotelpicId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, hotelpicId);

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
	public HotelpicVO findByPrimaryKey(Integer hotelpicId) {

		HotelpicVO hotelpicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, hotelpicId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				hotelpicVO = new HotelpicVO();
				hotelpicVO.setHotelpicId(rs.getInt("hotelpicId"));
				hotelpicVO.setHotelId(rs.getInt("hotelId"));
				hotelpicVO.setHotelpicNo(rs.getBytes("hotelpicNo"));
				hotelpicVO.setHotelpicName(rs.getString("hotelpicName"));
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

		return hotelpicVO;

	}

	@Override
	public List<HotelpicVO> getAll() {

		List<HotelpicVO> list = new ArrayList<HotelpicVO>();
		HotelpicVO hotelpicVO = null;

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
				hotelpicVO = new HotelpicVO();
				hotelpicVO.setHotelpicId(rs.getInt("hotelpicId"));
				hotelpicVO.setHotelId(rs.getInt("hotelId"));
				hotelpicVO.setHotelpicNo(rs.getBytes("hotelpicNo"));
				hotelpicVO.setHotelpicName(rs.getString("hotelpicName"));
				list.add(hotelpicVO); // Store the row in the list
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

		HotelpicJDBCDAO dao = new HotelpicJDBCDAO();
	//	byte[] pic = new byte[10];
		// 新增test
//		HotelpicVO insertTest = new HotelpicVO();
//		insertTest.setHotelpicId(568797);
//		insertTest.setHotelId(465789);
//		insertTest.setHotelpicNo(C:/.........);
//		insertTest.setHotelpicName("SHKAHRJLFSK;");
//		dao.insert(insertTest);

		// 修改test
		HotelpicVO updateTest = new HotelpicVO();
		updateTest.setHotelpicId(13001);
		updateTest.setHotelId(3001);
//		updateTest.setHotelpicNo(C:/.........);
		updateTest.setHotelpicName("JRJ");
		dao.update(updateTest);

		// 刪除test
//		dao.delete(1);

		// 查詢單一項test
//		HotelpicVO selectTest = dao.findByPrimaryKey(1);
//		System.out.println(selectTest.getHotelpicId() + ",");
//		System.out.println(selectTest.getHotelId() + ",");
//		System.out.println(selectTest.getHotelpicNo() + ",");
//		System.out.println(selectTest.getHotelpicName() + ",");
//		System.out.println("------------------");

		// 查詢總表

//		List<HotelpicVO> list = dao.getAll();
//		for (HotelpicVO selectAllTest : list) {
//			System.out.println(selectAllTest.getHotelpicId() + ",");
//			System.out.println(selectAllTest.getHotelId() + ",");
//			System.out.println(selectAllTest.getHotelpicNo() + ",");
//			System.out.println(selectAllTest.getHotelpicName() + ",\t");
//			System.out.println();
//		}

	}

	@Override
	public void insert2(HotelpicVO hotelpicVO,Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, hotelpicVO.getHotelId());
			pstmt.setBytes(2, hotelpicVO.getHotelpicNo());
			pstmt.setString(3, hotelpicVO.getHotelpicName());

			Statement stmt=	con.createStatement();
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-ServiceListDAO-");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}

	@Override
	public void delete2(Integer hotelpicId, Connection con) {
		// TODO Auto-generated method stub
		
	}
	}


