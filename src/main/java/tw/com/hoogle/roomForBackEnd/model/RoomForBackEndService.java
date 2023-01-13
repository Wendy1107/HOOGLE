package tw.com.hoogle.roomForBackEnd.model;

import java.util.List;


public class RoomForBackEndService {
	
	private RoomForBackEndDAO_interface dao;
	
	public RoomForBackEndService() {
		dao = new RoomForBackEndDAO();
	}
	
	public List<RoomForBackEndVO> findRoomByHotelId(Integer hotelId) {
		return dao.findRoomDetail(hotelId);
	}

}
