package tw.com.hoogle.hotel.backEndModel;

import java.util.List;

import tw.com.hoogle.hotel.model.HotelVO;

public class HotelForBackEndService {
	
	private HotelForBackEndDAO_interface dao;

	public HotelForBackEndService() {
		dao = new HotelForBackEndDAO();
	}
		
	public List<HotelVO> findRegisterHotel() {
		return dao.findRegisterHotel();
	}

	
}
