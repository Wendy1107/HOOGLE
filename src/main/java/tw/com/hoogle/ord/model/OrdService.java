package tw.com.hoogle.ord.model;

import java.sql.Date;
import java.util.List;

public class OrdService {

	private OrdDAO_interface dao;

	public OrdService() {
		dao = new OrdDAO();
	}

	public OrdVO addOrd(Integer userId, Integer hotelId, String userName, String hotelName, Date ordDate
			, Date ordCheckin,Date ordCheckout, Integer ordNights,String ordRemark) {

		OrdVO ordVO = new OrdVO();

		ordVO.setUserId(userId);
		ordVO.setHotelId(hotelId);
		ordVO.setUserName(userName);
		ordVO.setHotelName(hotelName);
		ordVO.setOrdDate(ordDate);
		ordVO.setOrdCheckin(ordCheckin);
		ordVO.setOrdCheckout(ordCheckout);
		ordVO.setOrdNights(ordNights);
		ordVO.setOrdRemark(ordRemark);
		dao.insert(ordVO);

		return ordVO;
	}
	
	public OrdVO getNewOrdId(Integer userId) {
		return dao.findNewordId(userId);
	}

	public void addOrd(OrdVO ordVO) {
		dao.insert(ordVO);
	}
	
	public OrdVO updateOrd(Integer ordId, Integer userId, Integer hotelId, String userName, String hotelName, Date ordDate
			, Date ordCheckin,Date ordCheckout, Integer ordNights,String ordRemark) {

		OrdVO ordVO = new OrdVO();

		ordVO.setOrdId(ordId);
		ordVO.setUserId(userId);
		ordVO.setHotelId(hotelId);
		ordVO.setUserName(userName);
		ordVO.setHotelName(hotelName);
		ordVO.setOrdDate(ordDate);
		ordVO.setOrdCheckin(ordCheckin);
		ordVO.setOrdCheckout(ordCheckout);
		ordVO.setOrdNights(ordNights);
		ordVO.setOrdRemark(ordRemark);
		dao.update(ordVO);

		return dao.findByPrimaryKey(ordId);
	}
	
	public void updateOrd(OrdVO ordVO) {
		dao.update(ordVO);
	}

	public void deleteOrd(Integer ordId) {
		dao.delete(ordId);
	}

	public OrdVO getOneOrd(Integer ordId) {
		return dao.findByPrimaryKey(ordId);
	}
	
//		public List<OrdVO> getOneOrdDetail(Integer ordId) {
//		return dao.findOrddetail(ordId);
//	}
		
	public List<OrdVO> getOneUser(Integer userId) {
		return dao.findByUserId(userId);
	}

	public List<OrdVO> getAll() {
		return dao.getAll();
	}
}
