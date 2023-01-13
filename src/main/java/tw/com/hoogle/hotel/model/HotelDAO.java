package tw.com.hoogle.hotel.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class HotelDAO implements HotelDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO hotel (hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelState) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT hotelId,hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelCounty,hotelAddress,hotelType,hotelNotice,hotelQa,hotelIntroduction,hotelState FROM hotel";
	private static final String GET_ONE_STMT = "SELECT hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelCounty,hotelAddress,hotelType,hotelNotice,hotelQa,hotelIntroduction,hotelState FROM hotel where hotelId = ?";
	private static final String DELETE = "DELETE FROM hotel where hotelId = ?";
	private static final String UPDATE = "UPDATE hotel set hotelEmail=?, hotelPassword=?, hotelName=?, hotelPhone=?, hotelPrincipal=?, hotelTaxid=?, hotelCounty=?, hotelAddress=?, hotelType=?, hotelNotice=?, hotelQa=?, hotelIntroduction=?, hotelState=? where hotelId = ?";
	private static final String GET_ONE_HOTEL = "SELECT hotelId,hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelCounty,hotelAddress,hotelType,hotelNotice,hotelQa,hotelIntroduction,hotelState FROM hotel where hotelEmail = ? and hotelTaxid = ?";
	private static final String GET_EMAILbyHotel_STMT = "select hotelId,hotelEmail,hotelPassword,hotelName,hotelPhone,hotelPrincipal,hotelTaxid,hotelCounty,hotelAddress,hotelType,hotelNotice,hotelQa,hotelIntroduction,hotelState from hotel where hotelEmail = ?";

	@Override
	public void insert(HotelVO hotelVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("HotelDAO insert");

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, hotelVO.getHotelEmail());
			pstmt.setString(2, hotelVO.getHotelPassword());
			pstmt.setString(3, hotelVO.getHotelName());
			pstmt.setString(4, hotelVO.getHotelPhone());
			pstmt.setString(5, hotelVO.getHotelPrincipal());
			pstmt.setString(6, hotelVO.getHotelTaxid());
			pstmt.setInt(7, hotelVO.getHotelState());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(HotelVO hotelVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, hotelVO.getHotelEmail());
			pstmt.setString(2, hotelVO.getHotelPassword());
			pstmt.setString(3, hotelVO.getHotelName());
			pstmt.setString(4, hotelVO.getHotelPhone());
			pstmt.setString(5, hotelVO.getHotelPrincipal());
			pstmt.setString(6, hotelVO.getHotelTaxid());
			pstmt.setString(7, hotelVO.getHotelCounty());
			pstmt.setString(8, hotelVO.getHotelAddress());
			pstmt.setString(9, hotelVO.getHotelType());
			pstmt.setString(10, hotelVO.getHotelNotice());
			pstmt.setString(11, hotelVO.getHotelQa());
			pstmt.setString(12, hotelVO.getHotelIntroduction());
			pstmt.setInt(13, hotelVO.getHotelState());
			pstmt.setInt(14, hotelVO.getHotelId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(Integer hotelId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, hotelId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public HotelVO findByPrimaryKey(Integer hotelId) {
		
		HotelVO hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, hotelId);

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
				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
				hotelVO.setHotelAddress(rs.getString("hotelAddress"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelNotice(rs.getString("hotelNotice"));
				hotelVO.setHotelQa(rs.getString("hotelQa"));
				hotelVO.setHotelIntroduction(rs.getString("hotelIntroduction"));
				hotelVO.setHotelState(rs.getInt("hotelState"));
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
		
		return hotelVO;
	}

	@Override
	public List<HotelVO> getAll() {
		
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				hotelVO = new HotelVO();
				hotelVO.setHotelId(rs.getInt("hotelId"));
				hotelVO.setHotelEmail(rs.getString("hotelEmail"));
				hotelVO.setHotelPassword(rs.getString("hotelPassword"));
				hotelVO.setHotelName(rs.getString("hotelName"));
				hotelVO.setHotelPhone(rs.getString("hotelPhone"));
				hotelVO.setHotelPrincipal(rs.getString("hotelPrincipal"));
				hotelVO.setHotelTaxid(rs.getString("hotelTaxid"));
				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
				hotelVO.setHotelAddress(rs.getString("hotelAddress"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelNotice(rs.getString("hotelNotice"));
				hotelVO.setHotelQa(rs.getString("hotelQa"));
				hotelVO.setHotelIntroduction(rs.getString("hotelIntroduction"));
				hotelVO.setHotelState(rs.getInt("hotelState"));
				list.add(hotelVO); // Store the row in the list
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

	@Override
	public HotelVO findByHotelEmailandTaxid(String hotelEmail, String hotelTaxid) {
		
		HotelVO hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_HOTEL);
			System.out.println("Connecting to database successfully findByHotelEmail! ");

			pstmt.setString(1, hotelEmail);
			pstmt.setString(2, hotelTaxid);

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
				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
				hotelVO.setHotelAddress(rs.getString("hotelAddress"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelNotice(rs.getString("hotelNotice"));
				hotelVO.setHotelQa(rs.getString("hotelQa"));
				hotelVO.setHotelIntroduction(rs.getString("hotelIntroduction"));
				hotelVO.setHotelState(rs.getInt("hotelState"));
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
		
		return hotelVO;
	}

	@Override
	public String pwdhash(String password) {
		try {
			Base64.Encoder enc = Base64.getEncoder();
			String newPwd = enc.encodeToString(password.getBytes());
			System.out.println("加密後的密碼: ====="+newPwd+"=====");
			return newPwd;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HotelVO findByHotelEmail(String hotelEmail) {
		
		HotelVO hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMAILbyHotel_STMT);
			System.out.println("Connecting to database successfully findByHotelEmail! ");

			pstmt.setString(1, hotelEmail);
			

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
				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
				hotelVO.setHotelAddress(rs.getString("hotelAddress"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelNotice(rs.getString("hotelNotice"));
				hotelVO.setHotelQa(rs.getString("hotelQa"));
				hotelVO.setHotelIntroduction(rs.getString("hotelIntroduction"));
				hotelVO.setHotelState(rs.getInt("hotelState"));
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
		
		return hotelVO;
	}
}
