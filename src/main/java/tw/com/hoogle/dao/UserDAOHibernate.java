package tw.com.hoogle.dao;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tw.com.hoogle.domain.UserBean;

@Repository
public class UserDAOHibernate implements UserDAO {
	
	@PersistenceContext
	private Session session;
	public Session getSession() {
		return this.session;
	}

	@Override
	public UserBean findByUserEmail(String userEmail) {
		if(userEmail != null) {
			return this.getSession().get(UserBean.class, userEmail);
		}
		return null;
	}

	@Override
	public List<UserBean> getAll() {
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<UserBean> criteriaQuery = criteriaBuilder.createQuery(UserBean.class);
		
		Root<UserBean> root = criteriaQuery.from(UserBean.class);
		
		TypedQuery<UserBean> typedQuery = this.getSession().createQuery(criteriaQuery);
		List<UserBean> result = typedQuery.getResultList();
		if(result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
		
	}

	@Override
	public UserBean insert(UserBean bean) {
		if(bean != null && bean.getUserId() != null) {
			UserBean temp = this.getSession().get(UserBean.class, bean.getUserId());
			if(temp == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public UserBean update(UserBean bean) {
		if(bean != null && bean.getUserId() != null) {
			UserBean temp = this.getSession().get(UserBean.class, bean.getUserId());
			if(temp != null) {
				return (UserBean) this.getSession().merge(bean);
				
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer userId) {
		if(userId != null) {
			UserBean temp = this.getSession().get(UserBean.class, userId);
			if(temp != null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}


}
