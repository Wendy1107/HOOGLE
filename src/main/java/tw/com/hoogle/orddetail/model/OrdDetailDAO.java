package tw.com.hoogle.orddetail.model;

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


public class OrdDetailDAO implements OrdDetailDAO_interface{

	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
//	private static DataSource ds = null;
//	static {
//		try {
//			
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
		private static final String INSERT_STMT = 
			"INSERT INTO orddetail (ordId, roomAuto, roomNumber) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT orddetailId, ordId, roomAuto, roomNumber FROM orddetail order by orddetailId";
		private static final String GET_ONE_STMT = 
			"SELECT orddetailId, ordId, roomAuto, roomNumber FROM orddetail where orddetailId = ?";
		private static final String GET_ORD_STMT =
			"SELECT orddetailId, ordId, roomAuto, roomNumber FROM orddetail where ordId = ?";
		private static final String DELETE = 
			"DELETE FROM orddetail where orddetailId = ?";
		private static final String UPDATE = 
			"UPDATE orddetail set ordId=?, roomAuto=?, roomNumber=? where orddetailId=?";
		private static final String GET_NONRESERVED=
			"SELECT nonreserved FROM room where roomAuto=?";
		private static final String UPDATE_NONRESERVED = 
			"UPDATE room set nonreserved=? where roomAuto=?";

		
	
	@Override
	public void insert(OrdDetailVO orddetailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, orddetailVO.getOrdId());
			pstmt.setInt(2, orddetailVO.getRoomAuto());
			pstmt.setInt(3, orddetailVO.getRoomNumber());

			pstmt.executeUpdate();
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public void update(OrdDetailVO orddetailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orddetailVO.getOrdId());
			pstmt.setInt(2, orddetailVO.getRoomAuto());
			pstmt.setInt(3, orddetailVO.getRoomNumber());
			pstmt.setInt(4, orddetailVO.getOrddetailId());

//			pstmt.executeUpdate();
			java.sql.Statement stmt=con.createStatement();
			stmt.executeUpdate("set auto_increment_increment=1;");
			pstmt.executeUpdate();
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public void updateNonreserved(OrdDetailVO orddetailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NONRESERVED );

			pstmt.setInt(1, orddetailVO.getNonreserved());
			pstmt.setInt(2, orddetailVO.getRoomAuto());

//			pstmt.executeUpdate();
			java.sql.Statement stmt=con.createStatement();
			stmt.executeUpdate("set auto_increment_increment=1;");
			pstmt.executeUpdate();
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public void delete(Integer orddetailId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orddetailId);

			pstmt.executeUpdate();

		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public List<OrdDetailVO> findByOrdId(Integer ordId) {
		
		List<OrdDetailVO> list = new ArrayList<OrdDetailVO>();
		OrdDetailVO orddetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORD_STMT);
			pstmt.setInt(1, ordId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orddetailVO = new OrdDetailVO();
				
				
				if(rs.getInt("roomNumber") != 0) {
					orddetailVO.setOrddetailId(rs.getInt("orddetailId"));
					orddetailVO.setOrdId(rs.getInt("ordId"));
				if(rs.getInt("roomAuto") == 4001) {
					orddetailVO.setRoomName("單人房");
				}
				if(rs.getInt("roomAuto") == 4002) {
					orddetailVO.setRoomName("雙人房");
				}
				if(rs.getInt("roomAuto") ==4003) {
					orddetailVO.setRoomName("四人房");
				}
				
//				switch(rs.getInt("roomAuto")%10) {
//				case 1 :
//					orddetailVO.setRoomAuto(1);
//					break;
//				case 2 :
//					orddetailVO.setRoomAuto(2);
//					break;
//				case 3 :
//					orddetailVO.setRoomAuto(4);
//					break;
//					
//					default :
//					break;
//				}
				
				orddetailVO.setRoomNumber(rs.getInt("roomNumber"));
				list.add(orddetailVO);
				}
			}

		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		
		
		
		
		
//		OrdDetailVO orddetailVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
////			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ORD_STMT);
//
//			pstmt.setInt(1, OrdId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				orddetailVO = new OrdDetailVO();
//				orddetailVO.setOrdId(rs.getInt("ordId"));
//				orddetailVO.setRoomAuto(rs.getInt("roomAuto"));
//				orddetailVO.setRoomNumber(rs.getInt("roomNumber"));
//				orddetailVO.setOrddetailId(rs.getInt("orddetailId"));
//				
//			}
//
//		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		}
//		catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
//		return orddetailVO;
	}

	@Override
	public OrdDetailVO findByPrimaryKey(Integer orddetailId) {

		OrdDetailVO orddetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orddetailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orddetailVO = new OrdDetailVO();
				orddetailVO.setOrdId(rs.getInt("ordId"));
				orddetailVO.setRoomAuto(rs.getInt("roomAuto"));
				orddetailVO.setRoomNumber(rs.getInt("roomNumber"));
				orddetailVO.setOrddetailId(rs.getInt("orddetailId"));
				
			}

		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		return orddetailVO;
	}

	@Override
	public OrdDetailVO findByRoomAuto(Integer roomAuto) {

		OrdDetailVO orddetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NONRESERVED);

			pstmt.setInt(1, roomAuto);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orddetailVO = new OrdDetailVO();
				orddetailVO.setNonreserved(rs.getInt("nonreserved"));
//				Integer nonreserved = rs.getInt("nonreserved");
			}

		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		return orddetailVO;
	}
	
	@Override
	public List<OrdDetailVO> getAll() {

		List<OrdDetailVO> list = new ArrayList<OrdDetailVO>();
		OrdDetailVO orddetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orddetailVO = new OrdDetailVO();
				orddetailVO.setOrddetailId(rs.getInt("orddetailId"));
				orddetailVO.setOrdId(rs.getInt("ordId"));
				orddetailVO.setRoomAuto(rs.getInt("roomAuto"));
				orddetailVO.setRoomNumber(rs.getInt("roomNumber"));
	
				list.add(orddetailVO); 
			}

		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
