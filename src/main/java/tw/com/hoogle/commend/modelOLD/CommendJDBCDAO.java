package tw.com.hoogle.commend.modelOLD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommendJDBCDAO implements CommendDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO Commend (ordId,commendGrade,commendContent,commendDate) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT commendAuto,ordId,commendGrade,commendContent,commendDate FROM commend";
	private static final String GET_ONE_STMT = "SELECT commendAuto,ordId,commendGrade,commendContent,commendDate FROM commend where commendAuto = ?";
	private static final String DELETE = "DELETE FROM commend where commendAuto = ?";
	private static final String UPDATE = "UPDATE commend set commendGrade=?, commendContent=?, commendDate=? where commendAuto = ?";

	@Override
	public void insert(CommendVO CommendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, CommendVO.getOrdId());
			pstmt.setInt(2, CommendVO.getCommendGrade());
			pstmt.setString(3, CommendVO.getCommendContent());
			pstmt.setDate(4, CommendVO.getCommendDate());

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
	public void update(CommendVO CommendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, CommendVO.getCommendAuto());
			pstmt.setInt(2, CommendVO.getOrdId());
			pstmt.setInt(3, CommendVO.getCommendGrade());
			pstmt.setString(4, CommendVO.getCommendContent());
			pstmt.setDate(5, CommendVO.getCommendDate());

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
	public void delete(Integer commendAuto) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, commendAuto);

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
	public CommendVO findByPrimaryKey(Integer CommendAuto) {

		CommendVO CommendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, CommendAuto);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				CommendVO = new CommendVO();
				CommendVO.setCommendAuto(rs.getInt("commendAuto"));
				CommendVO.setOrdId(rs.getInt("ordId"));
				CommendVO.setCommendGrade(rs.getInt("commendGrade"));
				CommendVO.setCommendContent(rs.getString("commendContent"));
				CommendVO.setCommendDate(rs.getDate("commendDate"));
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

		return CommendVO;

	}

	@Override
	public List<CommendVO> getAll() {

		List<CommendVO> list = new ArrayList<CommendVO>();
		CommendVO CommendVO = null;

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
				CommendVO = new CommendVO();
				CommendVO.setCommendAuto(rs.getInt("commendAuto"));
				CommendVO.setOrdId(rs.getInt("ordId"));
				CommendVO.setCommendGrade(rs.getInt("commendGrade"));
				CommendVO.setCommendContent(rs.getString("commendContent"));
				CommendVO.setCommendDate(rs.getDate("commendDate"));
				list.add(CommendVO); // Store the row in the list
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

		CommendJDBCDAO dao = new CommendJDBCDAO();

		// 新增test
//		CommendVO insertTest = new CommendVO();
//		insertTest.setOrdId(5005);
//		insertTest.setCommendGrade(5);
//		insertTest.setCommendContent("各方面都很讚");
//		insertTest.setCommendDate(java.sql.Date.valueOf("2022-12-05"));
//		dao.insert(insertTest);

		// 修改test
//		CommendVO updateTest = new CommendVO();
//		updateTest.setCommendAuto(7002);
//		updateTest.setOrdId(5002);
//		updateTest.setCommendGrade(1);
//		updateTest.setCommendContent("差勁");
//		updateTest.setCommendDate(java.sql.Date.valueOf("2023-01-01"));
//		dao.update(updateTest);

		// 刪除test
//		dao.delete(7001);

		// 查詢單一項test
//		CommendVO selectTest = dao.findByPrimaryKey(7001);
//		System.out.println(selectTest.getCommendAuto() + ",");
//		System.out.println(selectTest.getOrdId() + ",");
//		System.out.println(selectTest.getCommendGrade() + ",");
//		System.out.println(selectTest.getCommendContent() + ",");
//		System.out.println(selectTest.getCommendDate() + ",");
//		System.out.println("------------------");

		// 查詢總表
//		List<CommendVO> list = dao.getAll();
//		for (CommendVO selectAllTest : list) {
//			System.out.println(selectAllTest.getCommendAuto() + ",");
//			System.out.println(selectAllTest.getOrdId() + ",");
//			System.out.println(selectAllTest.getCommendGrade() + ",");
//			System.out.println(selectAllTest.getCommendContent() + ",");
//			System.out.println(selectAllTest.getCommendDate() + ",");
//			System.out.println();
//		}

		}

	}

