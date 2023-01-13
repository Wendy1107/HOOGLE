package tw.com.hoogle.compare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CompareDAO implements CompareDAO_interface {

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

//	private static final String GET_ONE_STMT = "SELECT * FROM v_hotel_room_pic where hotelName = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM v_hotel_room_pic where hotelName = ?";
	private static final String GET_ONE_SERVICE = "SELECT serviceName FROM v_hotelservice where hotelname = ?";	
	private static final String GET_ALL_STMT = "SELECT DISTINCT hotelName FROM v_hotelall ";
	private static final String GET_ONEROOM_STMT = "SELECT roomName FROM v_hotelall where hotelName = ?";
	
	@Override
	public List<CompareVO> findByHotelName(String hotelName) {
		List<CompareVO> list = new ArrayList<CompareVO>();
		CompareVO compareVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, hotelName);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				compareVO = new CompareVO();
				compareVO.setHotelName(rs.getString("hotelName"));
				compareVO.setHotelAddress(rs.getString("hotelAddress"));
				compareVO.setRoomName(rs.getString("roomName"));
				compareVO.setRoomPrice(rs.getInt("roomPrice"));
				compareVO.setHotelpicNo(rs.getBytes("hotelpicNo"));
//				compareVO.setServiceName(rs.getString("serviceName"));

				
				list.add(compareVO);

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
	public List<CompareVO> findByHotelService(String hotelName) {
		List<CompareVO> listService = new ArrayList<CompareVO>();
		CompareVO compareVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SERVICE);
			
			pstmt.setString(1, hotelName);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				compareVO = new CompareVO();
//				compareVO.setHotelName(rs.getString("hotelName"));
				compareVO.setServiceName(rs.getString("serviceName"));
				listService.add(compareVO);

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
		return listService;
	}
	
	
	@Override
	public List<CompareVO> getAll() {
		List<CompareVO> list = new ArrayList<CompareVO>();
		CompareVO compareVO = null;

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
				compareVO = new CompareVO();
				compareVO.setHotelName(rs.getString("hotelName"));
//				compareVO.setHotelAddress(rs.getString("hotelAddress"));
//				compareVO.setRoomType(rs.getString("roomName"));
//				compareVO.setRoomPrice(rs.getInt("roomPrice"));
				list.add(compareVO);
			}

		} 
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
	public List<CompareVO> getRoomType(String hotelName) {
		List<CompareVO> list = new ArrayList<CompareVO>();
		CompareVO compareVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEROOM_STMT);
			
			pstmt.setString(1, hotelName);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				compareVO = new CompareVO();
				compareVO.setRoomName(rs.getString("roomName"));
				list.add(compareVO);

			}

		} 

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
}
