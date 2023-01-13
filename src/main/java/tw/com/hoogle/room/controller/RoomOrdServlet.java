package tw.com.hoogle.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hoogle.hotel.model.HotelVO;
import tw.com.hoogle.room.model.RoomService;
import tw.com.hoogle.room.model.RoomSummaryVo;

@WebServlet("/room/ord")
public class RoomOrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ordS = request.getParameter("ordDateStart");
		String ordE = request.getParameter("ordDateEnd");
		String roomNa = request.getParameter("roomName");

		HotelVO hotelVO = (HotelVO) request.getSession().getAttribute("hotelVO");
		Integer hotelId = hotelVO.getHotelId();
//		Integer hotelId = 3002;
		
		RoomService service = new RoomService();
		List<RoomSummaryVo> list = service.findForRoomSummary(hotelId, ordS, ordE, roomNa);
		
		request.setAttribute("list", list);		
		request.getRequestDispatcher("select_room_page.jsp").forward(request, response);
	}
}
