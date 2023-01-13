package tw.com.hoogle.servicelist.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.com.hoogle.ord.model.OrdService;
import tw.com.hoogle.ord.model.OrdVO;
import tw.com.hoogle.servicelist.model.ServiceListService;
import tw.com.hoogle.servicelist.model.ServiceListVO;


@WebServlet("/servicelist/ServiceListServlet")
public class ServiceListServlet extends HttpServlet {

//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("serviceListId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("serviceListId", "請輸入編號");
//				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/servicelist/select_servicelist_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer serviceListId = null;
			try {
				serviceListId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("serviceListId", "編號格式不正確");
//				errorMsgs.add("編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/servicelist/select_servicelist_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ServiceListService servicelistSvc = new ServiceListService();
			ServiceListVO servicelistVO = servicelistSvc.getOneServiceList(serviceListId);
			if (servicelistVO == null) {
				errorMsgs.put("serviceListId", "查無資料");
//				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/servicelist/select_servicelist_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("servicelistVO", servicelistVO); // 資料庫取出的servicelistVO物件,存入req
			String url = "/servicelist/listOneServiceList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrd.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllOrd.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer serviceListId = Integer.valueOf(req.getParameter("serviceListId"));

			/*************************** 2.開始查詢資料 ****************************************/

			
			ServiceListService servicelistSvc = new ServiceListService();
			ServiceListVO servicelistVO = servicelistSvc.getOneServiceList(serviceListId);
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?serviceListId=" + servicelistVO.getServiceListId() + 
							"&hotelId=" + servicelistVO.getHotelId() + 
							"&serviceId=" + servicelistVO.getServiceId();
							

			String url = "/servicelist/update_servicelist_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_servicelist_input.jsp
			successView.forward(req, res);
		}

///================================================================================================		
		if ("update".equals(action)) { // 來自update_food_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/********1.接收請求參數 - 輸入格式的錯誤處理*******/
			
				Integer serviceListId = null;
				try {
					serviceListId = Integer.valueOf(req.getParameter("serviceListId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("serviceListId","請填數字");
				}
				
				Integer hotelId = null;
				try {
					hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("hotelId","請填數字");
				}
				
				Integer serviceId = null;
				try {
					serviceId = Integer.valueOf(req.getParameter("serviceId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("serviceId","請填數字");
				}
				
							
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/food/update_food_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***********2.開始修改資料************/
				ServiceListService servicelistSvc = new ServiceListService();
				ServiceListVO servicelistVO = servicelistSvc.updateServiceList(serviceListId,hotelId,serviceId);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("servicelistVO", servicelistVO); // 資料庫update成功後,正確的的servicelistVO物件,存入req
				String url = "/servicelist/listOneServiceList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneServiceList.jsp
				successView.forward(req, res);
		}
//=====================================================================================================================
   if ("insert".equals(action)) { // 來自addServiceList.jsp的請求  
System.out.println("IN INSERT");
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		Integer hotelId = null;
		try {
			hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("hotelId","請填數字");
		}
		
		
		
		Integer serviceId = null;
		try {
			serviceId = Integer.valueOf(req.getParameter("serviceId").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("serviceId","請填數字");
		}
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/food/update_food_input.jsp");
			failureView.forward(req, res);
			return; //程式中斷
		}
//			
//			/***************************2.開始新增資料***************************************/
			
			ServiceListService servicelistSvc = new ServiceListService();
			servicelistSvc.addServiceList(hotelId,serviceId);
			
			
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/servicelist/listAllServicelist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFood.jsp
			successView.forward(req, res);				
	}
	
	
	if ("delete".equals(action)) { // 來自listAllFood.jsp

		Map<String,String> errorMsgs1 = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsg", errorMsgs1);

			/***************************1.接收請求參數***************************************/
			Integer serviceListId = Integer.valueOf(req.getParameter("serviceListId"));
			
			/***************************2.開始刪除資料***************************************/
			ServiceListService servicelistSvc = new ServiceListService();
			servicelistSvc.deleteServiceList(serviceListId);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/servicelist/listAllServicelist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	}
}
	}
