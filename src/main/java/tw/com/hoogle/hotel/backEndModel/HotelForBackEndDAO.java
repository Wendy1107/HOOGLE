package tw.com.hoogle.hotel.backEndModel;

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


public class HotelForBackEndDAO implements HotelForBackEndDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_HotelbyState_STMT = "select hotelId,hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelState from hotel where hotelState = 2";

	
	@Override
	public List<HotelVO> findRegisterHotel() {
		
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HotelbyState_STMT);
			System.out.println("Connecting to database successfully findRegisterHotel! ");

//			pstmt.setInt(1, hotelState);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				hotelVO = new HotelVO();
				hotelVO.setHotelId(rs.getInt("hotelId"));
				hotelVO.setHotelEmail(rs.getString("hotelEmail"));
				hotelVO.setHotelPassword(rs.getString("hotelPassword"));
				hotelVO.setHotelName(rs.getString("hotelName"));
				hotelVO.setHotelPhone(rs.getString("hotelPhone"));
				hotelVO.setHotelPrincipal(rs.getString("hotelPrincipal"));
				hotelVO.setHotelTaxid(rs.getString("hotelTaxid"));
				hotelVO.setHotelState(rs.getInt("hotelState"));
				list.add(hotelVO);
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
