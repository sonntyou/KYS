package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SearchLogic;
import model.SelectDateTime;
import model.TimeChoices;

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
		SearchLogic searchlogic = new SearchLogic();
		session.setAttribute("reservlist", searchlogic.execute(date));


		SelectDateTime selectdatetime = new SelectDateTime(date,"09","00","00");
		session.setAttribute("selectdatetime", selectdatetime);

		TimeChoices timechoices = new TimeChoices(selectdatetime);
		session.setAttribute("timechoices",timechoices);


		RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
		dispatcher.forward(request,response);
	}

}
