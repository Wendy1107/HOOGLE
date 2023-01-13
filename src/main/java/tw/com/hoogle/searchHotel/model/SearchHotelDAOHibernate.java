package tw.com.hoogle.searchHotel.model;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
@Repository
public class SearchHotelDAOHibernate implements SearchHotelDAO{
	@PersistenceContext
	private Session session;	
	public Session getSession() {
		return this.session;
	}
//	public SearchHotelDAOHibernate(SessionFactory sessionFactory) {
//		super();
//		this.sessionFactory = sessionFactory;
//	}
//	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//	Session session = sessionFactory.getCurrentSession();
//	Transaction transaction = session.beginTransaction();
//	SearchHotelDAO dao = new SearchHotelDAOHibernate(sessionFactory);

	@Override
	public SearchHotelBean select(String hotelCounty) {
		
		if(hotelCounty!=null) {
			return this.getSession().get(SearchHotelBean.class, hotelCounty);
		}
		return null;
	}
	@Override
	public List <SearchHotelBean> getAll() {
//		return this.getSession().createQuery("from SearchHotelBean", SearchHotelBean.class).list();

		CriteriaBuilder criteriaBuilder =  this.getSession().getCriteriaBuilder();
		CriteriaQuery<SearchHotelBean> crteriaQuery = criteriaBuilder.createQuery(SearchHotelBean.class);
		
		Root<SearchHotelBean> root = crteriaQuery.from(SearchHotelBean.class);		
		
		TypedQuery <SearchHotelBean> typedQuery = this.getSession().createQuery(crteriaQuery);
		List <SearchHotelBean> resultAll = typedQuery.getResultList();
		if(resultAll!=null && !resultAll.isEmpty()) {
			return resultAll;
		} else {
			return null;
		}
	}
//	transaction.commit();
//	session.close();
}
