package tw.com.hoogle.commend.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommendDAO implements CommendDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static DataSource ds = null;
	static {
		try {			
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO commend (ordId,commendGrade,commendContent,commendDate) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT commendAuto,ordId,commendGrade,commendContent,commendDate FROM commend order by commendAuto";
	private static final String GET_ONE_STMT = "SELECT commendAuto,ordId,commendGrade,commendContent,commendDate FROM commend where commendAuto = ?";
	private static final String DELETE = "DELETE FROM commend where commendAuto = ?";
	private static final String UPDATE = "UPDATE commend set ordId=?, commendGrade=?, commendContent=?, commendDate=? where commendAuto=?";
	private static final String GET_ORDID_STMT = "SELECT commendAuto,ordId,commendGrade,commendContent,commendDate FROM commend where upper(ordId) like upper(?)";

	@Override
	public void insert(CommendVO commendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, commendVO.getOrdId());
			pstmt.setInt(2, commendVO.getCommendGrade());
			pstmt.setString(3, commendVO.getCommendContent());
			pstmt.setDate(4, commendVO.getCommendDate());

			pstmt.executeUpdate();
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(CommendVO commendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, commendVO.getOrdId());
			pstmt.setInt(2, commendVO.getCommendGrade());
			pstmt.setString(3, commendVO.getCommendContent());
			pstmt.setDate(4, commendVO.getCommendDate());
			pstmt.setInt(5, commendVO.getCommendAuto());

			pstmt.executeUpdate();

		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, commendAuto);

			pstmt.executeUpdate();

		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public CommendVO findByPrimaryKey(Integer commendAuto) {
		CommendVO commendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, commendAuto);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				commendVO = new CommendVO();
				commendVO.setCommendAuto(rs.getInt("commendAuto"));
				commendVO.setOrdId(rs.getInt("ordId"));
				commendVO.setCommendGrade(rs.getInt("commendGrade"));
				commendVO.setCommendContent(rs.getString("commendContent"));
				commendVO.setCommendDate(rs.getDate("commendDate"));

			}

		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return commendVO;
	}

	@Override
	public List<CommendVO> getAll() {
		List<CommendVO> list = new ArrayList<CommendVO>();
		CommendVO commendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				commendVO = new CommendVO();
				commendVO.setCommendAuto(rs.getInt("commendAuto"));
				commendVO.setOrdId(rs.getInt("ordId"));
				commendVO.setCommendGrade(rs.getInt("commendGrade"));
				commendVO.setCommendContent(rs.getString("commendContent"));
				commendVO.setCommendDate(rs.getDate("commendDate"));
				list.add(commendVO);
				Collections.reverse(list);


			}

		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<CommendVO> findByOrdId(Integer ordId) {
		List<CommendVO> list = new ArrayList<CommendVO>();
		CommendVO commendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDID_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				commendVO = new CommendVO();
				commendVO.setCommendAuto(rs.getInt("commendAuto"));
				commendVO.setOrdId(rs.getInt("ordId"));
				commendVO.setCommendGrade(rs.getInt("commendGrade"));
				commendVO.setCommendContent(rs.getString("commendContent"));
				commendVO.setCommendDate(rs.getDate("commendDate"));
				list.add(commendVO);

			}

		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	}}
