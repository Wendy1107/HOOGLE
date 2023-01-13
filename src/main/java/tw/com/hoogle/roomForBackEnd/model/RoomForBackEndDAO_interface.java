package tw.com.hoogle.roomForBackEnd.model;

import java.util.List;

import tw.com.hoogle.roomForBackEnd.*;

public interface RoomForBackEndDAO_interface {
	
	public List<RoomForBackEndVO> findRoomDetail(Integer hotelId);

}
