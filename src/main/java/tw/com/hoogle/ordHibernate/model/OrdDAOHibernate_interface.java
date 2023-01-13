package tw.com.hoogle.ordHibernate.model;

import java.util.Date;
import java.util.List;

public interface OrdDAOHibernate_interface {
	public OrdBean insert(OrdBean ordBean);
//	public void update(OrdBean ordBean);
	public boolean delete(Integer ordId);
	public OrdBean findByPrimaryKey(Integer ordId);
	public List<OrdBean> getAll();
	public OrdBean update(int ordId, int userId, int hotelId, String userName,  
			String hotelName, Date ordDate, Date ordCheckin, Date ordCheckout, int ordNights, String ordRemark);
}
