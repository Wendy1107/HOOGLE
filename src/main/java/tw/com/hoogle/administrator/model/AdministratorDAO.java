package tw.com.hoogle.administrator.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdministratorDAO implements AdministratorDAO_interface {
	
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
			"INSERT INTO administrator (administratorName, administratorAccount, "
			                            + "administratorPassword, administratorDominate, "
			                            + "newsDominate, hotelDominate, userDominate, "
			                            + "administratorHiredate) "
			                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT administratorId, administratorName, administratorAccount, administratorPassword,"
			+ "administratorDominate, newsDominate, hotelDominate, userDominate, administratorHiredate "
			+ "FROM administrator order by administratorId";
		private static final String GET_ONE_STMT = 
			"SELECT administratorId, administratorName, administratorAccount, administratorPassword,"
			+ "administratorDominate, newsDominate, hotelDominate, userDominate, administratorHiredate "
			+ "FROM administrator where administratorId = ?";
		private static final String DELETE = 
			"DELETE FROM administrator where administratorId = ?";		// 用不到，如果管理者離職的話，權限全部修改為false
		private static final String UPDATE = 
			"UPDATE administrator set administratorName=?, administratorAccount=?, administratorPassword=?, "
			+ "administratorDominate=?, newsDominate=?, hotelDominate=?, "
			+ "userDominate=?, administratorHiredate=? where administratorId = ?";
		
		private static final String LOGIN_ACCOUNT = "SELECT administratorAccount FROM administrator "
				+ "where administratorAccount = ?";
		
		private static final String LOGIN_PASSWORD = "SELECT administratorPassword FROM administrator "
				+ "where administratorAccount = ?";
		
		private static final String DISABLE = "UPDATE administrator set administratorDominate = ?, "
				+ "newsDominate = ?, hotelDominate = ?, userDominate = ? "
				+ "where administratorId = ?;";
		
		private static final String SEARCH_PERMISSIONS = "SELECT administratorAccount, administratorDominate, "
				+ "newsDominate, hotelDominate, userDominate FROM administrator "
				+ "where administratorAccount = ?";

		
		
		@Override
		public String findAccount(String administratorAccount) {
			AdministratorVO administratorVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String adminAccount = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(LOGIN_ACCOUNT);

				pstmt.setString(1, administratorAccount);

				rs = pstmt.executeQuery();

//				while (rs.next()) {
//					// empVo 也稱為 Domain objects
//					administratorVO = new AdministratorVO();
//					administratorVO.setAdministratorAccount(rs.getString("administratorAccount"));
//				}
				if (rs.next()) {
					adminAccount = rs.getString(1);
					administratorVO = new AdministratorVO();
					administratorVO.setAdministratorAccount(rs.getString("administratorAccount"));
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
			return adminAccount;
		}
		
		
		

		@Override
		public String matchAccountPassword(String administratorAccount) {
			HashMap<String, String> hashMap = new HashMap();
			AdministratorVO administratorVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String adminPassword = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(LOGIN_PASSWORD);

				pstmt.setString(1, administratorAccount);

				rs = pstmt.executeQuery();

				
				if (rs.next()) {
					adminPassword = rs.getString(1);
					administratorVO = new AdministratorVO();
					administratorVO.setAdministratorAccount(rs.getString("administratorPassword"));
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
			return adminPassword;
		}
		
		


		@Override
		public void disable(AdministratorVO administratorVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DISABLE);

				pstmt.setBoolean(1, administratorVO.getAdministratorDominate());
				pstmt.setBoolean(2, administratorVO.getNewsDominate());
				pstmt.setBoolean(3, administratorVO.getHotelDominate());
				pstmt.setBoolean(4, administratorVO.getUserDominate());
				pstmt.setInt(5, administratorVO.getAdministratorId());

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
		public void insert(AdministratorVO administratorVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, administratorVO.getAdministratorName());
				pstmt.setString(2, administratorVO.getAdministratorAccount());
				pstmt.setString(3, administratorVO.getAdministratorPassword());
				pstmt.setBoolean(4, administratorVO.getAdministratorDominate());
				pstmt.setBoolean(5, administratorVO.getNewsDominate());
				pstmt.setBoolean(6, administratorVO.getHotelDominate());
				pstmt.setBoolean(7, administratorVO.getUserDominate());
				pstmt.setDate(8, administratorVO.getAdministratorHiredate());	// 這樣是存建立日期嗎?
				

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
		public void update(AdministratorVO administratorVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, administratorVO.getAdministratorName());
				pstmt.setString(2, administratorVO.getAdministratorAccount());
				pstmt.setString(3, administratorVO.getAdministratorPassword());
				pstmt.setBoolean(4, administratorVO.getAdministratorDominate());
				pstmt.setBoolean(5, administratorVO.getNewsDominate());
				pstmt.setBoolean(6, administratorVO.getHotelDominate());
				pstmt.setBoolean(7, administratorVO.getUserDominate());
				pstmt.setDate(8, administratorVO.getAdministratorHiredate());
				pstmt.setInt(9, administratorVO.getAdministratorId());

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
		public void delete(Integer administratorId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, administratorId);

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
		public AdministratorVO findByPrimaryKey(Integer administratorId) {

			AdministratorVO administratorVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, administratorId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					administratorVO = new AdministratorVO();
					administratorVO.setAdministratorId(rs.getInt("administratorId"));
					administratorVO.setAdministratorName(rs.getString("administratorName"));
					administratorVO.setAdministratorAccount(rs.getString("administratorAccount"));
					administratorVO.setAdministratorPassword(rs.getString("administratorPassword"));
					administratorVO.setAdministratorDominate(rs.getBoolean("administratorDominate"));
					administratorVO.setNewsDominate(rs.getBoolean("newsDominate"));
					administratorVO.setHotelDominate(rs.getBoolean("hotelDominate"));
					administratorVO.setUserDominate(rs.getBoolean("userDominate"));
					administratorVO.setAdministratorHiredate(rs.getDate("administratorHiredate"));
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
			return administratorVO;
		}

		@Override
		public List<AdministratorVO> getAll() {
			List<AdministratorVO> list = new ArrayList<AdministratorVO>();
			AdministratorVO administratorVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					administratorVO = new AdministratorVO();
					administratorVO.setAdministratorId(rs.getInt("administratorId"));
					administratorVO.setAdministratorName(rs.getString("administratorName"));
					administratorVO.setAdministratorAccount(rs.getString("administratorAccount"));
					administratorVO.setAdministratorPassword(rs.getString("administratorPassword"));
					administratorVO.setAdministratorDominate(rs.getBoolean("administratorDominate"));
					administratorVO.setNewsDominate(rs.getBoolean("newsDominate"));
					administratorVO.setHotelDominate(rs.getBoolean("hotelDominate"));
					administratorVO.setUserDominate(rs.getBoolean("userDominate"));
					administratorVO.setAdministratorHiredate(rs.getDate("administratorHiredate"));
					list.add(administratorVO); // Store the row in the list
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
		public AdministratorVO searchPermissionsByAccount(String administratorAccount) {

			AdministratorVO administratorVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SEARCH_PERMISSIONS);

				pstmt.setString(1, administratorAccount);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					administratorVO = new AdministratorVO();
					administratorVO.setAdministratorAccount(rs.getString("administratorAccount"));
					administratorVO.setAdministratorDominate(rs.getBoolean("administratorDominate"));
					administratorVO.setNewsDominate(rs.getBoolean("newsDominate"));
					administratorVO.setHotelDominate(rs.getBoolean("hotelDominate"));
					administratorVO.setUserDominate(rs.getBoolean("userDominate"));
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
			return administratorVO;
		}

}
