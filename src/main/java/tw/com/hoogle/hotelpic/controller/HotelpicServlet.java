package tw.com.hoogle.hotelpic.controller;

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

import tw.com.hoogle.hotelpic.model.HotelpicService;
import tw.com.hoogle.hotelpic.model.HotelpicVO;

@WebServlet("/hotelpic/HotelpicServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class HotelpicServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String str = req.getParameter("hotelpicId");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.put("hotelpicId", "請輸入編號");
////				errorMsgs.add("請輸入編號");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/food/select_food_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//			
//			Integer hotelpicId = null;
//			try {
//				hotelpicId = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.put("hotelpicId","編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/food/select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//				/***************************2.開始查詢資料*****************************************/
////				
//				
//				HotelpicService hotelpicSvc = new HotelpicService();
//				HotelpicVO hotelpicVO = hotelpicSvc.getOneHotelpic(hotelpicId);
//				if (hotelpicVO == null) {
//					errorMsgs.put("hotelpicId","查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/food/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("hotelpicVO", hotelpicVO); // 資料庫取出的hotelpicVO物件,存入req
//				String url = "/food/listOneFood.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFood.jsp
//				successView.forward(req, res);
//		}
//		
//		
//	if ("getOne_For_Update".equals(action)) { // 來自listAllFood.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.接收請求參數****************************************/
//				Integer hotelpicId = Integer.valueOf(req.getParameter("hotelpicId"));
//				
//				/***************************2.開始查詢資料****************************************/
//				HotelpicService hotelpicSvc = new HotelpicService();
//				HotelpicVO hotelpicVO = hotelpicSvc.getOneHotelpic(hotelpicId);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				String param = "?hotelpicId="        +hotelpicVO.getHotelpicId()+
//						       "&hotelId="           +hotelpicVO.getHotelId()+
//						       "&hotelpicNo="        +hotelpicVO.getHotelpicNo()+
//						       "&hotelpicName="      +hotelpicVO.getHotelpicName();
//				String url = "/food/update_food_input.jsp"+param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_food_input.jsp
//				successView.forward(req, res);
//		}
//		
//		
		if ("update".equals(action)) { // 來自update_food_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/			
				
				Integer hotelpicId = null;
				try {
					hotelpicId = Integer.valueOf(req.getParameter("hotelpicId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("hotelpicId","請填數字");
				}
				
				 
				
				Integer hotelId = null;
				try {
					hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("hotelId","請填數字");
				}
				
				
				byte[] hotelpicNo = null;

				Part part = req.getPart("hotelpicNo");
				if (part == null || part.getSize() == 0) {
					errorMsgs.put("hotelpicNo", "請上傳一張圖片");
				}
				
				InputStream is = part.getInputStream();
				hotelpicNo = new byte[is.available()];
				is.read(hotelpicNo);
				is.close();
//				Part part = req.getPart("foodPic");
//				InputStream is = part.getInputStream();
//				byte[] foodPic = is.readAllBytes();
//				is.close();
			//	byte[]foodPic = null;
				
				String hotelpicName = req.getParameter("hotelpicName");
				String hotelpicNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (hotelpicName == null || hotelpicName.trim().length() == 0) {
					errorMsgs.put("hotelpicName","欄位: 請勿空白");
				} else if(!hotelpicName.trim().matches(hotelpicNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("hotelpicName","欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/food/update_food_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
//				
////				
//				
				
//				
//				/***************************2.開始修改資料*****************************************/
				HotelpicService hotelpicSvc = new HotelpicService();
				HotelpicVO hotelpicVO = hotelpicSvc.updateHotelpic( hotelpicId,hotelId,hotelpicNo,  hotelpicName);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("hotelpicVO", hotelpicVO); // 資料庫update成功後,正確的的hotelpicVO物件,存入req
				String url = "/food/listOneFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFood.jsp
				successView.forward(req, res);
		}
//
   if ("insert".equals(action)) { // 來自addFood.jsp的請求  
System.out.println("IN INSERT");
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//		Integer hotelpicId = null;
//		try {
//			hotelpicId = Integer.valueOf(req.getParameter("hotelpicId").trim());
//		} catch (NumberFormatException e) {
//			errorMsgs.put("hotelpicId","請填數字");
//		}
//		
//		Integer hotelId = null;
//		try {
//			hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
//		} catch (NumberFormatException e) {
//			errorMsgs.put("hotelId","請填數字");
//		}
		
		
		Part part = req.getPart("hotelpicNo");
		InputStream is = part.getInputStream();
		byte[] hotelpicNo = is.readAllBytes();
		is.close();
	//	byte[]hotelpicNo = null;
		
		String hotelpicName = req.getParameter("hotelpicName");
		String hotelpicNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (hotelpicName == null || hotelpicName.trim().length() == 0) {
			errorMsgs.put("hotelpicName","欄位: 請勿空白");
		} else if(!hotelpicName.trim().matches(hotelpicNameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("hotelpicName","欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/food/update_food_input.jsp");
			failureView.forward(req, res);
			return; //程式中斷
		}
//			
//			/***************************2.開始新增資料***************************************/
			HotelpicService hotelpicSvc = new HotelpicService();
			hotelpicSvc.addHotelpic (null, hotelpicNo,  hotelpicName);
			
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/food/listAllFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFood.jsp
			successView.forward(req, res);				
	}
	
	
	if ("delete".equals(action)) { // 來自listAllFood.jsp

		Map<String,String> errorMsgs1 = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsg", errorMsgs1);

			/***************************1.接收請求參數***************************************/
			Integer hotelpicId = Integer.valueOf(req.getParameter("hotelpicId"));
			
			/***************************2.開始刪除資料***************************************/
			HotelpicService hotelpicSvc = new HotelpicService();
			hotelpicSvc.deletehotelpic(hotelpicId);
						
						
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/food/listAllFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	}
}
	}
