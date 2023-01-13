package tw.com.hoogle.food.controller;

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

import tw.com.hoogle.food.model.FoodService;
import tw.com.hoogle.food.model.FoodVO;

@WebServlet("/food/FoodServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class FoodServlet extends HttpServlet {

//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("foodPicid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("foodPicid", "請輸入編號");
//				errorMsgs.add("請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/food/select_food_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer foodPicid = null;
			try {
				foodPicid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("foodPicid","編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/food/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
				/***************************2.開始查詢資料*****************************************/
//				
				
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(foodPicid);
				if (foodVO == null) {
					errorMsgs.put("foodPicid","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/food/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodVO", foodVO); // 資料庫取出的foodVO物件,存入req
				String url = "/food/listOneFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFood.jsp
				successView.forward(req, res);
		}
		
		
	if ("getOne_For_Update".equals(action)) { // 來自listAllFood.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer foodPicid = Integer.valueOf(req.getParameter("foodPicid"));
				
				/***************************2.開始查詢資料****************************************/
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(foodPicid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?foodPicid="      +foodVO.getFoodPicid()+
						       "&restaurantId="   +foodVO.getRestaurantId()+
						       "&foodPic="        +foodVO.getFoodPic()+
						       "&foodName="       +foodVO.getFoodName();
				String url = "/food/update_food_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_food_input.jsp
				successView.forward(req, res);
		}
//		
//		
		if ("update".equals(action)) { // 來自update_food_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/			
				
//				Integer foodPicid = null;
//				try {
//					foodPicid = Integer.valueOf(req.getParameter("foodPicid").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("foodPicid","請填數字");
//				}
				
				Integer foodPicid = Integer.valueOf(req.getParameter("foodPicid"));
				 
				
				Integer restaurantId = null;
				try {
					restaurantId = Integer.valueOf(req.getParameter("restaurantId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("restaurantId","請填數字");
				}
				
				
				byte[] foodPic = null;

				Part part = req.getPart("foodPic");
				if (part == null || part.getSize() == 0) {
					errorMsgs.put("foodPic", "請上傳一張圖片");
				}
				
				InputStream is = part.getInputStream();
				foodPic = new byte[is.available()];
				is.read(foodPic);
				is.close();
//				Part part = req.getPart("foodPic");
//				InputStream is = part.getInputStream();
//				byte[] foodPic = is.readAllBytes();
//				is.close();
			//	byte[]foodPic = null;
				
				String foodName = req.getParameter("foodName");
				String foodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (foodName == null || foodName.trim().length() == 0) {
					errorMsgs.put("restaurantId","欄位: 請勿空白");
				} else if(!foodName.trim().matches(foodNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("restaurantId","欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
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
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.updateFood(foodPicid,  restaurantId, foodPic,  foodName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodVO", foodVO); // 資料庫update成功後,正確的的foodVO物件,存入req
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
		Integer foodPicid = null;
		try {
			foodPicid = Integer.valueOf(req.getParameter("foodPicid").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("foodPicid","請填數字");
		}
		
		Integer restaurantId = null;
		try {
			restaurantId = Integer.valueOf(req.getParameter("restaurantId").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("restaurantId","請填數字");
		}
		
		
		Part part = req.getPart("foodPic");
		InputStream is = part.getInputStream();
		byte[] foodPic = is.readAllBytes();
		is.close();
	//	byte[]foodPic = null;
		
		String foodName = req.getParameter("foodName");
		String foodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (foodName == null || foodName.trim().length() == 0) {
			errorMsgs.put("foodName","欄位: 請勿空白");
		} else if(!foodName.trim().matches(foodNameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("foodName","欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/food/update_food_input.jsp");
			failureView.forward(req, res);
			return; //程式中斷
		}
//			
//			/***************************2.開始新增資料***************************************/
			FoodService foodSvc = new FoodService();
			foodSvc.addFood (restaurantId,foodPic,foodName);
			
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/food/listAllFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFood.jsp
			successView.forward(req, res);				
	}
	
	
	if ("delete".equals(action)) { // 來自listAllFood.jsp

		Map<String,String> errorMsgs1 = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsg", errorMsgs1);

			/***************************1.接收請求參數***************************************/
			Integer foodPicid = Integer.valueOf(req.getParameter("foodPicid"));
			
			/***************************2.開始刪除資料***************************************/
			FoodService foodSvc = new FoodService();
			foodSvc.deleteFood(foodPicid);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/food/listAllFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	}
}
	}
