package tw.com.hoogle.service.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hoogle.ord.model.OrdService;
import tw.com.hoogle.ord.model.OrdVO;
import tw.com.hoogle.service.model.ServiceService;
import tw.com.hoogle.service.model.ServiceVO;


@WebServlet("/service/ServiceServlet")
public class ServiceServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("serviceId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("serviceId", "請輸入編號");
//				errorMsgs.add("請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/service/select_service_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer serviceId = null;
			try {
				serviceId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("serviceId","編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/service/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
				/***************************2.開始查詢資料*****************************************/
//				
				
			ServiceService serviceSvc = new ServiceService();
				ServiceVO serviceVO = serviceSvc.getOneService(serviceId);
				if (serviceVO == null) {
					errorMsgs.put("serviceId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/service/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("serviceVO", serviceVO); // 資料庫取出的serviceVO物件,存入req
				String url = "/service/listOneService.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneService.jsp
				successView.forward(req, res);
		}
		
		
	if ("getOne_For_Update".equals(action)) { // 來自listAllService.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer serviceId = Integer.valueOf(req.getParameter("serviceId"));
				
				/***************************2.開始查詢資料****************************************/
				ServiceService serviceSvc = new ServiceService();
				ServiceVO serviceVO = serviceSvc.getOneService(serviceId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?serviceId="      +serviceVO.getServiceId()+
						       "&serviceName="   +serviceVO.getServiceName();
				String url = "/service/update_service_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_service_input.jsp
				successView.forward(req, res);
		}
//		
//		
		if ("update".equals(action)) { // 來自update_service_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/			
				
				 
				
				Integer serviceId = null;
				try {
					serviceId = Integer.valueOf(req.getParameter("serviceId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("serviceId","請填數字");
				}
				
				
				
				
				String serviceName = req.getParameter("serviceName");
				String serviceNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (serviceName == null || serviceName.trim().length() == 0) {
					errorMsgs.put("serviceName","欄位: 請勿空白");
				} else if(!serviceName.trim().matches(serviceNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("serviceName","欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/service/update_service_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
//				/***************************2.開始修改資料*****************************************/
				ServiceService serviceSvc = new ServiceService();
				ServiceVO serviceVO = serviceSvc.updateService(serviceId, serviceName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("serviceVO", serviceVO); // 資料庫update成功後,正確的的serviceVO物件,存入req
				String url = "/service/listOneService.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneService.jsp
				successView.forward(req, res);
		}
//
   if ("insert".equals(action)) { // 來自addService.jsp的請求  
System.out.println("IN INSERT");
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		Integer serviceId = null;
		try {
			serviceId = Integer.valueOf(req.getParameter("serviceId").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("serviceId","請填數字");
		}
		
		
		String serviceName = req.getParameter("serviceName");
		String serviceNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (serviceName == null || serviceName.trim().length() == 0) {
			errorMsgs.put("serviceName","欄位: 請勿空白");
		} else if(!serviceName.trim().matches(serviceNameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("serviceName","欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/service/update_service_input.jsp");
			failureView.forward(req, res);
			return; //程式中斷
		}
//			
//			/***************************2.開始新增資料***************************************/
			ServiceService serviceSvc = new ServiceService();
			serviceSvc.addService (serviceName);
			
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/service/listAllService.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllService.jsp
			successView.forward(req, res);				
	}
	
	
	if ("delete".equals(action)) { // 來自listAllService.jsp

		Map<String,String> errorMsgs1 = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsg", errorMsgs1);

			/***************************1.接收請求參數***************************************/
			Integer serviceId = Integer.valueOf(req.getParameter("serviceId"));
			
			/***************************2.開始刪除資料***************************************/
			ServiceService serviceSvc = new ServiceService();
			serviceSvc.deleteService(serviceId);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/service/listAllService.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	}
}







	}
	



	
