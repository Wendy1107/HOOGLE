package tw.com.hoogle.searchHotel.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.hoogle.ord.model.OrdDAO;
import tw.com.hoogle.ord.model.OrdVO;
@Service
@Transactional
public class SearchHotelService {
	@Autowired
	private SearchHotelDAO searchHotelDao;
	
	public SearchHotelService() {
		searchHotelDao = new SearchHotelDAOHibernate();
	}
//	public SearchHotelService(SearchHotelDAO searchHotelDao) {
//		super();
//		this.searchHotelDao = searchHotelDao;
//	}
	
//	//...............
//	public SearchHotelService() {
//		searchHotelDao = new SearchHotelDAOHibernate();
//	}
//	//...............
	
	
//	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//
//		SearchService searchService = new SearchService(new SearchDAOHibernate(sessionFactory));
////		List<SearchBean> selects = productService.select(null);
////		System.out.println("selects="+selects);
////		
//		transaction.commit();
//		session.close();
//		HibernateUtil.closeSessionFactory();
//	}
	
	public SearchHotelBean select(SearchHotelBean bean) {
		SearchHotelBean result = null;
		if(bean!=null && bean.getHotelCounty()!=null && !bean.getHotelCounty().equals(0)) {
			SearchHotelBean temp = searchHotelDao.select(bean.getHotelCounty());
			if(temp!=null) {
				result = new SearchHotelBean();	
				result=bean;
				System.out.println(result);
			}
		} 
		
		return result;
	}
	public SearchHotelBean select(String hotelCounty) {
		SearchHotelBean result = null;
		if(hotelCounty!=null && hotelCounty!=null && !hotelCounty.equals(0)) {
			SearchHotelBean temp = searchHotelDao.select(hotelCounty);
			System.out.println("temp="+temp);
			if(temp!=null) {
				result = new SearchHotelBean();	
				result=temp;
				System.out.println(result);
			}
		} 
		
		return result;
	}
	
	//............... -->
	public List <SearchHotelBean> getAll() {
		return searchHotelDao.getAll();
	//............... -->
		
	}
//	public List<SearchHotelBean> getAll() {
//		List<SearchHotelBean> result = null;
//		if(bean!=null && bean.getUserId()!=null && !bean.getUserId().equals(0)) {
//			SearchBean temp = searchDao.select(bean.getUserId());
//			if(temp!=null) {
//				result = new ArrayList<SearchBean>();
//				result.add(temp);
//			}
//		} else {
//			result = searchDao.select(); 
//		}
//		return result;
//	}
}
