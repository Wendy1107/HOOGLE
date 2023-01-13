package tw.com.hoogle.news.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.com.hoogle.administrator.model.AdministratorVO;

public class NewsDAO implements NewsDAO_interface {
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
		"INSERT INTO news (administratorId, newsSubject, newsContent, newsDate, newsPic, newsState) "
		                            + "VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE = 
			"UPDATE news set administratorId=?, newsSubject=?, newsContent=?, newsDate=?, "
			+ "newsPic=?, newsState=? where newsId = ?";
		
	private static final String GET_ALL_STMT = 
		"SELECT newsId, administratorId, newsSubject, newsContent, newsDate, newsPic, newsState "
		                            + "FROM news order by newsId DESC";
	
	private static final String GET_RANGE_STMT = 
		"SELECT newsId, administratorId, newsSubject, newsContent, newsDate, newsPic, newsState "
				                    + "FROM news where newsDate between ? and ? order by newsId DESC";
	
	private static final String DISABLE_STMT = 
			"UPDATE news set newsState = 0 where newsId = ?";
	
	private static final String SHOW_INDEX_STMT = 
			"SELECT newsId, administratorId, newsSubject, newsContent, newsDate, newsPic, newsState "
			+ "FROM news where newsState = 1 order by newsId DESC limit 0,3 ;";


	@Override
	public void insert(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, newsVO.getAdministratorId());
			pstmt.setString(2, newsVO.getNewsSubject());
			pstmt.setString(3, newsVO.getNewsContent());
			pstmt.setDate(4, newsVO.getNewsDate());
			pstmt.setBytes(5, newsVO.getNewsPic());
			pstmt.setInt(6, newsVO.getNewsState());
			

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
	public void update(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, newsVO.getAdministratorId());
			pstmt.setString(2, newsVO.getNewsSubject());
			pstmt.setString(3, newsVO.getNewsContent());
			pstmt.setDate(4, newsVO.getNewsDate());
			pstmt.setBytes(5, newsVO.getNewsPic());
			pstmt.setInt(6, newsVO.getNewsState());
			pstmt.setInt(7, newsVO.getNewsId());
	
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
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNewsId(rs.getInt("newsId"));
				newsVO.setAdministratorId(rs.getInt("administratorId"));
				newsVO.setNewsSubject(rs.getString("newsSubject"));
				newsVO.setNewsContent(rs.getString("newsContent"));
				newsVO.setNewsDate(rs.getDate("newsDate"));
				newsVO.setNewsPic(rs.getBytes("newsPic"));
				newsVO.setNewsState(rs.getInt("newsState"));

				list.add(newsVO); // Store the row in the list
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
	public List<NewsVO> findByDate(Date dateFrom, Date dateEnd) {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RANGE_STMT);
			
			pstmt.setDate(1, dateFrom);
			pstmt.setDate(2, dateEnd);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNewsId(rs.getInt("newsId"));
				newsVO.setAdministratorId(rs.getInt("administratorId"));
				newsVO.setNewsSubject(rs.getString("newsSubject"));
				newsVO.setNewsContent(rs.getString("newsContent"));
				newsVO.setNewsDate(rs.getDate("newsDate"));
				newsVO.setNewsPic(rs.getBytes("newsPic"));
				newsVO.setNewsState(rs.getInt("newsState"));

				list.add(newsVO); // Store the row in the list
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
	
	
//	@Override
//	public NewsVO findByPrimaryKey(Integer newsId) {
//
//		NewsVO newsVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, newsId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				newsVO = new NewsVO();
//				newsVO.setNewsId(rs.getInt("newsId"));
//				newsVO.setAdministratorId(rs.getInt("administratorId"));
//				newsVO.setNewsSubject(rs.getString("newsSubject"));
//				newsVO.setNewsContent(rs.getString("newsContent"));
//				newsVO.setNewsDate(rs.getDate("newsDate"));
//				newsVO.setNewsPic(rs.getBytes("newsPic"));
//				newsVO.setNewsState(rs.getInt("newsState"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return newsVO;
//	}
	
	
	@Override
	public void disable(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DISABLE_STMT);

			pstmt.setInt(1, newsVO.getNewsId());

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
	public List<NewsVO> showIndex() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SHOW_INDEX_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNewsId(rs.getInt("newsId"));
				newsVO.setAdministratorId(rs.getInt("administratorId"));
				newsVO.setNewsSubject(rs.getString("newsSubject"));
				newsVO.setNewsContent(rs.getString("newsContent"));
				newsVO.setNewsDate(rs.getDate("newsDate"));
				newsVO.setNewsPic(rs.getBytes("newsPic"));
				newsVO.setNewsState(rs.getInt("newsState"));

				list.add(newsVO); // Store the row in the list
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
