package tw.com.hoogle.servicelist.model;

import java.sql.Connection;
import java.util.List;

public interface ServiceListDAO_interface {
	
	public void insert(ServiceListVO serviceListVO);
	public void insert2(ServiceListVO serviceListVO,Connection con);
	public void update(ServiceListVO serviceListVO);
	public void delete(Integer serviceListId);
	public ServiceListVO findByPrimaryKey(Integer serviceListId);
	public List<ServiceListVO> getAll();

}
