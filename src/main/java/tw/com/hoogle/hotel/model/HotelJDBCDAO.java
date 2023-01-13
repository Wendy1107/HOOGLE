//package tw.com.hoogle.hotel.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HotelJDBCDAO implements HotelDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
//
//	private static final String INSERT_STMT = "INSERT INTO hotel (hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid) VALUES (?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT hotelId,hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelCounty,hotelAddress,hotelType,hotelNotice,hotelQa,hotelIntroduction,hotelState FROM hotel";
//	private static final String GET_ONE_STMT = "SELECT hotelId,hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelCounty,hotelAddress,hotelType,hotelNotice,hotelQa,hotelIntroduction,hotelState FROM hotel where hotelId = ?";
//	private static final String DELETE = "DELETE FROM hotel where hotelId = ?";
//	private static final String UPDATE = "UPDATE hotel set hotelEmail=?, hotelPassword=?, hotelName=?, hotelPhone=?, hotelPrincipal=?, hotelTaxid=?, hotelCounty=?, hotelAddress=?, hotelType=?, hotelNotice=?, hotelQa=?, hotelIntroduction=?, hotelState=? where hotelId = ?";
//
//	@Override
//	public void insert(HotelVO hotelVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, hotelVO.getHotelEmail());
//			pstmt.setString(2, hotelVO.getHotelPassword());
//			pstmt.setString(3, hotelVO.getHotelName());
//			pstmt.setString(4, hotelVO.getHotelPhone());
//			pstmt.setString(5, hotelVO.getHotelPrincipal());
//			pstmt.setString(6, hotelVO.getHotelTaxid());
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
//	}
//
//	@Override
//	public void update(HotelVO hotelVO) {
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
//			pstmt.setString(1, hotelVO.getHotelEmail());
//			pstmt.setString(2, hotelVO.getHotelPassword());
//			pstmt.setString(3, hotelVO.getHotelName());
//			pstmt.setString(4, hotelVO.getHotelPhone());
//			pstmt.setString(5, hotelVO.getHotelPrincipal());
//			pstmt.setString(6, hotelVO.getHotelTaxid());
//			pstmt.setString(7, hotelVO.getHotelCounty());
//			pstmt.setString(8, hotelVO.getHotelAddress());
//			pstmt.setString(9, hotelVO.getHotelType());
//			pstmt.setString(10, hotelVO.getHotelNotice());
//			pstmt.setString(11, hotelVO.getHotelQa());
//			pstmt.setString(12, hotelVO.getHotelIntroduction());
//			pstmt.setInt(13, hotelVO.getHotelState());
//			pstmt.setInt(14, hotelVO.getHotelId());
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
//	@Override
//	public void delete(Integer hotelId) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, hotelId);
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
//	}
//
//	@Override
//	public HotelVO findByPrimaryKey(Integer hotelId) {
//
//		HotelVO hotelVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, hotelId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				hotelVO = new HotelVO();
//				hotelVO.setHotelId(rs.getInt("hotelId"));
//				hotelVO.setHotelEmail(rs.getString("hotelEmail"));
//				hotelVO.setHotelPassword(rs.getString("hotelPassword"));
//				hotelVO.setHotelName(rs.getString("hotelName"));
//				hotelVO.setHotelPhone(rs.getString("hotelPhone"));
//				hotelVO.setHotelPrincipal(rs.getString("hotelPrincipal"));
//				hotelVO.setHotelTaxid(rs.getString("hotelTaxid"));
//				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
//				hotelVO.setHotelAddress(rs.getString("hotelAddress"));
//				hotelVO.setHotelType(rs.getString("hotelType"));
//				hotelVO.setHotelNotice(rs.getString("hotelNotice"));
//				hotelVO.setHotelQa(rs.getString("hotelQa"));
//				hotelVO.setHotelIntroduction(rs.getString("hotelIntroduction"));
//				hotelVO.setHotelState(rs.getInt("hotelState"));
//			}
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
//		return hotelVO;
//	}
//
//	@Override
//	public List<HotelVO> getAll() {
//
//		List<HotelVO> list = new ArrayList<HotelVO>();
//		HotelVO hotelVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				hotelVO = new HotelVO();
//				hotelVO.setHotelId(rs.getInt("hotelId"));
//				hotelVO.setHotelEmail(rs.getString("hotelEmail"));
//				hotelVO.setHotelPassword(rs.getString("hotelPassword"));
//				hotelVO.setHotelName(rs.getString("hotelName"));
//				hotelVO.setHotelPhone(rs.getString("hotelPhone"));
//				hotelVO.setHotelPrincipal(rs.getString("hotelPrincipal"));
//				hotelVO.setHotelTaxid(rs.getString("hotelTaxid"));
//				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
//				hotelVO.setHotelAddress(rs.getString("hotelAddress"));
//				hotelVO.setHotelType(rs.getString("hotelType"));
//				hotelVO.setHotelNotice(rs.getString("hotelNotice"));
//				hotelVO.setHotelQa(rs.getString("hotelQa"));
//				hotelVO.setHotelIntroduction(rs.getString("hotelIntroduction"));
//				hotelVO.setHotelState(rs.getInt("hotelState"));
//				list.add(hotelVO); // Store the row in the list
//			}
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
//		return list;
//	}
//
//	public static void main(String[] args) {
//
//		HotelJDBCDAO dao = new HotelJDBCDAO();
//
//		// 新增test
////		HotelVO insertTest = new HotelVO();
////		insertTest.setHotelEmail("hoteltest1@gmail.com");
////		insertTest.setHotelPassword("hotel123");
////		insertTest.setHotelName("hotelname");
////		insertTest.setHotelPhone("0912345678");
////		insertTest.setHotelPrincipal("負責人");
////		insertTest.setHotelTaxid("11731806");
////		dao.insert(insertTest);
//
//		// 修改test
////		HotelVO updateTest = new HotelVO();
////		updateTest.setHotelId(1);
////		updateTest.setHotelEmail("yang123@gmail.com");
////		updateTest.setHotelPassword("5678");
////		updateTest.setHotelName("yang");
////		updateTest.setHotelPhone("0911111111");
////		updateTest.setHotelPrincipal("負責人3");
////		updateTest.setHotelTaxid("12345678");
////		updateTest.setHotelCounty("台北市");
////		updateTest.setHotelAddress("中山區中山北路四段1號");
////		updateTest.setHotelType("飯店");
////		updateTest.setHotelNotice("飯店內不可抽菸");
////		updateTest.setHotelQa("Q&A");
////		updateTest.setHotelIntroduction("飯店簡介");
////		updateTest.setHotelState(1);
////		dao.update(updateTest);
//
////		 刪除test
////		dao.delete(1);
//
//		// 查詢單一項test
////		HotelVO selectTest = dao.findByPrimaryKey(1);
////		System.out.println(selectTest.getHotelId() + ",");
////		System.out.println(selectTest.getHotelEmail() + ",");
////		System.out.println(selectTest.getHotelPassword() + ",");
////		System.out.println(selectTest.getHotelName() + ",");
////		System.out.println(selectTest.getHotelPhone() + ",");
////		System.out.println(selectTest.getHotelTaxid() + ",");
////		System.out.println(selectTest.getHotelCounty() + ",");
////		System.out.println(selectTest.getHotelAddress() + ",");
////		System.out.println(selectTest.getHotelType() + ",");
////		System.out.println(selectTest.getHotelNotice() + ",");
////		System.out.println(selectTest.getHotelQa() + ",");
////		System.out.println(selectTest.getHotelIntroduction() + ",");
////		System.out.println(selectTest.getHotelState() + ",");
////		System.out.println("------------------");
//
//		// 查詢總表
//
////		List<HotelVO> list = dao.getAll();
////		for (HotelVO selectAllTest : list) {
////			System.out.println(selectAllTest.getHotelId() + ",");
////			System.out.println(selectAllTest.getHotelEmail() + ",");
////			System.out.println(selectAllTest.getHotelPassword() + ",");
////			System.out.println(selectAllTest.getHotelName() + ",");
////			System.out.println(selectAllTest.getHotelPhone() + ",");
////			System.out.println(selectAllTest.getHotelTaxid() + ",");
////			System.out.println(selectAllTest.getHotelCounty() + ",");
////			System.out.println(selectAllTest.getHotelAddress() + ",");
////			System.out.println(selectAllTest.getHotelType() + ",");
////			System.out.println(selectAllTest.getHotelNotice() + ",");
////			System.out.println(selectAllTest.getHotelQa() + ",");
////			System.out.println(selectAllTest.getHotelIntroduction() + ",");
////			System.out.println(selectAllTest.getHotelState() + ",\t");
////			System.out.println();
////		}
//
//	}
//
//}
