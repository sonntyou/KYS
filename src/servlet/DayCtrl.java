package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReservContents;
import model.TimeChoices;
import model.Today;

/**
 * Servlet implementation class DayCtrl
 */
@WebServlet("/DayCtrl")
public class DayCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub




	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String date = year+"-"+month+"-"+day;

		HttpSession session = request.getSession();

		List<ReservContents> reservlist= new ArrayList<ReservContents>();
		ReservContents rc1 = new ReservContents();
		rc1.setSttime("2015-06-23 09:00:00");
        rc1.setEndtime("2015-06-23 10:15:00");
		rc1.setTitle("会議");
		rc1.setReservid(1);
		rc1.setResourceid(1);

		ReservContents rc2 = new ReservContents();
		rc2.setSttime("2015-06-23 09:15:00");
        rc2.setEndtime("2015-06-23 13:00:00");
		rc2.setTitle("会議");
		rc2.setReservid(2);
		rc2.setResourceid(2);

		ReservContents rc3 = new ReservContents();
		rc3.setSttime("2015-06-23 12:00:00");
        rc3.setEndtime("2015-06-23 15:00:00");
		rc3.setTitle("会議");
		rc3.setReservid(3);
        rc3.setResourceid(3);

		ReservContents rc4 = new ReservContents();
		rc4.setSttime("2015-06-23 16:00:00");
        rc4.setEndtime("2015-06-23 17:00:00");
		rc4.setTitle("会議");
		rc4.setReservid(3);
        rc4.setResourceid(3);

		reservlist.add(rc1);
		reservlist.add(rc2);
		reservlist.add(rc3);
		reservlist.add(rc4);

		session.setAttribute("reservlist", reservlist);


		Today today = new Today(date);

		TimeChoices timechoices = new TimeChoices(today);


		session.setAttribute("timechoices",timechoices);
		session.setAttribute("today", today);


		RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
		dispatcher.forward(request,response);
	}

}
