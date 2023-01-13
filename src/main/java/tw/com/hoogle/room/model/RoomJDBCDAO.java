package tw.com.hoogle.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomJDBCDAO {
	
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
//	
////	private static DataSource ds = null;
////	static {
////		try {			
////			Context ctx = new InitialContext();
////			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
////		} catch (NamingException e) {
////			e.printStackTrace();
////		}
////	}
//	
//	private static final String INSERT_STMT = "INSERT INTO room (hotelId,roomTotal,nonreserved,roomName,roomType,roomPrice) VALUES (?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT roomAuto,hotelId,roomTotal,nonreserved,roomName,roomType,roomPrice FROM room order by roomAuto";
//	private static final String GET_ONE_STMT = "SELECT roomAuto,hotelId,roomTotal,nonreserved,roomName,roomType,roomPrice FROM room where roomAuto = ?";
//	private static final String DELETE = "DELETE FROM room where roomAuto = ?";
//	private static final String UPDATE = "UPDATE room set hotelId=?, roomTotal=?, nonreserved=?, roomName=?, roomType=?, roomPrice=? where roomAuto=?";
//	@Override
//	public void insert(RoomVO roomVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, roomVO.getHotelId());
//			pstmt.setInt(2, roomVO.getRoomTotal());
//			pstmt.setInt(3, roomVO.getNonreserved());
//			pstmt.setString(4, roomVO.getRoomName());
//			pstmt.setString(5, roomVO.getRoomType());
//			pstmt.setInt(6, roomVO.getRoomPrice());
//
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
//	public void update(RoomVO roomVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, roomVO.getHotelId());
//			pstmt.setInt(2, roomVO.getRoomTotal());
//			pstmt.setInt(3, roomVO.getNonreserved());
//			pstmt.setString(4, roomVO.getRoomName());
//			pstmt.setString(5, roomVO.getRoomType());
//			pstmt.setInt(6, roomVO.getRoomPrice());
//			pstmt.setInt(7, roomVO.getRoomAuto());
//
//			pstmt.executeUpdate();
//
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
//	public void delete(Integer roomAuto) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, roomAuto);
//
//			pstmt.executeUpdate();
//
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
//	public RoomVO findByPrimaryKey(Integer roomAuto) {
//		RoomVO roomVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, roomAuto);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				roomVO = new RoomVO();
//				roomVO.setHotelId(rs.getInt("hotelId"));
//				roomVO.setRoomTotal(rs.getInt("roomTotal"));
//				roomVO.setNonreserved(rs.getInt("nonreserved"));
//				roomVO.setRoomName(rs.getString("roomName"));
//				roomVO.setRoomType(rs.getString("roomType"));
//				roomVO.setRoomPrice(rs.getInt("roomPrice"));
//				roomVO.setRoomAuto(rs.getInt("roomAuto"));
//
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//
//		} catch (SQLException se) {
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
//		return roomVO;	}
//	@Override
//	public List<RoomVO> getAll() {
//		List<RoomVO> list = new ArrayList<RoomVO>();
//		RoomVO roomVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				roomVO = new RoomVO();
//				roomVO.setRoomAuto(rs.getInt("roomAuto"));
//				roomVO.setHotelId(rs.getInt("hotelId"));
//				roomVO.setRoomTotal(rs.getInt("roomTotal"));
//				roomVO.setNonreserved(rs.getInt("nonreserved"));
//				roomVO.setRoomName(rs.getString("roomName"));
//				roomVO.setRoomType(rs.getString("roomType"));
//				roomVO.setRoomPrice(rs.getInt("roomPrice"));
//				list.add(roomVO);
//
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//
//		} catch (SQLException se) {
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
//		return list;	}
	
	public static void main(String[] args) {
		
		RoomDAO dao = new RoomDAO();

		// 新增
//		RoomVO roomVO1 = new RoomVO();
//		roomVO1.setHotelId(2011);
//		roomVO1.setRoomTotal(20);
//		roomVO1.setNonreserved(10);
//		roomVO1.setRoomName("單人房1號");
//		roomVO1.setRoomType("單人房");
//		roomVO1.setRoomPrice(2000);
//		dao.insert(roomVO1);
//		System.out.println("新增成功");
//		System.out.println("---------------------");
		
		//查詢單筆
//		RoomVO roomVO2 = dao.findByPrimaryKey(1);
//		System.out.print(roomVO2.getRoomAuto() + ",");
//		System.out.println(roomVO2.getHotelId()+",");
//		System.out.print(roomVO2.getHotelId() + ",");
//		System.out.print(roomVO2.getRoomTotal() + ",");
//		System.out.print(roomVO2.getNonreserved() + ",");
//		System.out.print(roomVO2.getRoomName() + ",");
//		System.out.print(roomVO2.getRoomType() + ",");
//		System.out.println(roomVO2.getRoomPrice()+ ",");
//		System.out.println("查詢成功");
//		System.out.println("---------------------");
		
		//查詢全部
//		List<RoomVO> list = dao.getAll();
//		for (RoomVO aRoom : list) {
//		System.out.print(aRoom.getRoomAuto() + ",");
//		System.out.print(aRoom.getHotelId() + ",");
//		System.out.print(aRoom.getRoomTotal() + ",");
//		System.out.print(aRoom.getNonreserved() + ",");
//		System.out.print(aRoom.getRoomName() + ",");
//		System.out.print(aRoom.getRoomType() + ",");
//		System.out.print(aRoom.getRoomPrice() + ",\t");
//		System.out.println("查詢成功");
//		System.out.println("---------------------");
//		}
		
		//刪除
//		dao.delete(3003);
//		System.out.println("刪除成功");
//		System.out.println("---------------------");
		
		//修改
//		RoomVO roomVO3 = new RoomVO();
//		roomVO3.setHotelId(2001);
//		roomVO3.setRoomTotal(20);
//		roomVO3.setNonreserved(11);
//		roomVO3.setRoomName("單人床2號");
//		roomVO3.setRoomType("單人床");
//		roomVO3.setRoomPrice(2200);		
//		roomVO3.setRoomAuto(3004);
//		dao.update(roomVO3);
//		System.out.println("修改成功");
//		System.out.println("---------------------");
		
		
	}
}
