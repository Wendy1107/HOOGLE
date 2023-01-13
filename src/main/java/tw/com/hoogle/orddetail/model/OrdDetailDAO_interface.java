package tw.com.hoogle.orddetail.model;

import java.util.List;

public interface OrdDetailDAO_interface {

	public void insert(OrdDetailVO orddetailVO);
	public void update(OrdDetailVO orddetailVO);
	public void delete(Integer orddetailId);
	public List<OrdDetailVO> findByOrdId(Integer ordId);
	public OrdDetailVO findByPrimaryKey(Integer orddetailId);
	public OrdDetailVO findByRoomAuto(Integer roomAuto);
	public List<OrdDetailVO> getAll();
	public void updateNonreserved(OrdDetailVO orddetailVO);

}