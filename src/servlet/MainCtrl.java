package servlet;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarLogic;
import model.ReservContents;
import model.TimeChoices;
import model.Today;

/**
 * Servlet implementation class MainCtrl
 */
@WebServlet("/MainCtrl")
public class MainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	    CalendarLogic calendar = new CalendarLogic();
	       ZonedDateTime now = ZonedDateTime.now();
	        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String todaytime = now.format(DateTimeFormatter.ofPattern("HH:mm"));

			Today today = new Today(date);
			today.setTodayTime(todaytime);

			TimeChoices timechoices = new TimeChoices(today);


	    session.setAttribute("calendar", calendar);
		session.setAttribute("timechoices",timechoices);
		session.setAttribute("today", today);

		List<ReservContents> reservlist= new ArrayList<ReservContents>();
		ReservContents rc1 = new ReservContents();
		rc1.setEndtime("2015-06-19 09:15:00");
		rc1.setSttime("2015-06-19 09:00:00");
		rc1.setTitle("会議");
		rc1.setReservid(1);

		ReservContents rc2 = new ReservContents();
		rc2.setEndtime("2015-06-19 13:00:00");
		rc2.setSttime("2015-06-19 09:15:00");
		rc2.setTitle("会議");
		rc2.setReservid(2);

		ReservContents rc3 = new ReservContents();
		rc3.setEndtime("2015-06-19 17:00:00");
		rc3.setSttime("2015-06-19 16:00:00");
		rc3.setTitle("会議");
		rc3.setReservid(3);

		reservlist.add(rc1);
		reservlist.add(rc2);
		reservlist.add(rc3);

		session.setAttribute("reservlist", reservlist);

		RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
		dispatcher.forward(request,response);

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    String prenext = request.getParameter("prenext");

	    CalendarLogic calendar = new CalendarLogic(year,month,prenext);

	    HttpSession session = request.getSession();

        session.setAttribute("calendar", calendar);

           RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
            dispatcher.forward(request,response);

	}

}
