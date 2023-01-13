package tw.com.hoogle.administrator.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministratorService {

	private AdministratorDAO_interface dao;

	public AdministratorService() {
		dao = new AdministratorDAO();
	}

	public AdministratorVO addAdministrator(String administratorName, String administratorAccount, String administratorPassword,
			Boolean administratorDominate, Boolean newsDominate, Boolean hotelDominate, Boolean userDominate,
			java.sql.Date administratorHiredate) {

		AdministratorVO administratorVO = new AdministratorVO();

		administratorVO.setAdministratorName(administratorName);
		administratorVO.setAdministratorAccount(administratorAccount);
		administratorVO.setAdministratorPassword(administratorPassword);
		administratorVO.setAdministratorDominate(administratorDominate);
		administratorVO.setNewsDominate(newsDominate);
		administratorVO.setHotelDominate(hotelDominate);
		administratorVO.setUserDominate(userDominate);
		administratorVO.setAdministratorHiredate(administratorHiredate);
		dao.insert(administratorVO);

		return administratorVO;
	}

	public AdministratorVO updateAdministrator(Integer administratorId, String administratorName, String administratorAccount,
			String administratorPassword, Boolean administratorDominate, Boolean newsDominate, Boolean hotelDominate,
			Boolean userDominate, java.sql.Date administratorHiredate) {

		AdministratorVO administratorVO = new AdministratorVO();

		administratorVO.setAdministratorId(administratorId);
		administratorVO.setAdministratorName(administratorName);
		administratorVO.setAdministratorAccount(administratorAccount);
		administratorVO.setAdministratorPassword(administratorPassword);
		administratorVO.setAdministratorDominate(administratorDominate);
		administratorVO.setNewsDominate(newsDominate);
		administratorVO.setHotelDominate(hotelDominate);
		administratorVO.setUserDominate(userDominate);
		administratorVO.setAdministratorHiredate(administratorHiredate);
		dao.update(administratorVO);

		return administratorVO;
	}

//	public void deleteAdministrator(Integer administratorId) {
//		dao.delete(administratorId);
//	}
	public AdministratorVO disableAdministrator(Boolean administratorDominate, Boolean newsDominate, Boolean hotelDominate,
			Boolean userDominate, Integer administratorId) {
		AdministratorVO administratorVO = new AdministratorVO();
		
		administratorVO.setAdministratorDominate(administratorDominate);
		administratorVO.setNewsDominate(newsDominate);
		administratorVO.setHotelDominate(hotelDominate);
		administratorVO.setUserDominate(userDominate);
		administratorVO.setAdministratorId(administratorId);
		dao.disable(administratorVO);

		return administratorVO;
	}

	public AdministratorVO getOneAdministrator(Integer administratorId) {
		return dao.findByPrimaryKey(administratorId);
	}

	public List<AdministratorVO> getAll() {
		return dao.getAll();
	}
	
	public String loginAccount(String administratorAccount) {
		return dao.findAccount(administratorAccount);
	}
	
	public String match(String administratorAccount) {
		return dao.matchAccountPassword(administratorAccount);
	}
	
	public AdministratorVO getPermissionsByAccount(String administratorAccount) {
		return dao.searchPermissionsByAccount(administratorAccount);
	}
}
