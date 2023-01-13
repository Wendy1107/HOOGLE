package tw.com.hoogle.otherhotel.model;

import java.util.ArrayList;
import java.util.List;

import tw.com.hoogle.hotel.model.HotelVO;
import tw.com.hoogle.hotelpic.model.HotelpicVO;
import tw.com.hoogle.servicelist.model.ServiceListVO;

public class OtherHotelService {

	private OtherHotelDAO_interface dao;

	public OtherHotelService() {
		dao = new OtherHotelDAO();
	}

	public HotelVO updateHotel(HotelVO hotelVO,String[] checkbox,List<HotelpicVO> Hotelpiclist) {
		System.out.println("### updateHotel service");
		List<ServiceListVO> servicelist = new ArrayList<ServiceListVO>();
		for(int i=0;i<checkbox.length;i++) {
//			System.out.println("checkbox value="+checkbox[i]);
			ServiceListVO serviceListvo = new ServiceListVO();
			serviceListvo.setHotelId(hotelVO.getHotelId());
			serviceListvo.setServiceId(Integer.valueOf(checkbox[i]));
			servicelist.add(serviceListvo);
		}
		dao.updateHotel(hotelVO,servicelist,Hotelpiclist);
		return hotelVO;
	}

}
