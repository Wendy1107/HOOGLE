//package tw.com.hoogle.otherhotel.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class OtherHotelJDBC implements OtherHotelDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
//
//	private static final String UPDATE = "UPDATE hotel set hotelEmail=?, hotelPassword=?, hotelName=?, hotelPhone=?, hotelPrincipal=?, hotelTaxid=?, hotelCounty=?, hotelAddress=?, hotelType=?, hotelNotice=?, hotelQa=?, hotelIntroduction=?, hotelState=? where hotelId = ?";
//
//	@Override
//	public void updateHotel(OtherHotelVO otherhotelVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, otherhotelVO.getHotelEmail());
//			pstmt.setString(2, otherhotelVO.getHotelPassword());
//			pstmt.setString(3, otherhotelVO.getHotelName());
//			pstmt.setString(4, otherhotelVO.getHotelPhone());
//			pstmt.setString(5, otherhotelVO.getHotelPrincipal());
//			pstmt.setString(6, otherhotelVO.getHotelTaxid());
//			pstmt.setString(7, otherhotelVO.getHotelCounty());
//			pstmt.setString(8, otherhotelVO.getHotelAddress());
//			pstmt.setString(9, otherhotelVO.getHotelType());
//			pstmt.setString(10, otherhotelVO.getHotelNotice());
//			pstmt.setString(11, otherhotelVO.getHotelQa());
//			pstmt.setString(12, otherhotelVO.getHotelIntroduction());
//			pstmt.setInt(13, otherhotelVO.getHotelState());
//			pstmt.setInt(14, otherhotelVO.getHotelId());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
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
//
//	}
//
//	public static void main(String[] args) {
//		//
//		OtherHotelJDBC dao = new OtherHotelJDBC();
//
//		// 修改test
//		OtherHotelVO updateTest = new OtherHotelVO();
//		updateTest.setHotelId(3010);
//		updateTest.setHotelEmail("yang123@gmail.com");
//		updateTest.setHotelPassword("1234");
//		updateTest.setHotelName("yang");
//		updateTest.setHotelPhone("090000000");
//		updateTest.setHotelPrincipal("負責人3");
//		updateTest.setHotelTaxid("12345678");
//		updateTest.setHotelCounty("台北市");
//		updateTest.setHotelAddress("中山區中山北路四段1號");
//		updateTest.setHotelType("飯店");
//		updateTest.setHotelNotice("飯店內不可抽菸");
//		updateTest.setHotelQa("Q&A");
//		updateTest.setHotelIntroduction("飯店簡介");
//		updateTest.setHotelState(1);
//		dao.updateHotel(updateTest);
//		System.out.println("修改成功");
//
//	}
//
//}
