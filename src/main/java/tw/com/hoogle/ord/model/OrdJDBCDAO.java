package tw.com.hoogle.ord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdJDBCDAO  {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";

//		private static DataSource ds = null;
//		static {
//			try {			
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}

//	private static final String INSERT_STMT = "INSERT INTO ord (userId,hotelId,userName,hotelName,ordDate,ordCheckin,ordCheckout,ordNights,ordRemark) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT ordId,userId,hotelId,userName,hotelName,ordDate,ordCheckin,ordCheckout,ordNights,ordRemark FROM ord order by ordId";
//	private static final String GET_ONE_STMT = "SELECT ordId,userId,hotelId,userName,hotelName,ordDate,ordCheckin,ordCheckout,ordNights,ordRemark FROM ord where ordId = ?";
//	private static final String DELETE = "DELETE FROM ord where ordId = ?";
//	private static final String UPDATE = "UPDATE ord set userId=?, hotelId=?, userName=?, hotelName=?, ordDate=?, ordCheckin=?, ordCheckout=?, ordNights=?, ordRemark=? where ordId=?";

//	@Override
//	public void insert(OrdVO ordVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////					con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, ordVO.getUserId());
//			pstmt.setInt(2, ordVO.getHotelId());
//			pstmt.setString(3, ordVO.getUserName());
//			pstmt.setString(4, ordVO.getHotelName());
//			pstmt.setDate(5, ordVO.getOrdDate());
//			pstmt.setDate(6, ordVO.getOrdCheckin());
//			pstmt.setDate(7, ordVO.getOrdCheckout());
//			pstmt.setInt(8, ordVO.getOrdNights());
//			pstmt.setString(9, ordVO.getOrdRemark());
//
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

//	@Override
//	public void update(OrdVO ordVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, ordVO.getUserId());
//			pstmt.setInt(2, ordVO.getHotelId());
//			pstmt.setString(3, ordVO.getUserName());
//			pstmt.setString(4, ordVO.getHotelName());
//			pstmt.setDate(5, ordVO.getOrdDate());
//			pstmt.setDate(6, ordVO.getOrdCheckin());
//			pstmt.setDate(7, ordVO.getOrdCheckout());
//			pstmt.setInt(8, ordVO.getOrdNights());
//			pstmt.setString(9, ordVO.getOrdRemark());
//			pstmt.setInt(10, ordVO.getOrdId());
//
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

//	@Override
//	public void delete(Integer ordId) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////					con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, ordId);
//
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

//	@Override
//	public OrdVO findByPrimaryKey(Integer ordId) {
//		OrdVO ordVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////					con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, ordId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				ordVO = new OrdVO();
//				ordVO.setUserId(rs.getInt("userId"));
//				ordVO.setHotelId(rs.getInt("hotelId"));
//				ordVO.setUserName(rs.getString("userName"));
//				ordVO.setHotelName(rs.getString("hotelName"));
//				ordVO.setOrdDate(rs.getDate("ordDate"));
//				ordVO.setOrdCheckin(rs.getDate("ordCheckin"));
//				ordVO.setOrdCheckout(rs.getDate("ordCheckout"));
//				ordVO.setOrdNights(rs.getInt("ordNights"));
//				ordVO.setOrdRemark(rs.getString("ordRemark"));
//				ordVO.setOrdId(rs.getInt("ordId"));
//
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return ordVO;
//	}

//	@Override
//	public List<OrdVO> getAll() {
//		List<OrdVO> list = new ArrayList<OrdVO>();
//		OrdVO ordVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////					con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				ordVO = new OrdVO();
//				ordVO.setOrdId(rs.getInt("ordId"));
//				ordVO.setUserId(rs.getInt("userId"));
//				ordVO.setHotelId(rs.getInt("hotelId"));
//				ordVO.setUserName(rs.getString("userName"));
//				ordVO.setHotelName(rs.getString("hotelName"));
//				ordVO.setOrdDate(rs.getDate("ordDate"));
//				ordVO.setOrdCheckin(rs.getDate("ordCheckin"));
//				ordVO.setOrdCheckout(rs.getDate("ordCheckout"));
//				ordVO.setOrdNights(rs.getInt("ordNights"));
//				ordVO.setOrdRemark(rs.getString("ordRemark"));
//				list.add(ordVO);
//
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	public static void main(String[] args) {
		
				OrdDAO dao = new OrdDAO();
		
				// 新增
//				OrdVO ordVO1 = new OrdVO();
//				ordVO1.setUserId(1001);
//				ordVO1.setHotelId(2001);
//				ordVO1.setUserName("userYang");
//				ordVO1.setHotelName("hotelYang");
//				ordVO1.setOrdDate(java.sql.Date.valueOf("2022-11-01"));
//				ordVO1.setOrdCheckin(java.sql.Date.valueOf("2022-11-11"));
//				ordVO1.setOrdCheckout(java.sql.Date.valueOf("2022-11-13"));
//				ordVO1.setOrdNights(1);
//				ordVO1.setOrdRemark("不要早餐");
//				dao.insert(ordVO1);
//				System.out.println("新增成功");
//				System.out.println("---------------------");
				
				//查詢單筆
//				OrdVO ordVO2 = dao.findByPrimaryKey(1);
//				System.out.print(ordVO2.getOrdId() + ",");
//				System.out.println(ordVO2.getUserId()+",");
//				System.out.print(ordVO2.getHotelId() + ",");
//				System.out.print(ordVO2.getUserName() + ",");
//				System.out.print(ordVO2.getHotelName() + ",");
//				System.out.print(ordVO2.getOrdDate() + ",");
//				System.out.print(ordVO2.getOrdCheckin() + ",");
//				System.out.println(ordVO2.getOrdCheckout()+ ",");
//				System.out.println(ordVO2.getOrdNights()+ ",");
//				System.out.println(ordVO2.getOrdRemark());
//				System.out.println("查詢成功");
//				System.out.println("---------------------");
				
				//查詢全部
//				List<OrdVO> list = dao.getAll();
//				for (OrdVO aOrd : list) {
//				System.out.print(aOrd.getOrdId() + ",");
//				System.out.print(aOrd.getUserId() + ",");
//				System.out.print(aOrd.getHotelId() + ",");
//				System.out.print(aOrd.getUserName() + ",");
//				System.out.print(aOrd.getHotelName() + ",");
//				System.out.print(aOrd.getOrdDate() + ",");
//				System.out.print(aOrd.getOrdCheckin() + ",\t");
//				System.out.print(aOrd.getOrdCheckout() + ",\t");
//				System.out.print(aOrd.getOrdNights() + ",\t");
//				System.out.print(aOrd.getOrdRemark() + ",\t");
//				System.out.println("查詢成功");
//				System.out.println("---------------------");
//				}
				
				//刪除
//				dao.delete(3);
//				System.out.println("刪除成功");
//				System.out.println("---------------------");
				
				//修改
//				OrdVO ordVO3 = new OrdVO();
//				ordVO3.setOrdId(1);
//				ordVO3.setUserId(1011);
//				ordVO3.setHotelId(2011);
//				ordVO3.setUserName("YYY");
//				ordVO3.setOrdDate(java.sql.Date.valueOf("2022-12-12"));
//				ordVO3.setOrdCheckin(java.sql.Date.valueOf("2022-12-22"));
//				ordVO3.setOrdCheckout(java.sql.Date.valueOf("2022-12-23"));
//				ordVO3.setOrdNights(1);
//				ordVO3.setOrdRemark("大份早餐");
//				dao.update(ordVO3);
//				System.out.println("修改成功");
//				System.out.println("---------------------");
				
				
	}
}
