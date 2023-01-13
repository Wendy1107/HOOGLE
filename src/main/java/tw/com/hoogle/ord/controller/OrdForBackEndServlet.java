package tw.com.hoogle.ord.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hoogle.ord.model.OrdService;
import tw.com.hoogle.ord.model.OrdVO;

@WebServlet("/OrdForBackEndServlet")
public class OrdForBackEndServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//==================== 單一查詢 ====================
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("====getOne_For_Display====");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("userId");
				System.out.println("旅客編號"+str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入旅客編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/hotelAndUser/userOrdList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer userId = null;
				try {
					userId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("旅客編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/hotelAndUser/userOrdList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				System.out.println("旅客編號"+userId);
				OrdService ordSvc = new OrdService();
				List<OrdVO> ordList = ordSvc.getOneUser(userId);
				if (ordList == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/hotelAndUser/userOrdList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("userIdForOrd", userId); // 資料庫取出的administratorVO物件,存入req
				String url = "/back_end/hotelAndUser/userOrdList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdmin.jsp
				successView.forward(req, res);
		}
	}

}
