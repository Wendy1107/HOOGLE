package tw.com.hoogle.compare.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.hoogle.compare.model.CompareService;
import tw.com.hoogle.compare.model.CompareVO;

@WebServlet("/compare/CompareServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CompareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println(123);
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		HttpSession session = req.getSession();
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//			System.out.println("getOne_For_Display##1");

//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String hotelName1 = req.getParameter("hotelName1");	//"hotelName1" = jsp的name屬性
			String hotelName2 = req.getParameter("hotelName2");
			String hotelName3 = req.getParameter("hotelName3");
			String serviceName1 = req.getParameter("hotelName1");	//"hotelName1" = jsp的name屬性
			String serviceName2 = req.getParameter("hotelName2");
			String serviceName3 = req.getParameter("hotelName3");
			System.out.println("hotelName1 = " + hotelName1);
			System.out.println("hotelName2 = " + hotelName2);
			System.out.println("hotelName3 = " + hotelName3);
			System.out.println("serviceName1 = " + hotelName1);
			System.out.println("serviceName2 = " + hotelName2);
			System.out.println("serviceName3 = " + hotelName3);
//			

			/*************************** 2.開始查詢資料 *****************************************/
			CompareService compareSvc = new CompareService();
			List<CompareVO> list1 = compareSvc.getOneHotel(hotelName1);
			List<CompareVO> list2 = compareSvc.getOneHotel(hotelName2);
			List<CompareVO> list3 = compareSvc.getOneHotel(hotelName3);
			System.out.println("List<CompareVO> list2"+list2);
			System.out.println("list1="+list1);
			System.out.println("list2="+list2);
			System.out.println("list3="+list3);
			List<CompareVO> listService1 = compareSvc.compareSvcList(serviceName1);
			List<CompareVO> listService2 = compareSvc.compareSvcList(serviceName2);
			List<CompareVO> listService3 = compareSvc.compareSvcList(serviceName3);
			System.out.println("List<CompareVO> listService2"+serviceName2);
			System.out.println("listsSrvice1="+serviceName1);
			System.out.println("listsSrvice2="+serviceName2);
			System.out.println("listsSrvice3="+serviceName3);
//			TreeSet<CompareVO> list1 = compareSvc.getOneHotel(hotelName1);
//			TreeSet<CompareVO> list2 = compareSvc.getOneHotel(hotelName2);
//			TreeSet<CompareVO> list3 = compareSvc.getOneHotel(hotelName3);
//			TreeSet<CompareVO> listService1 = compareSvc.compareSvcList(serviceName1);
//			TreeSet<CompareVO> listService2 = compareSvc.compareSvcList(serviceName2);
//			TreeSet<CompareVO> listService3 = compareSvc.compareSvcList(serviceName3);
//			System.out.println("getOne_For_Display##2");
//			if (hotelName == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/compare/compare.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list1", list1); // 資料庫取出的ordVO物件,存入req
			req.setAttribute("list2", list2); // 資料庫取出的ordVO物件,存入req
			req.setAttribute("list3", list3); // 資料庫取出的ordVO物件,存入req
//			System.out.println("list2"+list2);
//			session.setAttribute("listservice1", listService1);
			req.setAttribute("listservice1", listService1); // 資料庫取出的ordVO物件,存入req
			req.setAttribute("listservice2", listService2); // 資料庫取出的ordVO物件,存入req
			req.setAttribute("listservice3", listService3); // 資料庫取出的ordVO物件,存入req
			System.out.println("listservice3="+listService3);
			String url = "/compare/compare.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrd.jsp
			successView.forward(req, res);
			System.out.println("getOne_For_Display##3");
		}

//		if ("getOneService_For_Display".equals(action)) { // 來自select_page.jsp的請求
//			System.out.println("getOneService_For_Display##1");
//
////			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String serviceName1 = req.getParameter("hotelName1");	//"hotelName1" = jsp的name屬性
//			String serviceName2 = req.getParameter("hotelName2");
//			String serviceName3 = req.getParameter("hotelName3");
//			System.out.println("hotelName1 = " + serviceName1);
//			System.out.println("hotelName2 = " + serviceName2);
//			System.out.println("hotelName3 = " + serviceName3);
////			
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			CompareService compareSvcList = new CompareService();
//			List<CompareVO> listService1 = compareSvcList.compareSvcList(serviceName1);
//			List<CompareVO> listService2 = compareSvcList.compareSvcList(serviceName2);
//			List<CompareVO> listService3 = compareSvcList.compareSvcList(serviceName3);
//			System.out.println("list1="+listService1);
//			System.out.println("list2="+listService2);
//			System.out.println("list3="+listService3);
////			System.out.println("getOne_For_Display##2");
////			if (hotelName == null) {
////				errorMsgs.add("查無資料");
////			}
////			// Send the use back to the form, if there were errors
////			if (!errorMsgs.isEmpty()) {
////				RequestDispatcher failureView = req.getRequestDispatcher("/compare/compare.jsp");
////				failureView.forward(req, res);
////				return;// 程式中斷
////			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			session.setAttribute("listservice1", listService1);
////			req.setAttribute("listservice1", listservice1); // 資料庫取出的ordVO物件,存入req
//			req.setAttribute("listservice2", listService2); // 資料庫取出的ordVO物件,存入req
//			req.setAttribute("listservice3", listService3); // 資料庫取出的ordVO物件,存入req
//			String url = "/compare/compare.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrd.jsp
//			successView.forward(req, res);
////			System.out.println("getOne_For_Display##3");
//		}
		
	}

}
