package tw.com.hoogle.finStm.model;

import java.sql.Date;
import java.util.List;


public class FinStmService {
	
	private FinStmDAO_interface dao;
	
	public FinStmService() {
		dao = new FinStmDAO();
	}
	
	public List<FinStmVO> getAll(){
		return dao.getAll();
	}
	
	public List<FinStmVO> findStmByDate(Date dateFrom, Date dateEnd){
		return dao.findByDate(dateFrom, dateEnd);
	}

}
