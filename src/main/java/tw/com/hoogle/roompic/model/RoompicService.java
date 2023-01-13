package tw.com.hoogle.roompic.model;

import java.util.List;


public class RoompicService {
	
	private RoompicDAO_interface dao;
	
	public RoompicService() {
		dao = new RoompicDAO();
	}
	
	
	
	public RoompicVO addRoompic( Integer roomAuto, String roomType, byte[]roompicPic) {

		RoompicVO roompicVO = new RoompicVO();

		roompicVO.setRoomAuto(roomAuto);
		roompicVO.setRoomType(roomType);
		roompicVO.setRoompicPic(roompicPic);		
			
		dao.insert(roompicVO);

		return roompicVO;
	}

	

	public RoompicVO updateRoompic(Integer roompicId, Integer roomAuto, String roomType, byte[]roompicPic) {

		RoompicVO roompicVO = new RoompicVO();
		
		roompicVO.setRoompicId(roompicId);
		roompicVO.setRoomAuto(roomAuto);
		roompicVO.setRoomType(roomType);
		roompicVO.setRoompicPic(roompicPic);

		dao.update(roompicVO);
		return roompicVO;
	}

	
	
	public void deleteRoompic(Integer roompicId) {
		dao.delete(roompicId);
	}
	
	
		
		public RoompicVO getOneRoompic(Integer roompicId) {
			return dao.findByPrimaryKey(roompicId);
		}
	

	public List<RoompicVO> getAll() {
		return dao.getAll();
	}



	public RoompicVO updateRoompic(Object object) {
		// TODO Auto-generated method stub
		return null;
	}



	public RoompicVO updateRoompic(byte[] roompicPic) {
		// TODO Auto-generated method stub
		return null;
	}

		
	}



	