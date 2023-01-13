package tw.com.hoogle.hotelpic.model;

import java.sql.Connection;
import java.util.List;

import tw.com.hoogle.servicelist.model.ServiceListVO;

public interface HotelpicDAO_interface {
	



	public void insert(HotelpicVO hotelpicVO);
	public void update(HotelpicVO hotelpicVO);
	public void delete(Integer hotelpicId);
	public void delete2(Integer hotelpicId,Connection con);
	public void insert2(HotelpicVO hotelpicVO,Connection con);
	public HotelpicVO findByPrimaryKey(Integer hotelpicId);
	public List<HotelpicVO> getAll();
}
