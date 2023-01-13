package tw.com.hoogle.otherhotel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import tw.com.hoogle.hotel.model.HotelService;

import tw.com.hoogle.hotel.model.HotelVO;
import tw.com.hoogle.hotelpic.model.HotelpicVO;

import tw.com.hoogle.otherhotel.model.OtherHotelService;
import tw.com.hoogle.otherhotel.model.OtherHotelVO;

@WebServlet("/otherhotel/OtherHotelServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class OtherHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		// 產生HttpSession物件，讓Servlet可以取得該使用者資訊
		HttpSession session = req.getSession(); // 總共有64512條Session
		PrintWriter out = res.getWriter();

		String url = "/hotel/hotelMemberCenter.jsp";

		String otherhotelservlet = null;
		if (req.getParameter("otherhotelservlet") != null) {
			otherhotelservlet = req.getParameter("otherhotelservlet");
		}
		System.out.println("###### update into OtherHotelServlet  ######. otherhotelservlet is " + otherhotelservlet);

// ===================================================飯店修改=========================================================//
		if ("updateHotel".equals(otherhotelservlet)) { // 來自userMemberCenter的請求

			System.out.println("updateHotel");

			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);

			try {

//				OtherHotelService otherhotelSvc = new OtherHotelService();
//				OtherHotelVO otherhotelVO = (OtherHotelVO) session.getAttribute("hotelVO"); // 表示已登入，取得userVO物件
//				HotelService hotelSvc = new HotelService();
//				HotelVO hotelVO = (HotelVO) session.getAttribute("hotelVO"); // 表示已登入，取得hotelVO物件

				OtherHotelService otherhotelSvc = new OtherHotelService();
				HotelVO hotelVO = (HotelVO) session.getAttribute("hotelVO"); // 表示已登入，取得userVO物件
				System.out.println("SESSION VO=" + hotelVO.toString());

				System.out.println("### update into otherhotel update ### 1");

				// 1.接收請求參數，輸入格式的錯誤處理

				String hotelCounty = req.getParameter("hotelCounty");
				if (hotelCounty == null || hotelCounty.trim().length() == 0) {
					errors.put("hotelCounty", "請選擇區域");
				}
				String hotelAddress = req.getParameter("hotelAddress");
				if (hotelAddress == null || hotelAddress.trim().length() == 0) {
					errors.put("hotelAddress", "請輸入地址");
				}

				String hotelType = req.getParameter("hotelType");
				if (hotelType == null || hotelType.trim().length() == 0) {
					errors.put("hotelCounty", "請選擇類型");
				}

				String hotelNotice = req.getParameter("hotelNotice");
				if (hotelNotice == null || hotelNotice.trim().length() == 0) {
					errors.put("hotelNotice", "請輸入訂房須知");
				}

				String hotelQa = req.getParameter("hotelQa");
				if (hotelQa == null || hotelQa.trim().length() == 0) {
					errors.put("hotelQa", "請輸入QA");
				}

				String hotelIntroduction = req.getParameter("hotelIntroduction");
				if (hotelIntroduction == null || hotelIntroduction.trim().length() == 0) {
					errors.put("hotelIntroduction", "請輸入飯店介紹");
				}
				// 將session 的HotelVO 轉存至自己的OtherHotelVO
//				OtherHotelVO otherhotelVO = new OtherHotelVO();
//				otherhotelVO.setHotelId(hotelvo.getHotelId());
//				otherhotelVO.setHotelEmail(hotelvo.getHotelEmail());

//				hotelvo.getHotelPassword();
//				hotelvo.getHotelName();
//				hotelvo.getHotelPhone();
//				hotelvo.getHotelPrincipal();
//				hotelvo.getHotelTaxid();
//				hotelvo.getHotelCounty();
//				hotelvo.getHotelAddress();
//				hotelvo.getHotelType();
//				hotelvo.getHotelNotice();
//				hotelvo.getHotelQa();
//				hotelvo.getHotelIntroduction();
//				hotelvo.getHotelState();


				hotelVO.setHotelCounty(hotelCounty);
				hotelVO.setHotelAddress(hotelAddress);
				hotelVO.setHotelType(hotelType);
				hotelVO.setHotelNotice(hotelNotice);
				hotelVO.setHotelQa(hotelQa);
				hotelVO.setHotelIntroduction(hotelIntroduction);

				// 會被修改的內容
//				otherhotelVO.setHotelCounty(hotelCounty);
//				otherhotelVO.setHotelAddress(hotelAddress);
//				otherhotelVO.setHotelType(hotelType);
//				otherhotelVO.setHotelNotice(hotelNotice);
//				otherhotelVO.setHotelQa(hotelQa);
//				otherhotelVO.setHotelIntroduction(hotelIntroduction);

//======================================= servicelist table
				String[] checkbox = req.getParameterValues("serviceName");
//				System.out.println("checkbox="+checkbox.length);

				
//				for(int i=0;i<checkbox.length;i++) {
//					System.out.println("checkbox value="+checkbox[i]);
					
//				}
//======================================HOTELPIC table
				Collection<Part> parts=req.getParts();

				  List<HotelpicVO> Hotelpiclist = new ArrayList<HotelpicVO>();
				  for(Part part : parts) {
					  if("the_file".equals(part.getName())) {
						  String filename = part.getSubmittedFileName(); //上傳檔案名稱
						   if(filename != null && filename.length() !=0 && part.getContentType() != null) {
						    String name = part.getName();
						    InputStream in = part.getInputStream();
						    byte[] buf = new byte[in.available()];
						    in.read(buf);
						    in.close();
						    HotelpicVO hotelpicvo = new HotelpicVO();
						    hotelpicvo.setHotelId(hotelVO.getHotelId());
						    hotelpicvo.setHotelpicName(filename);
						    hotelpicvo.setHotelpicNo(buf);
						    Hotelpiclist.add(hotelpicvo);
						   }
					  }
				  }
				
				if (errors != null && !errors.isEmpty()) {
					req.getRequestDispatcher("/hotel/hotelMemberCenter.jsp").forward(req, res);
					return;
				}

				// 開始修改資料


//				hotelVO = hotelSvc.updateHotel(hotelVO);

				hotelVO = otherhotelSvc.updateHotel(hotelVO,checkbox,Hotelpiclist);

				System.out.println("修改成功");
				req.getRequestDispatcher("../hotel/hotelMemberCenter.jsp").forward(req, res);
			} catch (Exception e) {
				System.out.println("update exception :" + e);

		
			

				RequestDispatcher failureView = req.getRequestDispatcher("/hotel/hotelMemberCenter.jsp");

				failureView.forward(req, res);
			}
		}
	}
}