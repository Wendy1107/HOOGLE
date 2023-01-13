package tw.com.hoogle.roompic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.com.hoogle.roompic.model.RoompicService;
import tw.com.hoogle.roompic.model.RoompicVO;


@WebServlet("/roompic/RoompicServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoompicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("roompicId");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.put("roompicId", "請輸入編號");
//						errorMsgs.add("請輸入編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/roompic/select_roompic_page.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					
					Integer roompicId = null;
					try {
						roompicId = Integer.valueOf(str);
					} catch (Exception e) {
						errorMsgs.put("roompicId","編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/roompic/select_roompic_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
						/***************************2.開始查詢資料*****************************************/
//						
						
					RoompicService roompicSvc = new RoompicService();
						RoompicVO roompicVO = roompicSvc.getOneRoompic(roompicId);
						if (roompicVO == null) {
							errorMsgs.put("roompicId","查無資料");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/roompic/select_roompic_page.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						req.setAttribute("roompicVO", roompicVO); // 資料庫取出的roompicVO物件,存入req
						String url = "/roompic/listOneRoompic.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoompic.jsp
						successView.forward(req, res);
				}
				
				
			if ("getOne_For_Update".equals(action)) { // 來自listAllRoompic.jsp的請求

					Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
						/***************************1.接收請求參數****************************************/
						Integer roompicId = Integer.valueOf(req.getParameter("roompicId"));
						
						/***************************2.開始查詢資料****************************************/
						RoompicService roompicSvc = new RoompicService();
						RoompicVO roompicVO = roompicSvc.getOneRoompic(roompicId);
										
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						String param = "?roompicId="  +roompicVO.getRoompicId()+
								       "&roomAuto="   +roompicVO.getRoomAuto()+
						               "&roomType="   +roompicVO.getRoomType()+
						               "&roompicPic=" +roompicVO.getRoompicPic();
						String url = "/roompic/update_roompic_input.jsp"+param;
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_service_input.jsp
						successView.forward(req, res);
				}
//				
//				
				if ("update".equals(action)) { // 來自update_service_input.jsp的請求
					
					Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
					req.setAttribute("errorMsgs", errorMsgs);
				
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/			
						
						 
						
						Integer roompicId = null;
						try {
							roompicId = Integer.valueOf(req.getParameter("roompicId").trim());
						} catch (NumberFormatException e) {
							errorMsgs.put("roompicId","請填數字");
						}
						
						Integer roomAuto = null;
						try {
							roomAuto = Integer.valueOf(req.getParameter("roomAuto").trim());
						} catch (NumberFormatException e) {
							errorMsgs.put("roomAuto","請填數字");
						}
						
						
						
						String roomType = req.getParameter("roomType");
						String roomTypeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
						if (roomType == null || roomType.trim().length() == 0) {
							errorMsgs.put("roomType","欄位: 請勿空白");
						} else if(!roomType.trim().matches(roomTypeReg)) { //以下練習正則(規)表示式(regular-expression)
							errorMsgs.put("roomType","欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			            }
						
						byte[] roompicPic = null;

						Part part = req.getPart("roompicPic");
						if (part == null || part.getSize() == 0) {
							errorMsgs.put("roompicPic", "請上傳一張圖片");
						}
						
						InputStream is = part.getInputStream();
						roompicPic = new byte[is.available()];
						is.read(roompicPic);
						is.close();
						
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/roompic/update_roompic_input.jsp");
							failureView.forward(req, res);
							return; //程式中斷
						}
						
						
//						/***************************2.開始修改資料*****************************************/
						RoompicService roompicSvc = new RoompicService();
						RoompicVO roompicVO = roompicSvc.updateRoompic(roompicId, roomAuto,roomType,roompicPic);
						
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						req.setAttribute("roompicVO", roompicVO); // 資料庫update成功後,正確的的roompicVO物件,存入req
						String url = "/roompic/listOneRoompic.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoompic.jsp
						successView.forward(req, res);
				}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				RoompicVO roompicVO = new RoompicVO();
				
				Integer roomAuto = null;
				
				try {
					roomAuto = Integer.valueOf(req.getParameter("roomAuto"));
				} catch (NumberFormatException e) {
					errorMsgs.add("房型流水號必須是數字");
				}
				

				String roomType = req.getParameter("roomType");
				if (roomType == null || roomType.trim().length() == 0) {
					errorMsgs.add("房型種類不可為空");
				}

				byte[] roompicPic = null;

				Part part = req.getPart("roompicPic");
				if (part == null || part.getSize() == 0) {
					errorMsgs.add("請上傳一張圖片");
				}
				
				InputStream is = part.getInputStream();
				roompicPic = new byte[is.available()];
				is.read(roompicPic);
				is.close();
				
				
				roompicVO.setRoomAuto(roomAuto);
				roompicVO.setRoomType(roomType);
				roompicVO.setRoompicPic(roompicPic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roompicVO", roompicVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/roompic/addRoompic.jsp");
					failureView.forward(req, res);
					return;
				}

				// 新增
				RoompicService roompicSvc = new RoompicService();
				roompicVO = roompicSvc.addRoompic(roomAuto, roomType, roompicPic);

				// 轉交
				String url = "/roompic/listAllRoompic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交到旅客總表listAllUser.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/roompic/addRoompic.jsp");
				failureView.forward(req, res);
			}
	
			if ("delete".equals(action)) { // 來自listAllRoompic.jsp

				Map<String,String> errorMsgs1 = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsg", errorMsgs1);

					/***************************1.接收請求參數***************************************/
					Integer roompicId = Integer.valueOf(req.getParameter("roompicId"));
					
					/***************************2.開始刪除資料***************************************/
					RoompicService roompicSvc = new RoompicService();
					roompicSvc.deleteRoompic(roompicId);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/roompic/listAllRoompic.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
			}

		}

	}

}
