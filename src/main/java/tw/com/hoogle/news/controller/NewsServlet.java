package tw.com.hoogle.news.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.com.hoogle.hotel.model.HotelService;
import tw.com.hoogle.hotel.model.HotelVO;
import tw.com.hoogle.mail.model.MailService;
import tw.com.hoogle.news.model.*;


@WebServlet("/NewsServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
       

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		System.out.println(action);
		
		//==================== 單一查詢 ====================
		if ("getNewsByDate".equals(action)) { // 來自newsList.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("dateFrom");
				String str2 = req.getParameter("dateEnd");
				if ((str1 == null || (str1.trim()).length() == 0) || (str2 == null || (str2.trim()).length() == 0)) {
					errorMsgs.add("請選擇您要搜尋的期間");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/newsList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				Date dateFrom = null;
				Date dateEnd = null;
				try {
					dateFrom = java.sql.Date.valueOf(str1);
					dateEnd = java.sql.Date.valueOf(str2);
				} catch (Exception e) {
					errorMsgs.add("期間格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/newsList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				/***************************2.開始查詢資料*****************************************/
				NewsService newsSvc = new NewsService();
				List<NewsVO> searchNews = newsSvc.findNewsByDate(dateFrom, dateEnd);
				if (searchNews.isEmpty()) {
//					errorMsgs.add("此區間無資料");
					out.println("<meta http-equiv='refresh' content='1;URL=" + req.getContextPath()
					+ "/back_end/news/newsList.jsp'>");
					out.println("<script> alert('此區間無資料！');</script>");
					return;
				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/news/newsList.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
						
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("dateFrom", dateFrom); // 
				req.setAttribute("dateEnd", dateEnd); 
				System.out.println("getNewsByDate轉交");
				String url = "/back_end/news/searchNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdmin.jsp
				successView.forward(req, res);
		}
		
		if ("insert".equals(action)) { // 來自newsList.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {				
				NewsVO newsVO = new NewsVO();
				
				Integer administratorId = null;
				
				try {
					administratorId = Integer.valueOf(req.getParameter("administratorId"));
					
				} catch (NumberFormatException e) {
					System.out.println(administratorId);
					errorMsgs.add("管理者編號必須是數字");
				}				

				String newsDate = req.getParameter("newsDate");
				if (newsDate == null || newsDate.trim().length() == 0) {
					errorMsgs.add("請選擇日期");
				}
				
				String newsSubject = req.getParameter("newsSubject");
				if (newsSubject == null || newsSubject.trim().length() == 0) {
					errorMsgs.add("請輸入最新消息主旨");
				}
				
				String newsContent = req.getParameter("newsContent");
				if (newsContent == null || newsContent.trim().length() == 0) {
					errorMsgs.add("請輸入最新消息內容");
				}

				byte[] newsPic = null;

				Part part = req.getPart("newsPic");
				if (part == null || part.getSize() == 0) {
					errorMsgs.add("請上傳一張圖片");
				}
				
				InputStream is = part.getInputStream();
				newsPic = new byte[is.available()];
				is.read(newsPic);
				is.close();
				System.out.println("照片上傳成功");
				
				newsVO.setAdministratorId(administratorId);
				newsVO.setNewsDate(java.sql.Date.valueOf(newsDate));
				newsVO.setNewsSubject(newsSubject);
				newsVO.setNewsContent(newsContent);
				newsVO.setNewsPic(newsPic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsPic", newsPic);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/addNews.jsp");
					failureView.forward(req, res);
					return;
				}

				// 新增
							
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.addNews(administratorId, newsSubject, newsContent, 
						java.sql.Date.valueOf(newsDate), newsPic, 1);

				// 轉交
				out.println("<meta http-equiv='refresh' content='1;URL=" + req.getContextPath()
				+ "/back_end/news/newsList.jsp'>");
				out.println("<script> alert('新增成功！');</script>");
				
//				String url = "/back_end/news/newsList.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交到旅客總表listAllUser.jsp
//				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/addNews.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		if ("disable".equals(action)) { // 來自listAllHotel.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("111");
			

			/*************************** 1.接收請求參數 ****************************************/
			Integer newsId = Integer.valueOf(req.getParameter("newsId"));
			

			/*************************** 2.開始查詢資料 ****************************************/
			NewsService newsSvc = new NewsService();
			NewsVO newsVO = newsSvc.disableNews(newsId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("hotelVO", hotelVO);
			System.out.println("disable");
			
			out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
			+ "/back_end/news/newsList.jsp'>");
			out.println("<script> alert('已下架成功');</script>");
				
//				String url = "/back_end/hotelAndUser/hotelList.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
			
		}
		
		
	}

}
