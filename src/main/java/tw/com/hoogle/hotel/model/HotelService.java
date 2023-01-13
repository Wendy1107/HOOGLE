package tw.com.hoogle.hotel.model;

import java.util.List;

import tw.com.hoogle.user.model.UserVO;

public class HotelService {
	
	private HotelDAO_interface dao;

	public HotelService() {
		dao = new HotelDAO();
	}
	
	public List<HotelVO> getAll(){
		return dao.getAll();
	}
	
	public HotelVO addHotel(HotelVO hotelVO) {
		System.out.println("### addHotel service");
		hotelVO.setHotelState(2);
		dao.insert(hotelVO);
		
		return hotelVO;
	}
	
	public HotelVO getOneHotel(Integer hotelId) {
		return dao.findByPrimaryKey(hotelId);	
	}
	
	public HotelVO getOneHotel(String hotelEmail, String hotelTaxid) {
		return dao.findByHotelEmailandTaxid(hotelEmail, hotelTaxid);
	}
	
	public HotelVO findByHotelEmail(String hotelEmail) {
		return dao.findByHotelEmail(hotelEmail);
	}
	
	public HotelVO updateHotel(HotelVO hotelVO) {
		System.out.println("### updateHotel service");
		dao.update(hotelVO);
		return hotelVO;
	}

	public String pwdhash(String password) {
		return dao.pwdhash(password);	 
	}
	
	
}
