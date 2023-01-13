package tw.com.hoogle.adminFilters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
		urlPatterns = {"/back_end/administrator/*", "/back_end/approval/*", "/back_end/finStm/*", "/back_end/hotelAndUser/*", "/back_end/news/*"}
)
public class loginFilter extends HttpFilter implements Filter {
	
	private FilterConfig config;
       

    public void init(FilterConfig fConfig) throws ServletException {
    	this.config = config;
    }
    
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		PrintWriter out = res.getWriter();
//		out.println("<font color=red>!Filter開始!</font><br>");

		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object account = session.getAttribute("account");
		if (account == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back_end/login/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		config = null;
	}

}
