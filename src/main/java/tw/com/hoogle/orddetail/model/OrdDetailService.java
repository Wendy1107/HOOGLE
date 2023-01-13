package tw.com.hoogle.orddetail.model;

import java.sql.Date;
import java.util.List;

public class OrdDetailService {

	private OrdDetailDAO_interface dao;

	public OrdDetailService() {
		dao = new OrdDetailDAO();
	}

	public OrdDetailVO addOrddetail(Integer ordId, Integer roomAuto, Integer roomNumber) {

		OrdDetailVO orddetailVO = new OrdDetailVO();

		orddetailVO.setOrdId(ordId);
		orddetailVO.setRoomAuto(roomAuto);
		orddetailVO.setRoomNumber(roomNumber);
		dao.insert(orddetailVO);

		return orddetailVO;
	}

	public void addOrddetail(OrdDetailVO orddetailVO) {
		dao.insert(orddetailVO);
	}
	
	public OrdDetailVO updateOrddetail(Integer orddetailId, Integer ordId, Integer roomAuto, Integer roomNumber) {

		OrdDetailVO orddetailVO = new OrdDetailVO();

		orddetailVO.setOrddetailId(orddetailId);
		orddetailVO.setOrdId(ordId);
		orddetailVO.setRoomAuto(roomAuto);
		orddetailVO.setRoomNumber(roomNumber);
		dao.update(orddetailVO);

		return dao.findByPrimaryKey(orddetailId);
	}
	
	public OrdDetailVO updateNonreserved(Integer nonreserved, Integer roomAuto) {

		OrdDetailVO orddetailVO = new OrdDetailVO();

		orddetailVO.setNonreserved(nonreserved);
		orddetailVO.setRoomAuto(roomAuto);
		dao.updateNonreserved(orddetailVO);

		return dao.findByRoomAuto(roomAuto);
	}
	
	public void updateOrddetail(OrdDetailVO orddetailVO) {
		dao.update(orddetailVO);
	}
	
	public void deleteOrddetail(Integer orddetailId) {
		dao.delete(orddetailId);
	}

	public OrdDetailVO getOneOrddetail(Integer orddetailId) {
		return dao.findByPrimaryKey(orddetailId);
	}
	
	public OrdDetailVO getNonreserved(Integer roomAuto) {
		return dao.findByRoomAuto(roomAuto);
	}

	public List<OrdDetailVO> getOneOrd(Integer ordId) {
		return dao.findByOrdId(ordId);
	}

	public List<OrdDetailVO> getAll() {
		return dao.getAll();
	}
}
