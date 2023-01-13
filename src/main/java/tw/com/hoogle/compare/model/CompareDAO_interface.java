package tw.com.hoogle.compare.model;

import java.util.List;

public interface CompareDAO_interface {

//	public CompareVO findByHotelName(String hotelName);
	public List<CompareVO> findByHotelName(String hotelName);
	public List<CompareVO> getAll();
	public List<CompareVO> getRoomType(String hotelName);
	public List<CompareVO> findByHotelService(String hotelName);
}
	

