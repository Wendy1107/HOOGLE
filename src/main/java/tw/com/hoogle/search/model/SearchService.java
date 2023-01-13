package tw.com.hoogle.search.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class SearchService {
	@Autowired
	private SearchDAO searchDao;
//	public SearchService(SearchDAO searchDao) {
//		super();
//		this.searchDao = searchDao;
//	}

//	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//
//		SearchService searchService = new SearchService(new SearchDAOHibernate(sessionFactory));
//		List<SearchBean> selects = productService.select(null);
//		System.out.println("selects="+selects);
//		
//		transaction.commit();
//		session.close();
//		HibernateUtil.closeSessionFactory();
//	}
	
	public SearchBean select(SearchBean bean) {
		SearchBean result = null;
		if(bean!=null && bean.getUserId()!=null && !bean.getUserId().equals(0)) {
			SearchBean temp = searchDao.select(bean.getUserId());
			if(temp!=null) {
				result = new SearchBean();	
				result=bean;
				System.out.println(result);
			}
		} 
		
		return result;
	}
	public SearchBean select(Integer userId) {
		SearchBean result = null;
		if(userId!=null && userId!=null && !userId.equals(0)) {
			SearchBean temp = searchDao.select(userId);
			System.out.println("temp="+temp);
			if(temp!=null) {
				result = new SearchBean();	
				result=temp;
				System.out.println(result);
			}
		} 
		
		return result;
	}
//	public List<SearchBean> select(SearchBean bean) {
//		List<SearchBean> result = null;
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
