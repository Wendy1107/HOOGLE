package tw.com.hoogle.servicelist.model;

import java.util.*;
import java.sql.*;

public class ServiceListJDBCDAO implements ServiceListDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO serviceList (hotelId, serviceId) "
									+ "VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT serviceListId, hotelId, serviceId FROM serviceList order by serviceListId";
	private static final String GET_ONE_STMT = 
		"SELECT serviceListId, hotelId, serviceId FROM serviceList where serviceListId = ?";
	private static final String DELETE = 
		"DELETE FROM serviceList where serviceListId = ?";
	private static final String UPDATE = 
		"UPDATE serviceList set hotelId=?, serviceId=?  where serviceListId = ?";

	@Override
	public void insert(ServiceListVO serviceListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, serviceListVO.getHotelId());
			pstmt.setInt(2, serviceListVO.getServiceId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ServiceListVO serviceListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, serviceListVO.getHotelId());
			pstmt.setInt(2, serviceListVO.getServiceId());
			pstmt.setInt(3, serviceListVO.getServiceListId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer serviceListId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, serviceListId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public ServiceListVO findByPrimaryKey(Integer serviceListId) {

		ServiceListVO serviceListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, serviceListId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				serviceListVO = new ServiceListVO();
				serviceListVO.setServiceListId(rs.getInt("serviceListId"));
				serviceListVO.setHotelId(rs.getInt("hotelId"));
				serviceListVO.setServiceId(rs.getInt("serviceId"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return serviceListVO;
	}

	@Override
	public List<ServiceListVO> getAll() {
		List<ServiceListVO> list = new ArrayList<ServiceListVO>();
		ServiceListVO serviceListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				serviceListVO = new ServiceListVO();
				serviceListVO.setServiceListId(rs.getInt("serviceListId"));
				serviceListVO.setHotelId(rs.getInt("hotelId"));
				serviceListVO.setServiceId(rs.getInt("serviceId"));
				list.add(serviceListVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		ServiceListJDBCDAO dao = new ServiceListJDBCDAO();

		// 新增
//		ServiceListVO serviceListVO1 = new ServiceListVO();
//		serviceListVO1.setHotelId(3001);
//		serviceListVO1.setServiceId(9002);
//		dao.insert(serviceListVO1);

		// 修改
//		ServiceListVO serviceListVO2 = new ServiceListVO();
//		serviceListVO2.setHotelId(3001);
//		serviceListVO2.setServiceId(9002);		
//		serviceListVO2.setServiceListId(8001);
//		dao.update(serviceListVO2);

//		// 刪除
//		dao.delete(8001);

//		// 查詢
//		ServiceListVO serviceListVO3 = dao.findByPrimaryKey(8001);
//		System.out.print(serviceListVO3.getServiceListId() + ",");
//		System.out.print(serviceListVO3.getHotelId() + ",");
//		System.out.print(serviceListVO3.getServiceId() + ",");
//		System.out.println("---------------------");

//		// 查詢
		List<ServiceListVO> list = dao.getAll();
		for (ServiceListVO aAdministrator : list) {
			System.out.print(aAdministrator.getServiceListId() + ",");
			System.out.print(aAdministrator.getHotelId() + ",");
			System.out.print(aAdministrator.getServiceId() + ",");
			System.out.println();
		}
	}

	@Override
	public void insert2(ServiceListVO serviceListVO, Connection con) {
		// TODO Auto-generated method stub
		
	}

}
