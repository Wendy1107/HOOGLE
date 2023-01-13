package tw.com.hoogle.ord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.com.hoogle.orddetail.model.*;

public class OrdDAO implements OrdDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";

	private static DataSource ds = null;
	static {
		try {			
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ord (userId,hotelId,userName,hotelName,ordDate,ordCheckin,ordCheckout,ordNights,ordRemark) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ordId,userId,hotelId,userName,hotelName,ordDate,ordCheckin,ordCheckout,ordNights,ordRemark FROM ord order by ordId";
	private static final String GET_ONE_STMT = "SELECT ordId,userId,hotelId,userName,hotelName,ordDate,ordCheckin,ordCheckout,ordNights,ordRemark FROM ord where ordId = ?";
	private static final String GET_ORD_STMT = "SELECT ordId,userId,hotelId,userName,hotelName,ordDate,ordCheckin,ordCheckout,ordNights,ordRemark FROM ord where userId = ?";
//	private static final String GET_ORDDETAIL_STMT = "SELECT orddetailId,ordId,roomAuto,roomNumber FROM orddetail where ordId = ?";
	private static final String DELETE = "DELETE FROM ord where ordId = ?";
	private static final String UPDATE = "UPDATE ord set userId=?, hotelId=?, userName=?, hotelName=?, ordDate=?, ordCheckin=?, ordCheckout=?, ordNights=?, ordRemark=? where ordId=?";
	private static final String GET_NEWORD_STMT="SELECT ordId FROM ord ord  where userId = ?  order by ordId asc ";
	
	@Override
	public void insert(OrdVO ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, ordVO.getUserId());
			pstmt.setInt(2, ordVO.getHotelId());
			pstmt.setString(3, ordVO.getUserName());
			pstmt.setString(4, ordVO.getHotelName());
			pstmt.setDate(5, ordVO.getOrdDate());
			pstmt.setDate(6, ordVO.getOrdCheckin());
			pstmt.setDate(7, ordVO.getOrdCheckout());
			pstmt.setInt(8, ordVO.getOrdNights());
			pstmt.setString(9, ordVO.getOrdRemark());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys(); 
			if (rs.next()) { 
				Long ordId = rs.getLong(1); 
				System.out.println("ordId ="+ordId); 
			}
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
	public void update(OrdVO ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ordVO.getUserId());
			pstmt.setInt(2, ordVO.getHotelId());
			pstmt.setString(3, ordVO.getUserName());
			pstmt.setString(4, ordVO.getHotelName());
			pstmt.setDate(5, ordVO.getOrdDate());
			pstmt.setDate(6, ordVO.getOrdCheckin());
			pstmt.setDate(7, ordVO.getOrdCheckout());
			pstmt.setInt(8, ordVO.getOrdNights());
			pstmt.setString(9, ordVO.getOrdRemark());
			pstmt.setInt(10, ordVO.getOrdId());

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
	public void delete(Integer ordId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ordId);

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
	public OrdVO findByPrimaryKey(Integer ordId) {
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ordId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ordVO = new OrdVO();
				ordVO.setUserId(rs.getInt("userId"));
				ordVO.setHotelId(rs.getInt("hotelId"));
				ordVO.setUserName(rs.getString("userName"));
				ordVO.setHotelName(rs.getString("hotelName"));
				ordVO.setOrdDate(rs.getDate("ordDate"));
				ordVO.setOrdCheckin(rs.getDate("ordCheckin"));
				ordVO.setOrdCheckout(rs.getDate("ordCheckout"));
				ordVO.setOrdNights(rs.getInt("ordNights"));
				ordVO.setOrdRemark(rs.getString("ordRemark"));
				ordVO.setOrdId(rs.getInt("ordId"));

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
		return ordVO;
	}

	@Override
	public List<OrdVO> getAll() {
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			 String nowDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
	                    .format(LocalDateTime.now());
			 System.out.println("nowDate"+nowDate);
			
			while (rs.next()) {
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getInt("ordId"));
				ordVO.setUserId(rs.getInt("userId"));
				ordVO.setHotelId(rs.getInt("hotelId"));
				ordVO.setUserName(rs.getString("userName"));
				ordVO.setHotelName(rs.getString("hotelName"));
				ordVO.setOrdDate(rs.getDate("ordDate"));
				ordVO.setOrdCheckin(rs.getDate("ordCheckin"));
				ordVO.setOrdCheckout(rs.getDate("ordCheckout"));
				ordVO.setOrdNights(rs.getInt("ordNights"));
				ordVO.setOrdRemark(rs.getString("ordRemark"));
				ordVO.setNowDate(nowDate);
				list.add(ordVO);

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
	public  List<OrdVO> findByUserId(Integer userId) {
		OrdVO ordVO = null;
		List<OrdVO> list = new ArrayList<OrdVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORD_STMT);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ordVO = new OrdVO();
				ordVO.setUserId(rs.getInt("userId"));
				ordVO.setHotelId(rs.getInt("hotelId"));
				ordVO.setUserName(rs.getString("userName"));
				ordVO.setHotelName(rs.getString("hotelName"));
				ordVO.setOrdDate(rs.getDate("ordDate"));
				ordVO.setOrdCheckin(rs.getDate("ordCheckin"));
				ordVO.setOrdCheckout(rs.getDate("ordCheckout"));
				ordVO.setOrdNights(rs.getInt("ordNights"));
				ordVO.setOrdRemark(rs.getString("ordRemark"));
				ordVO.setOrdId(rs.getInt("ordId"));
				list.add(ordVO);

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
	public OrdVO findNewordId(Integer userId) {
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEWORD_STMT);

			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ordVO = new OrdVO();
				Integer ordId = rs.getInt(1); 
				ordVO.setOrdId(ordId);
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
		return ordVO;
	}
}


