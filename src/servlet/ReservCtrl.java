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
 * Servlet implementation class ReservCtrl
 */
@WebServlet("/ReservCtrl")
public class ReservCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservCtrl() {
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

	    String title = request.getParameter("title");
	    String stringresourceid = request.getParameter("resourceid");
	    int resourceid = Integer.parseInt(stringresourceid);
	    String styear = request.getParameter("styear");
	    String stmonth = request.getParameter("stmonth");
	    String stday = request.getParameter("stday");
	    String sthour = request.getParameter("sthour");
	    String stminute = request.getParameter("stminute");
	    String endyear = request.getParameter("endyear");
	    String endmonth = request.getParameter("endmonth");
	    String endday = request.getParameter("endday");
	    String endhour = request.getParameter("endhour");
	    String endminute = request.getParameter("endminute");
	    
	    //送られてきたメールアドレスの分だけ取り出したい！
	    for()
	    
	    String stdatetime = styear+"-"+stmonth+"-"+stday+" "+sthour+":"+stminute+":00";
	    String enddatetime = endyear+"-"+endmonth+"-"+endday+" "+endhour+":"+endminute+":00";
	    
		SelectDateTime today = new SelectDateTime(stdatetime);
		TimeChoices timechoices = new TimeChoices(today);
		
		

		session.setAttribute("timechoices",timechoices);
		session.setAttribute("selectdatetime", today);

		List<ReservContents> reservlist= new ArrayList<ReservContents>();

		session.setAttribute("reservlist", reservlist);

		RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
		dispatcher.forward(request,response);

	}

}
