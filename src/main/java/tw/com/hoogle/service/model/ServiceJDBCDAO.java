package tw.com.hoogle.service.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceJDBCDAO implements ServiceDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO service (serviceName) VALUES (?)";
	private static final String UPDATE = "UPDATE service set serviceName=? where serviceId = ?";
	private static final String DELETE = "DELETE FROM service where serviceId = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM service where serviceId = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM service";
	
	@Override
	public void insert(ServiceVO serviceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, serviceVO.getServiceName());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(ServiceVO serviceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, serviceVO.getServiceName());
			pstmt.setInt(2, serviceVO.getServiceId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer serviceId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, serviceId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public ServiceVO findByPrimaryKey(Integer serviceId) {

		ServiceVO serviceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, serviceId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				serviceVO = new ServiceVO();
				serviceVO.setServiceId(rs.getInt("serviceId"));
				serviceVO.setServiceName(rs.getString("serviceName"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return serviceVO;

	}

	@Override
	public List<ServiceVO> getAll() {

		List<ServiceVO> list = new ArrayList<ServiceVO>();
		ServiceVO serviceVO = null;

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
				serviceVO = new ServiceVO();
				serviceVO.setServiceId(rs.getInt("serviceId"));
				serviceVO.setServiceName(rs.getString("serviceName"));
				list.add(serviceVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		ServiceJDBCDAO dao = new ServiceJDBCDAO();

		// 新增test
//		ServiceVO insertTest = new ServiceVO();
//		insertTest.setServiceName("會議室");
//		dao.insert(insertTest);

		// 修改test
//		ServiceVO updateTest = new ServiceVO();
//		updateTest.setServiceId(9007);
//		updateTest.setServiceName("停車場");
//		dao.update(updateTest);
//		System.out.println("修改ok");

		// 刪除test
//		dao.delete(9008);
//		System.out.println("修改ok");
		
		// 查詢單一項test
//		ServiceVO selectTest = dao.findByPrimaryKey(9007);
//		System.out.println(selectTest.getServiceId() + ",");
//		System.out.println(selectTest.getServiceName() + ",");
//		System.out.println("------------------");
	
		// 查詢總表

//		List<ServiceVO> list = dao.getAll();
//		for (ServiceVO selectAllTest : list) {
//			System.out.println(selectAllTest.getServiceId() + ",");
//			System.out.println(selectAllTest.getServiceName() + ",");
//			System.out.println();
//		}

	}}


