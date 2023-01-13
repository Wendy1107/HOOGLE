package tw.com.hoogle.servicelist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ServiceListDAO implements ServiceListDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String INSERT_STMT = 
			"INSERT INTO serviceList (hotelId, serviceId) "
			                            + "VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT serviceListId, hotelId, serviceId FROM serviceList order by serviceListId";
		private static final String GET_ONE_STMT = 
			"SELECT serviceListId, hotelId, serviceId FROM serviceList where serviceListId = ?";
		private static final String DELETE = 
			"DELETE FROM serviceList where hotelId = ?";
		private static final String UPDATE = 
			"UPDATE serviceList set hotelId=?, serviceId=? where serviceListId = ?";

		@Override
		public void insert(ServiceListVO serviceListVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, serviceListVO.getServiceListId());
				pstmt.setInt(2, serviceListVO.getHotelId());
				pstmt.setInt(3, serviceListVO.getServiceId());

				pstmt.executeUpdate();

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, serviceListVO.getHotelId());
				pstmt.setInt(2, serviceListVO.getServiceId());
				pstmt.setInt(3, serviceListVO.getServiceListId());

				pstmt.executeUpdate();

				// Handle any driver errors
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
		
		public void insert2(ServiceListVO serviceListVO,Connection con) {

			PreparedStatement pstmt = null;

			try {

				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, serviceListVO.getHotelId());
				pstmt.setInt(2, serviceListVO.getServiceId());
//				pstmt.setInt(3, serviceListVO.getServiceListId());

				Statement stmt=	con.createStatement();
				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-ServiceListDAO-");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
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

				con = ds.getConnection();
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

				con = ds.getConnection();
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
