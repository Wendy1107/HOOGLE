package tw.com.hoogle.ord.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/hotelDetail/hotelDetail.jsp" ,"/commend/addCommend.jsp"})
	public class hotelDetailFilter extends HttpFilter {
	 

	 @Override
	 public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
	   throws IOException, ServletException {

	  // 【取得 session】
	  HttpSession session = req.getSession();
	  // 【從 session 判斷此user是否登入過】
	  Object account = session.getAttribute("userId");
	  if (account == null) {
	   session.setAttribute("location", req.getServletPath());
	   res.sendRedirect(req.getContextPath() + "/user/loginForUser.jsp");
	   return;
	  } else {
	   chain.doFilter(req, res);
	  }

	 }
}
