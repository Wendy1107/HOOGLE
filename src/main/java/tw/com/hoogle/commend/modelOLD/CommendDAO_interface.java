package tw.com.hoogle.commend.modelOLD;

import java.util.*;

public interface CommendDAO_interface {

	public void insert(CommendVO commendVO);
	public void update(CommendVO commendVO);
	public void delete(Integer commendAuto);
	public CommendVO findByPrimaryKey(Integer commendAuto);
	public List<CommendVO> getAll();

}
	

