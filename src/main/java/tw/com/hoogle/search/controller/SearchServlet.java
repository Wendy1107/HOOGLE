package tw.com.hoogle.search.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import tw.com.hoogle.ord.model.hibernate.HibernateUtil;
import tw.com.hoogle.search.model.SearchBean;
import tw.com.hoogle.search.model.SearchDAO;
import tw.com.hoogle.search.model.SearchDAOHibernate;
import tw.com.hoogle.search.model.SearchService;

//@WebServlet(
//		urlPatterns = {"/search/search.controller"}		
//)
@Controller
public class SearchServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(
		path ={"/search/search.controller"}		
)
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		SearchDAO dao = new SearchDAOHibernate(sessionFactory);
		
//接收資料
		String userId = request.getParameter("userId");
//		String userEmail = request.getParameter("userEmail");
//		String userPassword = request.getParameter("userPassword");
//		String userName = request.getParameter("userName");
//		String userPhone = request.getParameter("userPhone");
//		String userIdentity = request.getParameter("userIdentity");
//		String userBirthday = request.getParameter("userBirthday");
//		String userRegistration = request.getParameter("userRegistration");
		String search = request.getParameter("search");
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
//轉換資料
		int Id = 0;
		if(userId!=null && userId.length()!=0) {
			try {
				Id = Integer.parseInt(userId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("Id", "Id must be an integer");
			}
		}
//		String Phone = "";
//		if(userPhone!=null && userPhone.length()!=0) {
//			try {
//				Phone = userPhone;
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//				errors.put("Phone", "Phone must be an integer");
//			}
//		}
//		java.util.Date Birthday = null;
//		if(userBirthday!=null && userBirthday.length()!=0) {
//			try {
//				Birthday = sFormat.parse(userBirthday);
//			} catch (ParseException e) {
//				e.printStackTrace();
//				errors.put("Birthday", "Birthday must be a date of YYYY-MM-DD");
//			}
//		}
//		java.util.Date Registration = null;
//		if(userRegistration!=null && userRegistration.length()!=0) {
//			try {
//				Registration = sFormat.parse(userRegistration);
//			} catch (ParseException e) {
//				e.printStackTrace();
//				errors.put("Registration", "Registration must be a date of YYYY-MM-DD");
//			}
//		}
		if(errors!=null && !errors.isEmpty()) {
			System.out.println("error");
			request.getRequestDispatcher(
					"/search/search.jsp").forward(request, response);
			return;
		}
		
//呼叫Model
		SearchBean bean = new SearchBean();
		bean.setUserId(Id);
//		bean.setUserEmail(userEmail);
//		bean.setUserPassword(userPassword);
//		bean.setUserName(userName);
//		bean.setUserPhone(Phone);
//		bean.setUserIdentity(userIdentity);
//		bean.setUserBirthday(Birthday);
//		bean.setUserRegistration(Registration);
		
//根據Model執行結果導向View
//		if(search!=null && search.equals("Select")) {
//			SearchBean result = searchService.select(bean);
//			request.setAttribute("select", result);
//			request.getRequestDispatcher(
//					"/search/display.jsp").forward(request, response);
//		} 
//		else  {
//			errors.put("action", "Unknown Action:"+search);
//			request.getRequestDispatcher(
//					"/search/search.jsp").forward(request, response);
//		}
		if(search!=null && search.equals("Select")) {
			SearchBean result = searchService.select(bean.getUserId());
			request.setAttribute("select", result);
			request.getRequestDispatcher(
					"/search/display.jsp").forward(request, response);
		} 
		else  {
			errors.put("action", "Unknown Action:"+search);
			request.getRequestDispatcher(
					"/search/search.jsp").forward(request, response);
		}
//		transaction.commit();
//		session.close();
//		HibernateUtil.closeSessionFactory();
	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		this.doGet(req, resp);
//	}
}
