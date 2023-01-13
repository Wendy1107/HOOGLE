package tw.com.hoogle.commend.modelOLD;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class CommendDAO implements CommendDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}																	
	private static final String INSERT_STMT = "INSERT INTO commend (ordId,commendGrade,commendContent,commendDate) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE commend set commendGrade=?, commendContent=?, commendDate=? where commendAuto = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM commend where commendAuto = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM commend";
	private static final String DELETE = "DELETE FROM commend where commendAuto = ?";

	@Override
	public void insert(CommendVO CommendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, CommendVO.getOrdId());
			pstmt.setInt(2, CommendVO.getCommendGrade());
			pstmt.setString(3, CommendVO.getCommendContent());
			pstmt.setDate(4, CommendVO.getCommendDate());

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
	public void update(CommendVO CommendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, CommendVO.getCommendGrade());
			pstmt.setString(2, CommendVO.getCommendContent());
			pstmt.setDate(3, CommendVO.getCommendDate());
			pstmt.setInt(4, CommendVO.getCommendAuto());

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
	public void delete(Integer commendAuto) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, commendAuto);

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
	public CommendVO findByPrimaryKey(Integer commendAuto) {
		
		CommendVO CommendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, commendAuto);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				CommendVO = new CommendVO();
				CommendVO.setCommendAuto(rs.getInt("commendAuto"));
				CommendVO.setOrdId(rs.getInt("ordId"));
				CommendVO.setCommendGrade(rs.getInt("commendGrade"));
				CommendVO.setCommendContent(rs.getString("commendContent"));
				CommendVO.setCommendDate(rs.getDate("commendDate"));
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
		
		return CommendVO;
	}

	@Override
	public List<CommendVO> getAll() {
		
		List<CommendVO> list = new ArrayList<CommendVO>();
		CommendVO CommendVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				CommendVO = new CommendVO();
				CommendVO.setCommendAuto(rs.getInt("commendAuto"));
				CommendVO.setOrdId(rs.getInt("ordId"));
				CommendVO.setCommendGrade(rs.getInt("commendGrade"));
				CommendVO.setCommendContent(rs.getString("commendContent"));
				CommendVO.setCommendDate(rs.getDate("commendDate"));
				list.add(CommendVO); // Store the row in the list
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
