package tw.com.hoogle.orddetail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.hoogle.orddetail.model.OrdDetailService;
import tw.com.hoogle.orddetail.model.OrdDetailVO;

@WebServlet("/OrddetailServlet")
//@WebServlet(name="OrddetailServlet",urlPatterns="/orddetail/orddetail.do")
public class OrdDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrdDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("orddetailId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("orddetailID", "請輸入訂單明細編號");
//				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/orddetail/select_orddetail_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer orddetailId = null;
			try {
				orddetailId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("orddetailId", "訂單明細編號格式不正確");
//				errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/orddetail/select_orddetail_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrdDetailService orddetailSvc = new OrdDetailService();
			OrdDetailVO orddetailVO = orddetailSvc.getOneOrddetail(orddetailId);
			if (orddetailVO == null) {
				errorMsgs.put("orddetailId", "查無資料");
//				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/orddetail/select_orddetail_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orddetailVO", orddetailVO); // 資料庫取出的ordVO物件,存入req
			String url = "/orddetail/listOneOrdDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrdDetail.jsp
			successView.forward(req, res);
		}

		if ("OrdId_getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("ordId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("ordId", "請輸入訂單編號");
//				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/user/ordSearch.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer ordId = null;
			try {
				ordId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("ordId", "訂單編號格式不正確");
				//errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/user/ordSearch.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrdDetailService orddetailSvc = new OrdDetailService();
			List<OrdDetailVO> orddetailVO = orddetailSvc.getOneOrd(ordId);
			if (ordId == null) {
				errorMsgs.put("ordId", "查無資料");
//				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/user/ordSearch.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			session.setAttribute("orddetailVO", orddetailVO); // 資料庫取出的orddetailVO物件,存入req
			
			String url = "/user/ordPakage/listAllOrdDetailByOrdId.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrdDetail.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllOrddetail.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer orddetailId = Integer.valueOf(req.getParameter("orddetailId"));

			/*************************** 2.開始查詢資料 ****************************************/
			OrdDetailService orddetailSvc = new OrdDetailService();
			OrdDetailVO orddetailVO = orddetailSvc.getOneOrddetail(orddetailId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?orddetailId=" + orddetailVO.getOrddetailId() + 
							"&ordId=" + orddetailVO.getOrdId() + 
							"&roomAuto=" + orddetailVO.getRoomAuto() + 
							"&roomNumber=" + orddetailVO.getRoomNumber();

			String url = "/orddetail/update_orddetail_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_orddetail_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_orddetail_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer orddetailId = null;
				try {
					orddetailId = Integer.valueOf(req.getParameter("orddetailId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("orddetailId","請填數字");
				}
				
				Integer ordId = null;
				try {
					ordId = Integer.valueOf(req.getParameter("ordId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("ordId","請填數字");
				}
				
				Integer roomAuto = null;
				try {
					roomAuto = Integer.valueOf(req.getParameter("roomAuto").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("roomAuto","請填數字");
				}
				
				Integer roomNumber = null;
				try {
					roomNumber = Integer.valueOf(req.getParameter("roomNumber").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("roomNumber","請填數字");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/orddetail/update_orddetail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				OrdDetailService orddetailSvc = new OrdDetailService();
				OrdDetailVO orddetailVO = orddetailSvc.updateOrddetail(orddetailId, ordId, roomAuto, roomNumber);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("orddetailVO", orddetailVO); // 資料庫update成功後,正確的的ordVO物件,存入req
				String url = "/orddetail/listOneOrdDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneOrdDetail.jsp
				successView.forward(req, res);
		}

if ("reserve".equals(action)) { // 來自addOrd.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			Integer ordId = null;
			try {
				ordId = Integer.valueOf(req.getParameter("ordId").trim());
				System.out.println("ordId="+ordId);
			} catch (NumberFormatException e) {
				errorMsgs.put("ordId","請填數字");
			}
			
			Integer roomAuto1 = null;
			try {
				roomAuto1 = Integer.valueOf(req.getParameter("roomAuto1").trim());
				System.out.println("roomAuto1="+roomAuto1);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomAuto1","請填數字");
			}
			
			Integer roomAuto2 = null;
			try {
				roomAuto2 = Integer.valueOf(req.getParameter("roomAuto2").trim());
				System.out.println("roomAuto2="+roomAuto2);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomAuto2","請填數字");
			}
			
			Integer roomAuto3 = null;
			try {
				roomAuto3 = Integer.valueOf(req.getParameter("roomAuto3").trim());
				System.out.println("roomAuto3="+roomAuto3);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomAuto3","請填數字");
			}
			
			
			Integer roomNumber1 = null;
			try {
				roomNumber1 = Integer.valueOf(req.getParameter("roomNumber1").trim());
				System.out.println("roomNumber1="+roomNumber1);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomNumber1","請填數字");
			}
			
			Integer roomNumber2 = null;
			try {
				roomNumber2 = Integer.valueOf(req.getParameter("roomNumber2").trim());
				System.out.println("roomNumber2="+roomNumber2);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomNumber2","請填數字");
			}
			
			Integer roomNumber3 = null;
			try {
				roomNumber3 = Integer.valueOf(req.getParameter("roomNumber3").trim());
				System.out.println("roomNumber3="+roomNumber3);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomNumber3","請填數字");
			}
			
			Integer nonreserved4001 = null;
			try {
				nonreserved4001 = Integer.valueOf(req.getParameter("nonreserved4001").trim());
				nonreserved4001 = nonreserved4001 - roomNumber1;
				System.out.println("nonreserved4001="+nonreserved4001);
			}catch(NumberFormatException e) {
				errorMsgs.put("nonreserved4001","請填數字");
			}
			Integer nonreserved4002 = null;
			try {
				nonreserved4002 = Integer.valueOf(req.getParameter("nonreserved4002").trim());
				nonreserved4002 = nonreserved4002 - roomNumber2;
				System.out.println("nonreserved4002="+nonreserved4002);
			}catch(NumberFormatException e) {
				errorMsgs.put("nonreserved4002","請填數字");
			}
			Integer nonreserved4003 = null;
			try {
				nonreserved4003 = Integer.valueOf(req.getParameter("nonreserved4003").trim());
				nonreserved4003 = nonreserved4003 - roomNumber3;
				System.out.println("nonreserved4003="+nonreserved4003);
			}catch(NumberFormatException e) {
				errorMsgs.put("nonreserved4003","請填數字");
			}
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hotelDetail/hotelDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				int ordNights = Integer.valueOf(req.getParameter("ordNights").trim());
				int money1=0, money2=0, money3=0;
				if(roomAuto1 == 4001) {
					money1 = 2000*roomNumber1*ordNights;
				}
				if(roomAuto2 == 4002) {
					money2 = 3500*roomNumber2*ordNights;
				}
				if(roomAuto3 == 4003) {
					money3 = 6000*roomNumber3*ordNights;
				}
				int money =money1 + money2 + money3;
				
				session.setAttribute("ordId", ordId);
				session.setAttribute("roomNumber1", roomNumber1);
				session.setAttribute("roomNumber2", roomNumber2);
				session.setAttribute("roomNumber3", roomNumber3);
				session.setAttribute("nonreserved4001",nonreserved4001);
				session.setAttribute("nonreserved4002",nonreserved4002);
				session.setAttribute("nonreserved4003",nonreserved4003);
				session.setAttribute("money", money);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/orddetail/payPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrd.jsp
				successView.forward(req, res);	
		}
		
        if ("insert".equals(action)) { // 來自addOrd.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			Integer ordId = null;
			try {
				ordId = Integer.valueOf(req.getParameter("ordId").trim());
				System.out.println("ordId="+ordId);
			} catch (NumberFormatException e) {
				errorMsgs.put("ordId","請填數字");
			}
			
			Integer roomAuto1 = null;
			try {
				roomAuto1 = Integer.valueOf(req.getParameter("roomAuto1").trim());
				System.out.println("roomAuto1="+roomAuto1);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomAuto1","請填數字");
			}
			
			Integer roomAuto2 = null;
			try {
				roomAuto2 = Integer.valueOf(req.getParameter("roomAuto2").trim());
				System.out.println("roomAuto2="+roomAuto2);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomAuto2","請填數字");
			}
			
			Integer roomAuto3 = null;
			try {
				roomAuto3 = Integer.valueOf(req.getParameter("roomAuto3").trim());
				System.out.println("roomAuto3="+roomAuto3);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomAuto3","請填數字");
			}
			
			Integer roomNumber1 = null;
			try {
				roomNumber1 = Integer.valueOf(req.getParameter("roomNumber1").trim());
				System.out.println("roomNumber1="+roomNumber1);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomNumber1","請填數字");
			}
			
			Integer roomNumber2 = null;
			try {
				roomNumber2 = Integer.valueOf(req.getParameter("roomNumber2").trim());
				System.out.println("roomNumber2="+roomNumber2);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomNumber2","請填數字");
			}
			
			Integer roomNumber3 = null;
			try {
				roomNumber3 = Integer.valueOf(req.getParameter("roomNumber3").trim());
				System.out.println("roomNumber3="+roomNumber3);
			} catch (NumberFormatException e) {
				errorMsgs.put("roomNumber3","請填數字");
			}
			
			Integer nonreserved4001 = null;
			try {
				nonreserved4001 = Integer.valueOf(req.getParameter("nonreserved4001").trim());
				System.out.println("nonreserved4001="+nonreserved4001);
			}catch(NumberFormatException e) {
				errorMsgs.put("nonreserved4001","請填數字");
			}
			Integer nonreserved4002 = null;
			try {
				nonreserved4002 = Integer.valueOf(req.getParameter("nonreserved4002").trim());
				System.out.println("nonreserved4002="+nonreserved4002);
			}catch(NumberFormatException e) {
				errorMsgs.put("nonreserved4002","請填數字");
			}
			Integer nonreserved4003 = null;
			try {
				nonreserved4003 = Integer.valueOf(req.getParameter("nonreserved4003").trim());
				System.out.println("nonreserved4003="+nonreserved4003);
			}catch(NumberFormatException e) {
				errorMsgs.put("nonreserved4003","請填數字");
			}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("123");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				OrdDetailService orddetailSvc = new OrdDetailService();
				orddetailSvc.addOrddetail(ordId, roomAuto1, roomNumber1);
				orddetailSvc.addOrddetail(ordId, roomAuto2, roomNumber2);
				orddetailSvc.addOrddetail(ordId, roomAuto3, roomNumber3);
				
				OrdDetailVO orddetailVO1 = orddetailSvc.updateNonreserved(nonreserved4001, roomAuto1);
				OrdDetailVO orddetailVO2 = orddetailSvc.updateNonreserved(nonreserved4002, roomAuto2);
				OrdDetailVO orddetailVO3 = orddetailSvc.updateNonreserved(nonreserved4003, roomAuto3);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/orddetail/thanks.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrd.jsp
				successView.forward(req, res);	
				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllOrd.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer orddetailId = Integer.valueOf(req.getParameter("orddetailId"));
				
				/***************************2.開始刪除資料***************************************/
				OrdDetailService orddetailSvc = new OrdDetailService();
				orddetailSvc.deleteOrddetail(orddetailId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/orddetail/listAllOrdDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}

}
