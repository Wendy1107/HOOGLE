package tw.com.hoogle.ordHibernate.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OrdDAOHibernate implements OrdDAOHibernate_interface{
	private SessionFactory sessionFactory;
	public OrdDAOHibernate(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public static void main(String[] args) {
//		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		OrdDAOHibernate_interface dao = new OrdDAOHibernate(sessionFactory);
		
		/* 新增一筆 */
//		OrdBean insert = new OrdBean();
//		insert.setUserId(2001);
//		insert.setHotelId(3001);
//		insert.setUserName("user1");
//		insert.setHotelName("hotel1");
//		insert.setOrdDate(java.sql.Date.valueOf("2022-12-08"));
//		insert.setOrdCheckin(java.sql.Date.valueOf("2022-12-08"));
//		insert.setOrdCheckout(java.sql.Date.valueOf("2022-12-09"));
//		insert.setOrdNights(1);
//		insert.setOrdRemark("hibernate測試");
//		dao.insert(insert);
//		System.out.println("-----新增成功-----");
		
		/* 查詢多筆 */
//		List<OrdBean> getAll = dao.getAll();
//		System.out.println("-----查詢成功-----");
//		System.out.println("getAll = "+getAll);

		/* 查詢一筆 */
//		OrdBean findByPrimaryKey = dao.findByPrimaryKey(5001);
//		System.out.println("-----查詢成功-----");
//		System.out.println("查詢5001 = "+findByPrimaryKey);
		
		/* 修改一筆 */
//		OrdBean update = dao.update(5001,2002,3002,"user2","hotel2",java.sql.Date.valueOf("2022-12-08"),java.sql.Date.valueOf("2022-12-08"),java.sql.Date.valueOf("2022-12-09"),1,"測試");
//		System.out.println("-----修改成功-----");
//		System.out.println("update="+update);
		
		/* 刪除一筆 */
//		boolean delete = dao.delete(5013);
//		System.out.println("-----刪除成功-----");
//		System.out.println("delete="+delete);
		
//		transaction.commit();
//		session.close();
//		HibernateUtil.closeSessionFactory();
	}
	
	
	@Override
	public  OrdBean insert(OrdBean ordBean) {
		this.getSession().save(ordBean);
		return ordBean;
	}
	@Override
	public OrdBean update(int ordId, int userId, int hotelId, String userName, String hotelName, Date ordDate,
			Date ordCheckin, Date ordCheckout, int ordNights, String ordRemark) {
		if(Integer.toString(ordId)!=null) {
			OrdBean temp = this.getSession().get(OrdBean.class, ordId);
			if(temp!=null) {
				temp.setUserId(userId);
				temp.setHotelId(hotelId);
				temp.setUserName(userName);
				temp.setHotelName(hotelName);
				temp.setOrdDate(ordDate);
				temp.setOrdCheckin(ordCheckin);
				temp.setOrdCheckout(ordCheckout);
				temp.setOrdNights(ordNights);
				temp.setOrdRemark(ordRemark);
				return temp;
			}
		}return null;
	}
	@Override
	public boolean delete(Integer ordId) {
		if(ordId!=null) {
			OrdBean temp = this.getSession().get(OrdBean.class, ordId);
			if(temp!=null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
	@Override
	public OrdBean findByPrimaryKey(Integer ordId) {
		if(ordId!=null) {
			return this.getSession().get(OrdBean.class, ordId);
		}
		return null;	}
	@Override
	public List<OrdBean> getAll() {
		return this.getSession().createQuery("from OrdBean", OrdBean.class).list();
	}

}
