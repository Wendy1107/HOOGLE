package tw.com.hoogle.searchTests;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.hoogle.search.model.SearchBean;
import tw.com.hoogle.search.model.SearchDAOHibernate;

@SpringBootTest
public class HoogleApplicationTests {
	@Autowired
//	private SessionFactory sessionFactory;
	private SearchDAOHibernate searchDAOHibernate ;
	
	@Test
	public void testSessionFactory() {
		SearchBean bean = searchDAOHibernate.select(2002);
		System.out.println("bean="+bean);
//		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		List result = session.createNativeQuery("select * from ord").list();
//		if(result!=null && !result.isEmpty()) {
//			for(Object obj : result) {
//				Object[] array = (Object[]) obj;
//				System.out.println(array[0]+":"+array[1]);
//			}
//		}
//		transaction.commit();
//		session.close();
	}
}
