package tw.com.hoogle.roompic.model;

import java.util.List;

public interface RoompicDAO_interface {

	public void insert(RoompicVO roompicVO);

	public void update(RoompicVO roompicVO);

	public void delete(Integer roompicId);

	public RoompicVO findByPrimaryKey(Integer roompicId);

	public List<RoompicVO> getAll();
	
	int updatePicByRoomAuto(RoompicVO vo);
}
