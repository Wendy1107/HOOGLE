package tw.com.hoogle.commend.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import tw.com.hoogle.commend.model.CommendService;
import tw.com.hoogle.commend.model.CommendVO;
import tw.com.hoogle.ord.model.OrdService;
import tw.com.hoogle.ord.model.OrdVO;

@WebServlet("/commend/commend.do")
public class CommendServlet extends HttpServlet {

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
//=======================================================================================
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//			System.out.println("***** into getOne_For_Display *******");
//			Map<String, String> errorMsgs = new Linkedne_For_DHashMap<String, String>();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("commendAuto");
			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.put("ordID", "請輸入訂單編號");
				errorMsgs.add("請輸入評價編號");
			}
//			System.out.println(str);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/commend/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer commendAuto = null;
			try {
				commendAuto = Integer.valueOf(str);
			} catch (Exception e) {
//				errorMsgs.put("commendAuto", "評價編號格式不正確");
				errorMsgs.add("評價編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/commend/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CommendService commendSvc = new CommendService();
			CommendVO commendVO = commendSvc.getOneCommend(commendAuto);
			if (commendVO == null) {
//				errorMsgs.put("commendAuto", "查無資料");
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/commend/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("commendVO", commendVO); // 資料庫取出的commendVO物件,存入req
			String url = "/commend/listOneCommend.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrd.jsp
			successView.forward(req, res);
		}

//=======================================================================================
		if ("getOne_For_Update".equals(action)) { // 來自listAllOrd.jsp的請求

//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer commendAuto = Integer.valueOf(req.getParameter("commendAuto"));

			/*************************** 2.開始查詢資料 ****************************************/
			CommendService commendSvc = new CommendService();
			CommendVO commendVO = commendSvc.getOneCommend(commendAuto);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("commendVO", commendVO);

			String url = "/commend/update_commend_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ord_input.jsp
			successView.forward(req, res);
		}
		
//=======================================================================================
		if ("update".equals(action)) { // 來自update_commend_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer commendAuto = Integer.valueOf(req.getParameter("commendAuto").trim());

			Integer ordId = null;
			try {
				ordId = Integer.valueOf(req.getParameter("ordId").trim());
			} catch (Exception e) {
				errorMsgs.add("訂單編號格式不正確");
			}

			String gradeStr = req.getParameter("stars");
			Integer commendGrade = null;
			if (gradeStr == null || gradeStr.isEmpty()) {
				errorMsgs.add("請選擇評價等級");
			} else {
				commendGrade = Integer.valueOf(gradeStr);
				}
			
			String commendContent = req.getParameter("commendContent");
			if (commendContent == null || commendContent.trim().length() == 0) {
				errorMsgs.add("請填寫評論");
			} else if (commendContent.trim().length() > 500) {
				errorMsgs.add("評價內容500字以內");
			}

			java.sql.Date commendDate = null;
//			try {
//				commendDate = java.sql.Date.valueOf(req.getParameter("commendDate").trim());
//			} catch (IllegalArgumentException e) {
////				errorMsgs.put("commendDate","請輸入日期");
//				errorMsgs.add("請輸入日期");
//			}

			CommendVO commendVO = new CommendVO();
			commendVO.setCommendAuto(commendAuto);
			commendVO.setOrdId(ordId);
			commendVO.setCommendGrade(commendGrade);
			commendVO.setCommendContent(commendContent);
			commendVO.setCommendDate(commendDate);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("commendVO", commendVO); // 含有輸入格式錯誤的commendVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/commend/update_commend_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			CommendService commendSvc = new CommendService();
			commendVO = commendSvc.updateCommend(commendVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("commendVO", commendVO); // 資料庫update成功後,正確的的commendVO物件,存入req
			String url = "/commend/updateSuccess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCommend.jsp
			successView.forward(req, res);
		}

//=======================================================================================		
		if ("insert".equals(action)) { // 來自addCommend.jsp的請求
			System.out.println("action = insert");
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
			CommendService commendSvc = new CommendService();
//			String hotelName1 = req.getParameter("hotelName1");
//			String str = req.getParameter("ordId");
			Integer ordId = Integer.valueOf(req.getParameter("ordId"));
//			System.out.println("ordId="+ordId);
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入訂單編號");
//			}
//			else if(commendSvc.findByOrdId(str).size() > 0) {
//				errorMsgs.add("訂單編號不可重複");	
//			}
//			Integer ordId = null;
//			try {
//				ordId = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("訂單編號格式不正確");
//			}
//			
			String gradeStr = req.getParameter("stars");
			Integer commendGrade = null;
			if (gradeStr == null || gradeStr.isEmpty()) {
				errorMsgs.add("請選擇評價星星");
			} else {
				commendGrade = Integer.valueOf(gradeStr);
				}
//			System.err.println(gradeStr);
			
			String commendContent = req.getParameter("commendContent");
			if (commendContent == null || commendContent.trim().length() == 0) {
//				errorMsgs.put("評價內容", "請勿空白");
				errorMsgs.add("請輸入評價內容");
			} else if (commendContent.trim().length() > 500) {
				errorMsgs.add("評價內容500字以內");
			}
//			System.err.println(commendContent);

//			java.sql.Date commendDate = null;
//			try {
//				commendDate = java.sql.Date.valueOf(req.getParameter("commendDate").trim());
//			} catch (IllegalArgumentException e) {
//				errorMsgs.put("commendDate","請輸入日期");
//				errorMsgs.add("請輸入日期");
//			}

			CommendVO commendVO = new CommendVO();
			commendVO.setOrdId(ordId);
			commendVO.setCommendGrade(commendGrade);
			commendVO.setCommendContent(commendContent);
//			commendVO.setCommendDate(commendDate);
			session.setAttribute("ordId", ordId);
				System.out.println("ordId="+ordId);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/commend/addCommend.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			// 開始新增資料
			commendSvc = new CommendService();
			commendVO = commendSvc.addCommend(commendVO);
			System.out.println("開始新增資料");

			// 新增完成，準備轉交
			String url = "/commend/addSuccess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交到旅客總表listAllCommend.jsp
			successView.forward(req, res);
		}

//=======================================================================================		
		if ("delete".equals(action)) { // 來自listAllCommend.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer commendAuto = Integer.valueOf(req.getParameter("commendAuto"));

			/*************************** 2.開始刪除資料 ***************************************/
			CommendService commendSvc = new CommendService();
			commendSvc.deleteCommend(commendAuto);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/commend/listAllCommend.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}
}
