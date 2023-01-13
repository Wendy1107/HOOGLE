package tw.com.hoogle.finStm.model;

import java.sql.Date;
import java.util.List;

public interface FinStmDAO_interface {
	
	public List<FinStmVO> getAll();
	public List<FinStmVO> findByDate(Date dateFrom, Date dateEnd);

}
