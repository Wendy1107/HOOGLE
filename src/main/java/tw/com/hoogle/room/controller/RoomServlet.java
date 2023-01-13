package tw.com.hoogle.room.controller;

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

import tw.com.hoogle.room.model.RoomService;
import tw.com.hoogle.room.model.RoomVO;
import tw.com.hoogle.roompic.model.RoompicVO;

@WebServlet("/room/RoomServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoomServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		if ("getByHotelId".equals(action)) { // 來自newsList.jsp的請求

			   List<String> errorMsgs = new LinkedList<String>();
			   // Store this set in the request scope, in case we need to
			   // send the ErrorPage view.
			   req.setAttribute("errorMsgs", errorMsgs);

			    /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			    String str1 = req.getParameter("hotelId");
			   
			    if ((str1 == null || (str1.trim()).length() == 0)) {
			     errorMsgs.add("請選擇您要搜尋的期間");
			    }
			    // Send the use back to the form, if there were errors
			    if (!errorMsgs.isEmpty()) {
			     RequestDispatcher failureView = req
			       .getRequestDispatcher("/back_end/news/newsList.jsp");
			     failureView.forward(req, res);
			     return;//程式中斷
			    }
			      
			    Integer hotelId = null;
			    
			    try {
			    	hotelId = Integer.valueOf(str1);
			     
			    } catch (Exception e) {
			     errorMsgs.add("期間格式不正確");
			    }
			    // Send the use back to the form, if there were errors
			    if (!errorMsgs.isEmpty()) {
			     RequestDispatcher failureView = req
			       .getRequestDispatcher("/back_end/news/newsList.jsp");
			     failureView.forward(req, res);
			     return;//程式中斷
			    }
			      
			    /***************************2.開始查詢資料*****************************************/
			    RoomService roomSvc = new RoomService();
			    List<RoomVO> searchRooms = roomSvc.getByHotelId(hotelId);
			    if (searchRooms.isEmpty()) {
//			     errorMsgs.add("此區間無資料");
//			     out.println("<meta http-equiv='refresh' content='1;URL=" + req.getContextPath()
//			     + "/back_end/news/newsList.jsp'>");
//			     out.println("<script> alert('此區間無資料！');</script>");
//			     return;
			    }
			    // Send the use back to the form, if there were errors
//			    if (!errorMsgs.isEmpty()) {
//			     RequestDispatcher failureView = req
//			       .getRequestDispatcher("/back_end/news/newsList.jsp");
//			     failureView.forward(req, res);
//			     return;//程式中斷
//			    }
			      
			    /***************************3.查詢完成,準備轉交(Send the Success view)*************/
			    req.setAttribute("hotelId", hotelId); // 			     
			    System.out.println("getByHotelId轉交");
			    String url = "/back_end/news/searchNews.jsp";
			    RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdmin.jsp
			    successView.forward(req, res);
			  }
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("roomAuto");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("roomAuto", "請輸入編號");
//					errorMsgs.add("請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/select_room_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer roomAuto = null;
			try {
				roomAuto = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("roomAuto", "編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/select_room_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			System.out.println("get one for1 ");
			/*************************** 2.開始查詢資料 *****************************************/

			RoomService roomSvc = new RoomService();
			RoomVO roomVO = roomSvc.getOneRoom(roomAuto);
			if (roomVO == null) {
				errorMsgs.put("roomAuto", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/select_room_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			System.out.println("get one for 2");
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("roomVO", roomVO); // 資料庫取出的roomVO物件,存入req
			String url = "/room/listOneRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFood.jsp
			successView.forward(req, res);
		}

		System.out.println("get one for3 ");
		if ("getOne_For_Update".equals(action)) { // 來自listAllroom.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer roomAuto = Integer.valueOf(req.getParameter("roomAuto"));

			/*************************** 2.開始查詢資料 ****************************************/
			RoomService roomSvc = new RoomService();
			RoomVO roomVO = roomSvc.getOneRoom(roomAuto);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?roomAuto=" + roomVO.getRoomAuto() + "&hotelId=" + roomVO.getHotelId() + "&roomTotal="
					+ roomVO.getRoomTotal() + "&roomName=" + roomVO.getRoomName() + "&roomStatus="
					+ roomVO.getRoomStatus() + "&roomPrice=" + roomVO.getRoomPrice();

			String url = "/room/update_room_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//			
//			
		if ("update".equals(action)) { // 來自update_room_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer roomAuto = null;
			try {
				roomAuto = Integer.valueOf(req.getParameter("roomAuto").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("roomAuto", "請填數字");
			}

			Integer hotelId = null;
			try {
				hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("hotelId", "請填數字");
			}

			Integer roomTotal = null;
			try {
				roomTotal = Integer.valueOf(req.getParameter("roomTotal").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("roomTotal", "請填數字");
			}

			String roomName = req.getParameter("roomName");
			String roomNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (roomName == null || roomName.trim().length() == 0) {
				errorMsgs.put("roomName", "旅客名稱: 請勿空白");
			} else if (!roomName.trim().matches(roomNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("roomName", "旅客名稱: 只能是中、英文字母、數字和_ , 且長度必需在10字以內");
			}

			Boolean roomStatus;
			if (req.getParameter("roomStatus") != null) {
				roomStatus = true;
			} else {
				roomStatus = false;
			}

			Integer roomPrice = null;
			try {
				roomPrice = Integer.valueOf(req.getParameter("roomPrice").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("roomPrice", "請填數字");
			}

			Part part = req.getPart("roompicPic");
			InputStream is = part.getInputStream();
			byte[] roomPic = is.readAllBytes();
			is.close();

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/update_room_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			System.out.println("here111");
//					/***************************2.開始修改資料*****************************************/
			RoomService roomSvc = new RoomService();
			RoomVO roomVO = new RoomVO();
			roomVO.setRoomAuto(roomAuto);
			roomVO.setHotelId(hotelId);
			roomVO.setRoomTotal(roomTotal);
			roomVO.setRoomName(roomName);
			roomVO.setRoomStatus(roomStatus);
			roomVO.setRoomPrice(roomPrice);

			RoompicVO roompicVO = new RoompicVO();
			roompicVO.setRoomAuto(roomAuto);
			roompicVO.setRoompicPic(roomPic);

			roomVO = roomSvc.updateRoom(roomVO, roompicVO);

			System.out.println("here222");
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("roomVO", roomVO); // 資料庫update成功後,正確的的roomVO物件,存入req
			String url = "/room/listAllRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFood.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addFood.jsp的請求
			System.out.println("IN INSERT");
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer hotelId = null;
			try {
				hotelId = Integer.valueOf(req.getParameter("hotelId").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("hotelId", "請填數字");
			}

			Integer roomTotal = null;
			try {
				roomTotal = Integer.valueOf(req.getParameter("roomTotal").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("roomTotal", "請填數字");
			}

			String roomName = req.getParameter("roomName");
			String roomNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (roomName == null || roomName.trim().length() == 0) {
				errorMsgs.put("roomName", "飯店名稱: 請勿空白");
			} else if (!roomName.trim().matches(roomNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("roomName", "飯店名稱: 只能是中、英文字母、數字和_ , 且長度必需在10字以內");
			}

			Boolean roomStatus;
			if (req.getParameter("roomStatus") != null) {
				roomStatus = true;
			} else {
				roomStatus = false;
			}

			Integer roomPrice = null;
			try {
				roomPrice = Integer.valueOf(req.getParameter("roomPrice").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("roomPrice", "請填數字");
			}

			Part part = req.getPart("roompicPic");
			InputStream is = part.getInputStream();
			byte[] roompicPic = is.readAllBytes();
			is.close();

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/newAddRoomFile.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
//				
//				/***************************2.開始新增資料***************************************/
			RoomService roomSvc = new RoomService();

			RoomVO roomVO = new RoomVO();
			roomVO.setHotelId(hotelId);
			roomVO.setRoomTotal(roomTotal);
			roomVO.setRoomName(roomName);
			roomVO.setRoomStatus(roomStatus);
			roomVO.setRoomPrice(roomPrice);

			RoompicVO roompicVO = new RoompicVO();
//			roompicVO.setRoomStatus(roomStatus);
			roompicVO.setRoompicPic(roompicPic);

			roomSvc.addRoom(roomVO, roompicVO);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/room/listAllRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFood.jsp
			successView.forward(req, res);
		}
		
//		if ("disable".equals(action)) {
//
//			Integer roomAuto = Integer.valueOf(req.getParameter("roomAuto"));
//
//			/*************************** 2.修改 ***************************************/
//
//			Boolean roomStatus = false;
//
//			RoomVO roomVO = new RoomVO();
//			roomVO.setRoomStatus(roomStatus);
//			RoomService roomSvc = new RoomService();
//			roomVO = roomSvc.disableRoom(roomStatus);
//
//			/*************************** 3.轉交 ***********/
//			String url = "/listAllRoom.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}
	


		}}
