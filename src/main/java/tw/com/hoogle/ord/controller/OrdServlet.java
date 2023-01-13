package tw.com.hoogle.ord.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.hoogle.ord.model.OrdService;
import tw.com.hoogle.ord.model.OrdVO;

@WebServlet("/OrdServlet")
//@WebServlet(name="OrdServlet",urlPatterns="/ord/ord.do")
public class OrdServlet extends HttpServlet {

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		if ("getOrd_For_Display".equals(action)) { // 來自select_page.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("userId");
			System.out.println("userId="+str);
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("userId", "請輸入旅客編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/user/userMemberCenter.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer userId = null;
			try {
				userId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("userId", "旅客編號格式不正確");
//				errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/user/userMemberCenter.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrdService ordSvc = new OrdService();
			List<OrdVO> ordVO = ordSvc.getOneUser(userId);
			if (ordVO == null) {
				errorMsgs.put("userId", "查無資料");
//				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/user/userMemberCenter.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ordVO", ordVO); // 資料庫取出的ordVO物件,存入req
			session.setAttribute("ordVO", ordVO);

			String url = "/user/ordPakage/userIdGetOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrd.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("ordId");
			System.out.println("ordId="+str);
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("ordId", "請輸入訂單編號");
//				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer ordId = null;
			try {
				ordId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("ordId", "訂單編號格式不正確");
//				errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrdService ordSvc = new OrdService();
			OrdVO ordVO = ordSvc.getOneOrd(ordId);
			if (ordVO == null) {
				errorMsgs.put("ordId", "查無資料");
//				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ordVO", ordVO); // 資料庫取出的ordVO物件,存入req
			session.setAttribute("ordVO", ordVO);

			String url = "/user/ordPakage/listOneOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrd.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllOrd.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer ordId = Integer.valueOf(req.getParameter("ordId"));

			/*************************** 2.開始查詢資料 ****************************************/
			OrdService ordSvc = new OrdService();
			OrdVO ordVO = ordSvc.getOneOrd(ordId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?ordId=" + ordVO.getOrdId() + 
							"&userId=" + ordVO.getUserId() + 
							"&hotelId=" + ordVO.getHotelId() + 
							"&userName=" + ordVO.getUserName() + 
							"&hotelName=" + ordVO.getHotelName() + 
							"&ordDate=" + ordVO.getOrdDate() + 
							"&ordCheckin=" + ordVO.getOrdCheckin() + 
							"&ordCheckout=" + ordVO.getOrdCheckout() + 
							"&ordNights=" + ordVO.getOrdNights() + 
							"&ordRemark=" + ordVO.getOrdRemark();

			String url = "/user/ordPakage/update_ord_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ord_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_ord_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer ordId = null;
				try {
					ordId = Integer.valueOf(req.getParameter("ordId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("ordId","請填數字");
				}
				
				Integer userId = null;
				try {
					userId = Integer.valueOf(req.getParameter("userId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("userId","請填數字");
				}
				
				Integer hotelId = null;
				try {
					hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("hotelId","請填數字");
				}
				
				String userName = req.getParameter("userName");
				String userNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (userName == null || userName.trim().length() == 0) {
					errorMsgs.put("userName","旅客名稱: 請勿空白");
				} else if(!userName.trim().matches(userNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("userName","旅客名稱: 只能是中、英文字母、數字和_ , 且長度必需在10字以內");
	            }
				
				String hotelName = req.getParameter("hotelName");
				String hotelNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (hotelName == null || hotelName.trim().length() == 0) {
					errorMsgs.put("hotelName","飯店名稱: 請勿空白");
				} else if(!hotelName.trim().matches(hotelNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("hotelName","飯店名稱: 只能是中、英文字母、數字和_ , 且長度必需在10字以內");
	            }
				
				java.sql.Date ordDate = null;
				try {
					ordDate = java.sql.Date.valueOf(req.getParameter("ordDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("ordDate","請輸入日期");
				}
				
				java.sql.Date ordCheckin = null;
				try {
					ordCheckin = java.sql.Date.valueOf(req.getParameter("ordCheckin").trim());
					System.out.println("ordCheckin = "+ordCheckin);
//					ordCheckin = req.getParameter("ordCheckin").trim();

				} catch (IllegalArgumentException e) {
					errorMsgs.put("ordCheckin","請輸入日期");
				}
				
				java.sql.Date ordCheckout = null;
				try {
					ordCheckout = java.sql.Date.valueOf(req.getParameter("ordCheckout").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("ordCheckout","請輸入日期");
				}
				
				Integer ordNights = null;
				try {
					ordNights = Integer.valueOf(req.getParameter("ordNights").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("ordNights","入住天數請填數字");
				}
				
				String ordRemark = req.getParameter("ordRemark").trim();
			
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/update_ord_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.updateOrd(ordId, userId ,hotelId ,userName ,hotelName ,ordDate ,ordCheckin ,ordCheckout ,ordNights ,ordRemark);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // 資料庫update成功後,正確的的ordVO物件,存入req
				String url = "/user/ordPakage/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneOrd.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addOrd.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			Integer userId = null;
			try {
				userId = Integer.valueOf(req.getParameter("userId").trim());
				System.out.println("servlet userId="+userId);
			} 
				catch (NumberFormatException e) {
//				errorMsgs.put("userId","請填數字");
//				errorMsgs.put("請先登入 ",">> 填寫Email與密碼");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/user/loginForUser.jsp");
				failureView.forward(req, res);
				return;
				}
			
			Integer hotelId = null;
			try {
				hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
				System.out.println("servlet hotelId="+hotelId);
			} catch (NumberFormatException e) {
				errorMsgs.put("hotelId","請填數字");
			}
			
			String userName = req.getParameter("userName");
			String userNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (userName == null || userName.trim().length() == 0) {
				errorMsgs.put("userName","旅客名稱: 請勿空白");

			} else if(!userName.trim().matches(userNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("userName","旅客名稱: 只能是中、英文字母、數字和_ , 且長度必需在10字以內");
            }
			
			String hotelName = req.getParameter("hotelName");
			System.out.println("servlet hotelName="+hotelName);

			String hotelNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (hotelName == null || hotelName.trim().length() == 0) {
				errorMsgs.put("hotelName","飯店名稱: 請勿空白");
			} else if(!hotelName.trim().matches(hotelNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("hotelName","飯店名稱: 只能是中、英文字母、數字和_ , 且長度必需在10字以內");
            }
			
			java.sql.Date ordDate = null;
			try {
				ordDate = java.sql.Date.valueOf(req.getParameter("ordDate").trim());
				System.out.println("servlet ordDate="+ordDate);

			} catch (IllegalArgumentException e) {
				errorMsgs.put("ordDate","請輸入日期");
			}
			
			java.sql.Date ordCheckin = null;
			try {
				ordCheckin = java.sql.Date.valueOf(req.getParameter("ordCheckin").trim());
				System.out.println("servlet ordCheckin="+ordCheckin);

			} catch (IllegalArgumentException e) {
				errorMsgs.put("ordCheckin","請輸入日期");
			}
			
			java.sql.Date ordCheckout = null;
			try {
				ordCheckout = java.sql.Date.valueOf(req.getParameter("ordCheckout").trim());
				System.out.println("servlet ordCheckout="+ordCheckout);

			} catch (IllegalArgumentException e) {
				errorMsgs.put("ordCheckout","請輸入日期");
			}
			
			Integer ordNights = null;
			try {
				ordNights = Integer.valueOf(req.getParameter("ordNights").trim());
				System.out.println("servlet ordNights="+ordNights);

			} catch (NumberFormatException e) {
				errorMsgs.put("ordNights","入住天數請填數字");
			}
			
			String ordRemark = req.getParameter("ordRemark").trim();
			System.out.println("servlet ordRemark="+ordRemark);

			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/user/loginForUser.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.addOrd(userId ,hotelId ,userName ,hotelName ,ordDate ,ordCheckin ,ordCheckout ,ordNights ,ordRemark);
				
				req.setAttribute("ordVO", ordVO);
				session.setAttribute("ordVO", ordVO);
				OrdVO newOrdId =ordSvc.getNewOrdId(userId);
				session.setAttribute("newOrdId", newOrdId);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/hotelDetail/hotelDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrd.jsp
				successView.forward(req, res);	
			}
		
		
		if ("delete".equals(action)) { // 來自listAllOrd.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer ordId = Integer.valueOf(req.getParameter("ordId"));
				
				/***************************2.開始刪除資料***************************************/
				OrdService ordSvc = new OrdService();
				ordSvc.deleteOrd(ordId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/user/ordPakage/listAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}

}
