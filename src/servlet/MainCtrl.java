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
import model.SelectDateTime;
import model.TimeChoices;

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
	    String currentdatetime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		SelectDateTime today = new SelectDateTime(currentdatetime);

		TimeChoices timechoices = new TimeChoices(today);


	    session.setAttribute("calendar", calendar);
		session.setAttribute("timechoices",timechoices);
		session.setAttribute("selectdatetime", today);

		List<ReservContents> reservlist= new ArrayList<ReservContents>();

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