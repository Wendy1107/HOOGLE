package tw.com.hoogle.commend.model;

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

public class CommendJDBCDAO  {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

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
	private static final String GET_ALL_STMT = "SELECT commendAuto,ordId,commendGrade,commendContent,commendDate FROM commend order by commendAuto";
	private static final String GET_ONE_STMT = "SELECT commendAuto,ordId,commendGrade,commendContent,commendDate FROM commend where commendAuto = ?";
	private static final String DELETE = "DELETE FROM commend where commendAuto = ?";
	private static final String UPDATE = "UPDATE commend set ordId=?, commendGrade=?, commendContent=?, commendDate=? where commendAuto=?";

	public void insert(CommendVO commendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//					con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, commendVO.getOrdId());
			pstmt.setInt(2, commendVO.getCommendGrade());
			pstmt.setString(3, commendVO.getCommendContent());
			pstmt.setDate(4, commendVO.getCommendDate());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void update(CommendVO commendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, commendVO.getOrdId());
			pstmt.setInt(2, commendVO.getCommendGrade());
			pstmt.setString(3, commendVO.getCommendContent());
			pstmt.setDate(4, commendVO.getCommendDate());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void delete(Integer commendAuto) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//					con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, commendAuto);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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


	public CommendVO findByPrimaryKey(Integer commendAuto) {
		CommendVO commendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//					con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, commendAuto);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				commendVO = new CommendVO();
				commendVO.setCommendAuto(rs.getInt("commendAuto"));
				commendVO.setOrdId(rs.getInt("ordId"));
				commendVO.setCommendGrade(rs.getInt("commendGrade"));
				commendVO.setCommendContent(rs.getString("commendContent"));
				commendVO.setCommendDate(rs.getDate("commendDate"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return commendVO;
	}

	public List<CommendVO> getAll() {
		List<CommendVO> list = new ArrayList<CommendVO>();
		CommendVO commendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//					con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				commendVO = new CommendVO();
				commendVO.setCommendAuto(rs.getInt("commendAuto"));
				commendVO.setOrdId(rs.getInt("ordId"));
				commendVO.setCommendGrade(rs.getInt("commendGrade"));
				commendVO.setCommendContent(rs.getString("commendContent"));
				commendVO.setCommendDate(rs.getDate("commendDate"));
				list.add(commendVO);

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		
				CommendDAO dao = new CommendDAO();
		
				// 新增
				CommendVO commendVO = new CommendVO();
				commendVO.setOrdId(5007);
				commendVO.setCommendGrade(1);
				commendVO.setCommendContent("xxx");
				commendVO.setCommendDate(java.sql.Date.valueOf("2022-11-15"));
				dao.insert(commendVO);
				System.out.println("新增成功");
				System.out.println("---------------------");
				
				//查詢單筆
				CommendVO commendVO2 = dao.findByPrimaryKey(7001);
				System.out.print(commendVO2.getCommendAuto() + ",");
				System.out.println(commendVO2.getOrdId()+",");
				System.out.print(commendVO2.getCommendGrade() + ",");
				System.out.print(commendVO2.getCommendContent() + ",");
				System.out.print(commendVO2.getCommendDate());
				System.out.println("查詢成功");
				System.out.println("---------------------");
				
				//查詢全部
				List<CommendVO> list = dao.getAll();
				for (CommendVO aCommend : list) {
				System.out.print(aCommend.getCommendAuto() + ",");
				System.out.print(aCommend.getOrdId() + ",");
				System.out.print(aCommend.getCommendGrade() + ",");
				System.out.print(aCommend.getCommendContent() + ",");
				System.out.print(aCommend.getCommendDate()+ ",\t");
				System.out.println("查詢成功");
				System.out.println("---------------------");
				}
				
				//刪除
				dao.delete(7005);
				System.out.println("刪除成功");
				System.out.println("---------------------");
				
				//修改
				CommendVO commendVO3 = new CommendVO();
				commendVO3.setOrdId(5006);
				commendVO3.setCommendGrade(5);
				commendVO3.setCommendContent("best");
				commendVO3.setCommendDate(java.sql.Date.valueOf("2022-12-12"));
				dao.update(commendVO3);
				System.out.println("修改成功");
				System.out.println("---------------------");
				
				
	}
}
