package tw.com.hoogle.finStm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hoogle.finStm.model.FinStmService;
import tw.com.hoogle.finStm.model.FinStmVO;

@WebServlet("/FinStmServlet")
public class FinStmServlet extends HttpServlet {
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
		
		if ("getStmByDate".equals(action)) { // 來自statementList.jsp的請求

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
							.getRequestDispatcher("/back_end/finStm/stmList.jsp");
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
							.getRequestDispatcher("/back_end/finStm/stmList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				/***************************2.開始查詢資料*****************************************/
				FinStmService finStmSvc = new FinStmService();
				List<FinStmVO> finStmtList = finStmSvc.findStmByDate(dateFrom, dateEnd);
				if (finStmtList.isEmpty()) {
//					errorMsgs.add("此區間查無資料");
					out.println("<meta http-equiv='refresh' content='1;URL=" + req.getContextPath()
					+ "/back_end/finStm/stmList.jsp'>");
					out.println("<script> alert('此區間無資料！');</script>");
					return;
				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/finStm/stmList.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
						
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("dateFrom", dateFrom); // 
				req.setAttribute("dateEnd", dateEnd);
				System.out.println("getByHotelId轉交");
				String url = "/back_end/finStm/searchStm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 searchStatement.jsp
				successView.forward(req, res);
		}
		
		
		
		
		
	}

}
