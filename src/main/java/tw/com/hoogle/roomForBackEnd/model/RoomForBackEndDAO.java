package tw.com.hoogle.roomForBackEnd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.com.hoogle.hotel.model.HotelVO;
import tw.com.hoogle.roomForBackEnd.*;

public class RoomForBackEndDAO implements RoomForBackEndDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ROOMDETAIL = "SELECT hotel.hotelId,hotelName,roomAuto,roomName,roomPrice,roomTotal,nonreserved "
			                                     + "FROM room as room "
			                                     + "join hotel as hotel on room.hotelId = hotel.hotelId "
			                                     + "WHERE hotel.hotelId = ?";
	
	public List<RoomForBackEndVO> findRoomDetail(Integer hotelId){
		System.out.println("====11====");
		List<RoomForBackEndVO> list = new ArrayList<RoomForBackEndVO>();
		RoomForBackEndVO roomForBackEndVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ROOMDETAIL);
			System.out.println("Connecting to database successfully findRoomDetail! ");

			pstmt.setInt(1, hotelId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				
				roomForBackEndVO = new RoomForBackEndVO();
				roomForBackEndVO.setHotelId(rs.getInt("hotelId"));
				roomForBackEndVO.setHotelName(rs.getString("hotelName"));
				roomForBackEndVO.setRoomAuto(rs.getInt("roomAuto"));
				roomForBackEndVO.setRoomName(rs.getString("roomName"));
				roomForBackEndVO.setRoomPrice(rs.getInt("roomPrice"));
				roomForBackEndVO.setRoomTotal(rs.getInt("roomTotal"));
				roomForBackEndVO.setNonreserved(rs.getInt("nonreserved"));
				System.out.println("==dao==");
				list.add(roomForBackEndVO);
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
