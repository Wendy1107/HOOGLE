package tw.com.hoogle.compare.model;

import java.util.List;

public class CompareService {

	private CompareDAO_interface dao;

	public CompareService() {
		dao = new CompareDAO();
	}

	public List<CompareVO> getOneHotel(String hotelName) {
		return dao.findByHotelName(hotelName);
	}
	
	public List<CompareVO> getAll() {
		return dao.getAll();
	}


	public List<CompareVO> compareSvcList(String hotelName) {
		 return dao.findByHotelService(hotelName);
	}
	
//	public List<CompareVO> getRoomType(String hotelName) {
//		return dao.getRoomType(hotelName);
//	}
}
