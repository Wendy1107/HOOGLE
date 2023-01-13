package tw.com.hoogle.room.model;

import java.util.List;

import tw.com.hoogle.roompic.model.RoompicDAO_interface;
import tw.com.hoogle.roompic.model.RoompicJDBCDAO;
import tw.com.hoogle.roompic.model.RoompicVO;

public class RoomService {

	private RoomDAO_interface dao;
	private RoompicDAO_interface picDao;

	public RoomService() {
		dao = new RoomDAO();
		picDao = new RoompicJDBCDAO();
	}

	public RoomVO addRoom(RoomVO roomVO, RoompicVO roompicVO) {
		Integer roomAuto = dao.insert(roomVO);
		roompicVO.setRoomAuto(roomAuto);
		picDao.insert(roompicVO);
		return roomVO;
	}

	public RoomVO updateRoom(RoomVO roomVO, RoompicVO roompicVO) {
		dao.update(roomVO);
		picDao.updatePicByRoomAuto(roompicVO);
		return roomVO;
	}

	public void deleteRoom(Integer roomAuto) {
		dao.delete(roomAuto);
	}

	public RoomVO getOneRoom(Integer roomAuto) {
		return dao.findByPrimaryKey(roomAuto);
	}

	public List<RoomVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomVO> getByHotelId(Integer hotelId) {
		return dao.getByHotelId(hotelId);
	}

	// 預留給 Struts 2 用的
	public void updateRoom(RoomVO roomVO) {
		dao.update(roomVO);
	}
	
	public List<RoomSummaryVo> findForRoomSummary(
			Integer hotelId,
			String ordDateStart,
			String ordDateEnd,
			String roomName) {
		if (hotelId == null) {
			return null;
		}
		
		if (ordDateStart == null || ordDateStart.isEmpty()) {
			return null;
		}
		
		if (ordDateEnd == null || ordDateEnd.isEmpty()) {
			return null;
		}
		
		if (roomName == null || roomName.isEmpty()) {
			return null;
		}
		
		return dao.selectRoomSummaryByHotelIdAndOrdDateAndRoomName(hotelId, ordDateStart, ordDateEnd, roomName);
	}


}


//		
//		public void deleteRoom(Integer roomAuto) {
//			dao.delete(roomAuto);
//		}
//		
//		public RoomVO getOneRoom(Integer roomAuto) {
//			return dao.findByPrimaryKey(roomAuto);
//		}
//		
//		public List<RoomVO> getAll(){
//			return dao.getAll();
//		}
//
//	}
