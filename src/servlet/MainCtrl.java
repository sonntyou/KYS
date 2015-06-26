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
import model.Judge;
import model.ReservContents;
import model.SearchLogic;
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

		//現在時刻の取得
	    ZonedDateTime now = ZonedDateTime.now();
	    String currentdatetime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	    //現在時刻を渡したSelectDateTimeの生成と保存(一日の予定一覧の日付の表示に使用する)
		SelectDateTime today = new SelectDateTime(currentdatetime);
		session.setAttribute("selectdatetime", today);

		Judge judge = new Judge();

		//アクセスした日の予約リストの生成と保存
		SearchLogic searchlogic = new SearchLogic();
		List<ReservContents> reservlist = searchlogic.execute();
		if(reservlist != null){
			session.setAttribute("reservlist", reservlist);
		}else{
			judge.setJudge(15);
			session.setAttribute("judge", judge);
			reservlist = new ArrayList<ReservContents>();
			session.setAttribute("reservlist", reservlist);
		}

		//カレンダーの生成と保存
	    CalendarLogic calendar = new CalendarLogic();
	    session.setAttribute("calendar", calendar);

	    //アクセスしたときの日付を反映させたプルダウンメニューの生成と保存
		TimeChoices timechoices = new TimeChoices(today);
		session.setAttribute("timechoices",timechoices);

		//エラーに関すること
		judge.setJudge(0);
		session.setAttribute("judge", judge);


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