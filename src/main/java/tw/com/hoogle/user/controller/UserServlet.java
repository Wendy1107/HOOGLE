package tw.com.hoogle.user.controller;

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
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import tw.com.hoogle.hotel.model.HotelVO;
import tw.com.hoogle.mail.model.MailService;
import tw.com.hoogle.user.model.UserService;
import tw.com.hoogle.user.model.UserVO;

@WebServlet("/user/UserServlet")

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		// 產生HttpSession物件，讓Servlet可以取得該使用者資訊
		HttpSession session = req.getSession(); // 總共有64512條Session
		PrintWriter out = res.getWriter();
		String action = null;

		if (req.getParameter("action") != null) {
			action = req.getParameter("action");
		}
		System.out.println("######  into UserServlet  ######. action is " + action);


// ===================================================旅客註冊=========================================================//
		if ("insert".equals(action)) {

			System.out.println("### into insert user ###");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);


			try {

				UserService userSvc = new UserService();
				UserVO userVO = new UserVO();

				// 1.接收請求參數，輸入格式的錯誤處理
				String userEmail = req.getParameter("userEmail");
				String userEmailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (userEmail == null || userEmail.trim().length() == 0) {
					errorMsgs.add("請填寫信箱");
				} else if (!userEmail.trim().matches(userEmailReg)) {
					errorMsgs.add("請輸入正確信箱格式");
				} else if (userSvc.getUserEmails(userEmail).size() > 0) {
					errorMsgs.add("已有此帳號，請直接登入");
				}

				String userPassword = req.getParameter("userPassword");
				String comfirmPassword = req.getParameter("comfirmpassword");
				if (userPassword == null || userPassword.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!userPassword.equals(comfirmPassword)) {
					errorMsgs.add("兩次密碼需一致");
				}

				String userName = req.getParameter("userName");
				String userNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (userName == null || userName.trim().length() == 0) {
					errorMsgs.add("請填寫姓名");
				} else if (!userName.trim().matches(userNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String userPhone = req.getParameter("userPhone");
				String userPhoneReg = "^[0-9]{10}$";
				if (userPhone == null || userPhone.trim().length() == 0) {
					errorMsgs.add("請輸入電話號碼");
				} else if (!userPhone.trim().matches(userPhoneReg)) {
					errorMsgs.add("電話號碼: 只能是數字 , 且長度必需是10");
				}

				String userIdentity = req.getParameter("userIdentity");
				String userIdentityReg = "^[A-Z][12]\\d{8}$";
				if (userIdentity == null || userIdentity.trim().length() == 0) {
					errorMsgs.add("請輸入身分證");
				} else if (!userIdentity.trim().matches(userIdentityReg)) {
					errorMsgs.add("請符合身分證格式");
				}

				java.sql.Date userBirthday = null;
				try {
					userBirthday = java.sql.Date.valueOf(req.getParameter("userBirthday").trim());
				} catch (IllegalArgumentException e) {
					userBirthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入西元日期");
				}

				// 獲得時間戳記
//				Timestamp userRegistration = new Timestamp(System.currentTimeMillis());// 獲取系統當前時間
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String timeStr = df.format(userRegistration);
//				userRegistration = Timestamp.valueOf(timeStr);

				userVO.setUserEmail(userEmail);
				userVO.setUserPassword(userSvc.pwdhash(userPassword));
				userVO.setUserName(userName);
				userVO.setUserPhone(userPhone);
				userVO.setUserIdentity(userIdentity);
				userVO.setUserBirthday(userBirthday);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("userVO", userVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/user/registerForUser.jsp");
					failureView.forward(req, res);
					return;
				}

				// 開始新增資料

				userVO = userSvc.addUser(userVO);
				req.setAttribute("userVO", userVO);

				// 新增完成，準備轉交

				out.println("<meta http-equiv='refresh' content='1;URL=" + req.getContextPath()
						+ "/user/loginForUser.jsp'>");
				out.println("<script> alert('註冊成功!');</script>");

			} catch (Exception e) {
				session.setAttribute("userVO", "");
				req.getRequestDispatcher("/index.jsp").forward(req, res);			
			}

		}

// ===================================================旅客登入=========================================================//

		if ("loginForUser".equals(action)) {
			System.out.println("### into loginForUser ###");

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				UserVO userVO = new UserVO();
				UserService userSvc = new UserService();

				// 確認旅客輸入的值
				String userEmail = req.getParameter("userEmail");
				if (userEmail == null || userEmail.trim().length() == 0) {
					errorMsgs.add("請輸入信箱");
				}
				System.out.println("使用者輸入的信箱: " + userEmail); // 使用者輸入的信箱

				String userPassword = req.getParameter("userPassword");
				if (userPassword == null || userPassword.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				System.out.println("使用者輸入的密碼: " + userPassword);

				// 設定UserService傳入資訊

				userVO = userSvc.findByUserEmail(userEmail);
				String userPwd = userSvc.pwdhash(userPassword);
				
				if(userVO == null) {
					errorMsgs.add("信箱或密碼有誤");
				}
				
				String userEmailCheck = userVO.getUserEmail();
				System.out.println(userEmailCheck);
				if (!userEmailCheck.equals(userEmail)) {
					errorMsgs.add("非註冊信箱");
				}

				String userPasswordCheck = userVO.getUserPassword();
				if (!userPasswordCheck.equals(userPwd)) {
					errorMsgs.add("信箱或密碼錯誤");
				}

				// 確認資料有誤，印出錯誤資料並跳回原頁
				if (!errorMsgs.isEmpty()) {
//					session.setAttribute("userVO", userVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/user/loginForUser.jsp");
					failureView.forward(req, res);
					return;
				}

				// 確認資料無誤，則設定
				session.setAttribute("userVO", userVO);
				System.out.println("be login...");
				String location = (String) session.getAttribute("location"); // 看看有無來源網頁

				if (location != null) { // 代表有來源網頁
					session.removeAttribute("location"); // 有來源網頁:重導至來源網頁
					res.sendRedirect(location);
					return;
				}
				RequestDispatcher successView = req.getRequestDispatcher("/user/userMemberCenter.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/user/loginForUser.jsp");
				failureView.forward(req, res);
			}

		}
		
// ===================================================旅客、飯店登出=========================================================//		

		if("logout".equals(action)) {
			try {
				System.out.println("into logout");
				session.removeAttribute("userVO");
				session.removeAttribute("hotelVO");
				
				HashMap<String, String> userInfoMap = new HashMap<String, String>();
				userInfoMap.put("check", "2"); // 表登出，回到首頁，右上方顯示「註冊」及「登入

				session.invalidate(); // 清除用戶端與伺服器之間的會話資料
				
				JSONObject obj = new JSONObject(userInfoMap);
//				PrintWriter out = res.getWriter(); 已經宣告在doPost執行時
				out.println(obj);
				System.out.println(obj + "logout");
				return;
				
			}catch(Exception e) {
				e.getStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("index.html");
				failureView.forward(req, res);
			}
			
		}
		
		
		
// ===================================================旅客修改資料=========================================================//
		
		if ("update".equals(action)) { // 來自userMemberCenter的請求

			System.out.println("update");

			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);

			try {

				UserService userSvc = new UserService();
				UserVO userVO = (UserVO) session.getAttribute("userVO"); // 表示已登入，取得userVO物件
				System.out.println("### into update ### 1");

				// 1.接收請求參數，輸入格式的錯誤處理
			
				String userName = req.getParameter("userName");
				String userNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (userName == null || userName.trim().length() == 0) {
					errors.put("userName", "請填寫姓名");
				} else if (!userName.trim().matches(userNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errors.put("userName", "姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String userPhone = req.getParameter("userPhone");
				String userPhoneReg = "^[0-9]{10}$";
				if (userPhone == null || userPhone.trim().length() == 0) {
					errors.put("userPhone", "請輸入電話號碼");
				} else if (!userPhone.trim().matches(userPhoneReg)) {
					errors.put("userPhone", "電話號碼: 只能是數字 , 且長度必需是10");
				}

				String userIdentity = req.getParameter("userIdentity");
				String userIdentityReg = "^[A-Z][12]\\d{8}$";
				if (userIdentity == null || userIdentity.trim().length() == 0) {
					errors.put("userIdentity" ,"請輸入身分證");
				} else if (!userIdentity.trim().matches(userIdentityReg)) {
					errors.put("userIdentity" ,"請符合身分證格式");
				}

				java.sql.Date userBirthday = null;
				try {
					userBirthday = java.sql.Date.valueOf(req.getParameter("userBirthday").trim());
				} catch (IllegalArgumentException e) {
					userBirthday = new java.sql.Date(System.currentTimeMillis());
					errors.put("userBirthday" ,"請輸入西元日期");
				}
				
				
				if (errors != null && !errors.isEmpty()) {
					session.setAttribute("userVO", userVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/user/userMemberCenter.jsp");
					failureView.forward(req, res);
					return;
				}

		
				userVO.setUserName(userName);
				userVO.setUserPhone(userPhone);
				userVO.setUserIdentity(userIdentity);
				userVO.setUserBirthday(userBirthday);
		
				// 開始修改資料

				userVO = userSvc.updateUser(userVO);
				System.out.println("修改成功");

				out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
				+ "/user/userMemberCenter.jsp'>");
				out.println("<script> alert('修改資料完成!');</script>");

			} catch (Exception e) {
				System.out.println("update exception :" + e);
				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}
		
// ===================================================旅客修改密碼=========================================================//
		
		if("updateUserPassword".equals(action)) {
			
			System.out.println("updateUserPassword");

			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);
			
			try {

				UserService userSvc = new UserService();
				UserVO userVO = (UserVO) session.getAttribute("userVO"); // 表示已登入，取得userVO物件
				System.out.println("### into updateUserPassword ### 1");

				
				String oldUserPassword = req.getParameter("oldUserPassword");
				String newUserPassword = req.getParameter("newUserPassword");
				String oldPwd = userSvc.pwdhash(oldUserPassword);
				if(oldUserPassword == null || oldUserPassword.trim().length() == 0) {
					errors.put("oldUserPassword", "請輸入舊密碼");
				} else if(!oldPwd.equals(userVO.getUserPassword())) {
					errors.put("oldUserPassword", "舊密碼錯誤");			
				} 
				System.out.println(oldUserPassword);
				System.out.println(newUserPassword);
					
						
				
				if (errors != null && !errors.isEmpty()) {
					session.setAttribute("userVO", userVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/user/userMemberCenter.jsp");
					failureView.forward(req, res);
					return;
				}

				// 開始修改資料
				userVO.setUserPassword(userSvc.pwdhash(newUserPassword));
				userVO = userSvc.updateUser(userVO);
				System.out.println("旅客密碼修改成功");
						
				out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
				+ "/user/userMemberCenter.jsp'>");
				out.println("<script> alert('修改密碼成功!');</script>");

			} catch (Exception e) {
				System.out.println("update exception :" + e);
				RequestDispatcher failureView = req.getRequestDispatcher("index.jsp");
				failureView.forward(req, res);
			}
			
		}
		
// ===================================================忘記密碼=========================================================//
		if ("forgotPassword".equals(action)) {
			System.out.println("### into forgotPassword ###");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				UserVO userVO = new UserVO();
				UserService userSvc = new UserService();

				String userEmail = req.getParameter("userEmail");
				System.out.println("使用者輸入的userEmail：" + userEmail);
				String userEmailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (userEmail == null || userEmail.trim().length() == 0) {
					errorMsgs.add("請填寫信箱");
				} else if (!userEmail.trim().matches(userEmailReg)) {
					errorMsgs.add("請輸入正確信箱格式");
				} else if (userSvc.getUserEmails(userEmail).size() <= 0) {
					errorMsgs.add("無此信箱，請輸入註冊時信箱");
				}
				System.out.println("信箱通過驗證：" + userEmail);

				userVO = userSvc.findByUserEmail(userEmail);

				// 確認資料有誤，印出錯誤資料並跳回原頁
				if (!errorMsgs.isEmpty()) {
//					session.setAttribute("userVO", userVO);
					req.setAttribute("userVO", userVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/user/forgotPassword.jsp"); // 錯誤返回原頁面
					failureView.forward(req, res);
					return;
				}

				// 確認資料無誤，則設定
				MailService mailService = new MailService();
				String newPassword = mailService.genAuthCode();
				String subject = "忘記密碼重新設定";
				String messageText = "Hello!" + userVO.getUserName() + "您的新密碼 ：「 " + newPassword + "  」";
				mailService.sendMail(userEmail, subject, messageText);

				userVO.setUserPassword(userSvc.pwdhash(newPassword));
				System.out.println(userSvc.pwdhash(newPassword));
				userVO = userSvc.updateUser(userVO);
				System.out.println("forgotPasswordSuccess");

				// 設定成功，轉交回登入畫面
				out.println("<meta http-equiv='refresh' content='0;URL=" + req.getContextPath()
				+ "/user/loginForUser.jsp'>");
				out.println("<script> alert('請至信箱提取新密碼');</script>");				

			} catch (Exception e) {
				session.setAttribute("userVO", "");
//				errorMsgs.add("忘記密碼錯誤" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/user/forgotPassword.jsp");
				failureView.forward(req, res);
			}

		}
// ===================================================確認狀態=========================================================//		

		if ("checkLogin".equals(action)) {
			try {
				UserVO userVO = (UserVO) session.getAttribute("userVO");
				HotelVO hotelVO = (HotelVO) session.getAttribute("hotelVO");
				
				HashMap<String, String> userInfoMap = new HashMap<String, String>();
				userInfoMap.put("check", "2");
				
				if(userVO != null) {
					userInfoMap.put("check", "1");
					userInfoMap.put("email", userVO.getUserEmail());
					userInfoMap.put("url", "/HOOGLE/user/userMemberCenter.jsp");
				} else if(hotelVO != null) {
					userInfoMap.put("check", "1");
					userInfoMap.put("email", hotelVO.getHotelEmail());
					userInfoMap.put("url", "/HOOGLE/hotel/hotelMemberCenter.jsp");
				}
				
				JSONObject obj = new JSONObject(userInfoMap);
				out.println(obj);
				System.out.println(obj);
				
			}catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("index.html");
				failureView.forward(req, res);
			}
		}					
		out.flush();
		out.close();
	}

}
