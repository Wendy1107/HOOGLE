package tw.com.hoogle.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hoogle.hotel.model.HotelService;
import tw.com.hoogle.hotel.model.HotelVO;
import tw.com.hoogle.mail.model.MailService;

@WebServlet("/HotelController")
public class HotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = null;

		if (req.getParameter("action") != null) {
			action = req.getParameter("action");
		}
		System.out.println(action);

// ===================================================單一查詢=========================================================//

		if ("getOne_For_Hotel".equals(action)) { // select_page.jsp請求
			System.out.println("getOne_For_Hotel");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 1.接收請求參數，輸入格式的錯誤處理
//			Integer hotelId = Integer.valueOf(req.getParameter("hotelId"));
			String hotelEmail = req.getParameter("hotelEmail");

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
				return;
			}

			// 2.查詢資料

			HotelService hotelSvc = new HotelService();
			HotelVO hotelVO = hotelSvc.findByHotelEmail(hotelEmail);
			if (hotelEmail == null) {
				errorMsgs.add("無此筆資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
				return;
			}

			// 3.查詢完成，準備轉交

			req.setAttribute("hotelVO", hotelVO); // 資料庫取出hotelVO物件，存入req
			String url = "/back_end/hotelAndUser/hotelDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交給listOneHotel.jsp
			successView.forward(req, res);
		}

// ===================================================飯店停權、審核通過=========================================================//

		// 先取得單一飯店資訊 hotelVO物件
		if ("getOne_For_Update".equals(action)) { // 來自listAllHotel.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			/*************************** 1.接收請求參數 ****************************************/
//			Integer hotelId = Integer.valueOf(req.getParameter("hotelId"));
			String hotelEmail = req.getParameter("hotelEmail");
			

			/*************************** 2.開始查詢資料 ****************************************/
			HotelService hotelSvc = new HotelService();
			HotelVO hotelVO = hotelSvc.findByHotelEmail(hotelEmail);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("hotelVO", hotelVO);
			System.out.println("getOne_For_Update");
			
			if(hotelVO.getHotelState()==1) {  // 停權：狀態由1變成0
				hotelVO.setHotelState(0);
				hotelVO = hotelSvc.updateHotel(hotelVO);
				System.out.println("狀態由1變成0");
				
				MailService mailService = new MailService();
				String subject = "通知！您於HOOGLE帳號已遭停權！";
				String messageText = "親愛的" + hotelVO.getHotelName() + "，您於HOOGLE帳號已遭停權，詳情請撥打：(02)1111-5555";
				mailService.sendMail(hotelEmail, subject, messageText);

				// 發送成功，導回原頁面
				out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
				+ "/back_end/hotelAndUser/hotelList.jsp'>");
				out.println("<script> alert('已寄發停權通知');</script>");
				
//				String url = "/back_end/hotelAndUser/hotelList.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
			} else if(hotelVO.getHotelState()==2) {  // 飯店審核通過：狀態由2變成1
				hotelVO.setHotelState(1);
				hotelVO = hotelSvc.updateHotel(hotelVO);
				System.out.println("狀態由2變成1");
				
				// Email通知飯店審核通過
				MailService mailService = new MailService();
				String subject = "您註冊的飯店審核結果：核准";
				String messageText = "親愛的" + hotelVO.getHotelName() + "，您的HOOGLE註冊已審核通過！";
				mailService.sendMail(hotelEmail, subject, messageText);

				// 發送成功，導回原頁面
				out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
				+ "/back_end/approval/approveRegisterHotel.jsp'>");
				out.println("<script> alert('已寄發審核結果');</script>");
				
//				String url = "/back_end/approval/approveRegisterHotel.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
			}			
		}
		
// ===================================================飯店審核資料不符=========================================================//

		// 先取得單一飯店資訊 hotelVO物件
		if ("hotel_Register_Fail".equals(action)) { // 來自listAllHotel.jsp的請求
			System.out.println("==hotel_Register_Fail==");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
					

			/*************************** 1.接收請求參數 ****************************************/
//			Integer hotelId = Integer.valueOf(req.getParameter("hotelId"));
			String hotelEmail = req.getParameter("hotelEmail");
					

			/*************************** 2.開始查詢資料 ****************************************/
			HotelService hotelSvc = new HotelService();
			HotelVO hotelVO = hotelSvc.findByHotelEmail(hotelEmail);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("hotelVO", hotelVO);
			System.out.println("getOne_For_Update");
						
				// Email通知飯店審核通過
			MailService mailService = new MailService();
			String subject = "您註冊的飯店審核結果：資料不符合";
			String messageText = "親愛的" + hotelVO.getHotelName() + "，您於HOOGLE註冊的資料不符合！詳情請撥打：(02)1111-5555";
			mailService.sendMail(hotelEmail, subject, messageText);

				// 發送成功，導回原頁面
			out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
			+ "/back_end/approval/approveRegisterHotel.jsp'>");
			out.println("<script> alert('已寄發審核結果');</script>");
							
		}
		
		// 修改資料頁面
		if ("hotelupdate".equals(action)) {

			System.out.println("hotelupdate");

			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);

			try {

				HotelService hotelSvc = new HotelService();
				HotelVO hotelVO = new HotelVO();
				
				System.out.println("### into hotel update ### 1");

				// 1.接收請求參數，輸入格式的錯誤處理
				
				Integer hotelState = Integer.valueOf(req.getParameter("hotelState"));

				hotelVO.setHotelState(hotelState);

				if (errors != null && !errors.isEmpty()) {
					req.getRequestDispatcher("修改資料頁面").forward(req, res);
					return;
				}

				// 開始修改資料(只針對HotelState做修改)
				hotelVO = hotelSvc.updateHotel(hotelVO);
				System.out.println("修改成功");

				// 修改完成，準備轉交
				out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
						+ "/hotel/hotelMemberCenter.jsp'>"); //這邊的網址一樣換成資料修改頁面的網址
				out.println("<script> alert('修改飯店狀態完成!');</script>");

			} catch (Exception e) {
				System.out.println("update exception :" + e);
				RequestDispatcher failureView = req.getRequestDispatcher("index.jsp"); //錯誤可以跳回後台首頁或飯店審核首頁
				failureView.forward(req, res);
			}
		}

	}

}
