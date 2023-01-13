package tw.com.hoogle.finStm.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FinStmDAO implements FinStmDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_STMT = "SELECT o.ordDate, o.hotelId, o.hotelName, roomPrice, o.userId "
			+ "FROM (ord o join orddetail od on o.ordId = od.ordId) "
			+ "join room r on od.roomAuto = r.roomAuto "
			+ "order by o.ordDate desc;";
	
	private static final String GET_BY_HOTELID = "SELECT o.ordDate, o.hotelId, o.hotelName, roomPrice, o.userId "
			+ "FROM (ord o join orddetail od on o.ordId = od.ordId) "
			+ "join room r on od.roomAuto = r.roomAuto "
			+ "where o.ordDate between ? and ? "
			+ "order by o.ordDate DESC";
	
	@Override
	public List<FinStmVO> getAll(){
		List<FinStmVO> list = new ArrayList<FinStmVO>();
		FinStmVO finStatementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				finStatementVO = new FinStmVO();
				finStatementVO.setOrdDate(rs.getDate("ordDate"));
				finStatementVO.setHotelId(rs.getInt("hotelId"));
				finStatementVO.setHotelName(rs.getString("hotelName"));
				finStatementVO.setRoomPrice(rs.getInt("roomPrice"));
				finStatementVO.setUserId(rs.getInt("userId"));
				list.add(finStatementVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
		
	}
	
	
	public List<FinStmVO> findByDate(Date dateFrom, Date dateEnd){
		List<FinStmVO> list = new ArrayList<FinStmVO>();
		FinStmVO finStatementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_HOTELID);
			
			pstmt.setDate(1, dateFrom);
			pstmt.setDate(2, dateEnd);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				finStatementVO = new FinStmVO();
				finStatementVO.setOrdDate(rs.getDate("ordDate"));
				finStatementVO.setHotelId(rs.getInt("hotelId"));
				finStatementVO.setHotelName(rs.getString("hotelName"));
				finStatementVO.setRoomPrice(rs.getInt("roomPrice"));
				finStatementVO.setUserId(rs.getInt("userId"));

				list.add(finStatementVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
		
	}
	
	

}

