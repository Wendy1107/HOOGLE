package tw.com.hoogle.room.model;

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

public class RoomDAO implements RoomDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO room (hotelId,roomTotal,roomName,roomStatus,roomPrice) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT roomAuto,hotelId,roomTotal,roomName,roomStatus,roomPrice FROM room order by roomAuto";
	private static final String GET_ONE_STMT = "SELECT roomAuto,hotelId,roomTotal,roomName,roomStatus,roomPrice FROM room where roomAuto = ?";
	private static final String GET_getByHotelId_STMT = "SELECT roomAuto,hotelId,roomTotal,roomName,roomStatus,roomPrice FROM room where hotelId = ?";
	private static final String DELETE = "DELETE FROM room where roomAuto = ?";
	private static final String UPDATE = "UPDATE room set hotelId=?, roomTotal=?, roomName=?, roomStatus=?, roomPrice=? where roomAuto=?";

	@Override
	public Integer insert(RoomVO roomVO) {
		try (
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS)
		) {
			pstmt.setInt(1, roomVO.getHotelId());
			pstmt.setInt(2, roomVO.getRoomTotal());
			pstmt.setString(3, roomVO.getRoomName());
			pstmt.setBoolean(4, roomVO.getRoomStatus());
			pstmt.setInt(5, roomVO.getRoomPrice());
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roomVO.getHotelId());
			pstmt.setInt(2, roomVO.getRoomTotal());
//			pstmt.setInt(3, roomVO.getNonreserved());
			pstmt.setString(3, roomVO.getRoomName());
			pstmt.setBoolean(4, roomVO.getRoomStatus());
			pstmt.setInt(5, roomVO.getRoomPrice());
			pstmt.setInt(6, roomVO.getRoomAuto());

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
	public void delete(Integer roomAuto) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomAuto);

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
	public RoomVO findByPrimaryKey(Integer roomAuto) {
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomAuto);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setHotelId(rs.getInt("hotelId"));
				roomVO.setRoomTotal(rs.getInt("roomTotal"));
//				roomVO.setNonreserved(rs.getInt("nonreserved"));
				roomVO.setRoomName(rs.getString("roomName"));
				roomVO.setRoomStatus(rs.getBoolean("roomStatus"));
				roomVO.setRoomPrice(rs.getInt("roomPrice"));
				roomVO.setRoomAuto(rs.getInt("roomAuto"));

			}

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

		return roomVO;
	}

	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomAuto(rs.getInt("roomAuto"));
				roomVO.setHotelId(rs.getInt("hotelId"));
				roomVO.setRoomTotal(rs.getInt("roomTotal"));
//				roomVO.setNonreserved(rs.getInt("nonreserved"));
				roomVO.setRoomName(rs.getString("roomName"));
				roomVO.setRoomStatus(rs.getBoolean("roomStatus"));
				roomVO.setRoomPrice(rs.getInt("roomPrice"));
				list.add(roomVO);

			}

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

	@Override
	public List<RoomSummaryVo> selectRoomSummaryByHotelIdAndOrdDateAndRoomName(Integer hotelId, String ordDateStart,
			String ordDateEnd, String roomName) {
		final String sql = "select r.roomName, r.roomPrice, count(*) as roomCount " + "from ord o "
				+ "    join orddetail od " + "		on o.ordId = od.ordId " + "	join room r "
				+ "		on od.roomAuto = r.roomAuto " + "where o.hotelId = ? "
				+ "        and o.ordDate between ? and ? " + "        and r.roomName = ? " + "group by r.roomAuto";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, hotelId);
			pstmt.setString(2, ordDateStart);
			pstmt.setString(3, ordDateEnd);
			pstmt.setString(4, roomName);
			rs = pstmt.executeQuery();
			List<RoomSummaryVo> list = new ArrayList<>();
			while (rs.next()) {
				RoomSummaryVo vo = new RoomSummaryVo();
				vo.setRoomName(rs.getString(1));
				vo.setRoomPrice(rs.getInt(2));
				vo.setRoomCount(rs.getInt(3));
				list.add(vo);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RoomVO> getByHotelId(Integer hotelId) {
		
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_getByHotelId_STMT);
			
			pstmt.setInt(1, hotelId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomAuto(rs.getInt("roomAuto"));
				roomVO.setHotelId(rs.getInt("hotelId"));
				roomVO.setRoomTotal(rs.getInt("roomTotal"));
//				roomVO.setNonreserved(rs.getInt("nonreserved"));
				roomVO.setRoomName(rs.getString("roomName"));
				roomVO.setRoomStatus(rs.getBoolean("roomStatus"));
				roomVO.setRoomPrice(rs.getInt("roomPrice"));
				list.add(roomVO);

			}

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

}
