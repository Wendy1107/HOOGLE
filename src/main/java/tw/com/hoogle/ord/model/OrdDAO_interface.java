package tw.com.hoogle.ord.model;

import java.util.*;

public interface OrdDAO_interface {

	public void insert(OrdVO ordVO);
	public void update(OrdVO ordVO);
	public void delete(Integer ordId);
	public OrdVO findByPrimaryKey(Integer ordId);
	public  List<OrdVO> findByUserId(Integer userId);
//	public List<OrdVO> findOrddetail(Integer ordId);
	public List<OrdVO> getAll();
	public OrdVO findNewordId(Integer userId);

}
	

