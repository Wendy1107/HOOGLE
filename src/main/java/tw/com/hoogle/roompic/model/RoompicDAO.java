package tw.com.hoogle.roompic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoompicDAO implements RoompicDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO roompic (roomAuto,roompicPic) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT roompicId,roomAuto,roompicPic FROM roompic";
	private static final String GET_ONE_STMT = "SELECT roompicId,roomAuto,roompicPic FROM roompic where roompicId = ?";
	private static final String DELETE = "DELETE FROM roompic where roompicId = ?";
	private static final String UPDATE = "UPDATE roompic set roomAuto=?, roompicPic=? where roompicId = ?";
	@Override
	public void insert(RoompicVO roompicVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, roompicVO.getRoomAuto());
			pstmt.setString(2, roompicVO.getRoomType());
			pstmt.setBytes(3, roompicVO.getRoompicPic());


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
	public void update(RoompicVO roompicVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roompicVO.getRoomAuto());
			pstmt.setString(2, roompicVO.getRoomType());
			pstmt.setBytes(3, roompicVO.getRoompicPic());
			pstmt.setInt(4, roompicVO.getRoompicId());

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
	public void delete(Integer roompicId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roompicId);

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
	public RoompicVO findByPrimaryKey(Integer roompicId) {
		
		RoompicVO roompicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roompicId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				roompicVO = new RoompicVO();
				roompicVO.setRoompicId(rs.getInt("roompicId"));
				roompicVO.setRoomAuto(rs.getInt("roomAuto"));
				roompicVO.setRoompicPic(rs.getBytes("roompicPic"));
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
		
		return roompicVO;
		
	}
	@Override
	public List<RoompicVO> getAll() {
		
		List<RoompicVO> list = new ArrayList<RoompicVO>();
		RoompicVO roompicVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				roompicVO = new RoompicVO();
				roompicVO.setRoompicId(rs.getInt("roompicId"));
				roompicVO.setRoomAuto(rs.getInt("roomAuto"));
				roompicVO.setRoompicPic(rs.getBytes("roompicPic"));
				
				list.add(roompicVO); // Store the row in the list
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
	public int updatePicByRoomAuto(RoompicVO vo) {
		final String sql = "update roompic set roompicPic = ? where roomauto = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
//			Connection conn = DriverManager.getConnection(url, userid, passwd);
//			PreparedStatement pstmt = conn.prepareStatement(sql);
		
			pstmt.setBytes(1, vo.getRoompicPic());
			pstmt.setInt(2, vo.getRoomAuto());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}