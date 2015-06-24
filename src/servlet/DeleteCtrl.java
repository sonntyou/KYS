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
 * Servlet implementation class DeleteCtrl
 */
@WebServlet("/DeleteCtrl")
public class DeleteCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		HttpSession session = request.getSession();

		String reservid = request.getParameter("reservid");
		String mail = request.getParameter("mail");
		
		SelectDateTime selectdatetime = (SelectDateTime)session.getAttribute("")
		List<ReservContents> reservlist= new ArrayList<ReservContents>();


		ReservContents rc1 = new ReservContents();
		rc1.setSttime("2015-06-22 09:00:00");
        rc1.setEndtime("2015-06-22 10:00:00");
		rc1.setTitle("無題");
		rc1.setReservid(3);
        rc1.setResourceid(3);

		ReservContents rc2 = new ReservContents();
		rc2.setSttime("2015-06-22 09:15:00");
        rc2.setEndtime("2015-06-22 13:00:00");
		rc2.setTitle("会議");
		rc2.setReservid(2);
		rc2.setResourceid(2);

		ReservContents rc3 = new ReservContents();
		rc3.setSttime("2015-06-22 16:00:00");
        rc3.setEndtime("2015-06-22 17:00:00");
		rc3.setTitle("会議");
		rc3.setReservid(3);
        rc3.setResourceid(3);


		reservlist.add(rc1);
		reservlist.add(rc2);
		reservlist.add(rc3);


		session.setAttribute("reservlist", reservlist);

		RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
		dispatcher.forward(request,response);

	}

}
