package tw.com.hoogle.searchHotel.model;

import java.util.List;

public interface SearchHotelDAO {
	
	public abstract SearchHotelBean select(String hotelCounty);

	public abstract List <SearchHotelBean> getAll();

	
}
