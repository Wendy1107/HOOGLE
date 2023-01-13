package tw.com.hoogle.room.model;

import java.util.List;

public interface RoomDAO_interface {

	public Integer insert(RoomVO roomVO);

	public void update(RoomVO roomVO);

	public void delete(Integer roomAuto);

	public RoomVO findByPrimaryKey(Integer roomAuto);

	public List<RoomVO> getAll();
	
	public List<RoomVO> getByHotelId(Integer hotelId);
	
	List<RoomSummaryVo> selectRoomSummaryByHotelIdAndOrdDateAndRoomName(
			Integer hotelId,
			String ordDateStart,
			String ordDateEnd,
			String roomName);

}
