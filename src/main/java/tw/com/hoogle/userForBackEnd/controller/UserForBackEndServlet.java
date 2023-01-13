package tw.com.hoogle.userForBackEnd.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hoogle.userForBackEnd.model.UserForBackEndService;
import tw.com.hoogle.userForBackEnd.model.UserVO;

//@WebServlet(name = "UserForBackEndServlet", urlPatterns= {"/back_end/hotelAndUser/UserForBackEndServlet"})
@WebServlet("/UserForBackEndServlet")
public class UserForBackEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = null;
		
		if (req.getParameter("action") != null) {
			action = req.getParameter("action");
		}

// ===================================================單一查詢=========================================================//
		if ("getOne_For_User".equals(action)) { // select_page.jsp請求
//			System.out.println("getOne_For_User");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 1.接收請求參數，輸入格式的錯誤處理
			Integer userId = Integer.valueOf(req.getParameter("userId"));
	
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/hotelAndUser/userList.jsp");
				failureView.forward(req, res);
				return;
			}

			// 2.查詢資料

			UserForBackEndService userForBackEndSvc = new UserForBackEndService();
			UserVO userVO = userForBackEndSvc.getOneUser(userId);
			if (userId == null) {
				errorMsgs.add("無此筆資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/hotelAndUser/userList.jsp");
				failureView.forward(req, res);
				return;
			}

			// 3.查詢完成，準備轉交

			req.setAttribute("userVO", userVO); // 資料庫取出userVO物件，存入req
			String url = "/back_end/hotelAndUser/userDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交給listOneUser.jsp
			successView.forward(req, res);
		}

// ===============================================單一旅客詳細資料=====================================================//
		
		if ("getUserDetail".equals(action)) { // select_page.jsp請求
//			System.out.println("getOne_For_User");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 1.接收請求參數，輸入格式的錯誤處理
			Integer userId = Integer.valueOf(req.getParameter("userId"));
	
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/hotelAndUser/userList.jsp");
				failureView.forward(req, res);
				return;
			}

			// 2.查詢資料

			UserForBackEndService userForBackEndSvc = new UserForBackEndService();
			UserVO userVO = userForBackEndSvc.getOneUser(userId);
			if (userId == null) {
				errorMsgs.add("無此筆資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/hotelAndUser/userList.jsp");
				failureView.forward(req, res);
				return;
			}

			// 3.查詢完成，準備轉交

			req.setAttribute("userVO", userVO); // 資料庫取出userVO物件，存入req
			String url = "/back_end/hotelAndUser/userDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交給listOneUser.jsp
			successView.forward(req, res);
		}


	}

}
