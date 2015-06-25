package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Judge;
import model.ReservContents;
import model.ReservLogic;
import model.SearchLogic;
import model.SelectDateTime;

/**
 * Servlet implementation class ReservCtrl
 */
@WebServlet("/ReservCtrl")
public class ReservCtrl extends HttpServlet {
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
		HttpSession session = request.getSession();

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

	    String stconvdatetime =styear+stmonth+stday+sthour+stminute+"00";
	    String endconvdatetime =endyear+endmonth+endday+endhour+endminute+"00";

//		//現在時刻の取得
//	    ZonedDateTime now = ZonedDateTime.now();
//	    String currentdatetime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//		SelectDateTime today = new SelectDateTime(currentdatetime);
//		String currentconvdatetime = today.getConvdatetime();
//		long intcurrentconvdatetime = Long.parseLong(currentconvdatetime);
//
//		//過去の時刻を入力されたときにエラーを返す。
//		if(Long.parseLong(stconvdatetime)<=intcurrentconvdatetime || Long.parseLong(endconvdatetime)<=intcurrentconvdatetime){
//	    	Judge judge = new Judge();
//	    	judge.setJudge(16);
//	    	session.setAttribute("judge", judge);
//			RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
//			dispatcher.forward(request,response);
//
//	    }else

	    //終了時間が開始時間以前だった場合、エラーを返す。
	    if(Long.parseLong(stconvdatetime)>=Long.parseLong(endconvdatetime)){
	    	Judge judge = new Judge();
	    	judge.setJudge(7);
	    	session.setAttribute("judge", judge);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
			dispatcher.forward(request,response);
	    }else{

	    	String title = request.getParameter("title");
	    	String stringresourceid = request.getParameter("resourceid");
	    	int resourceid = Integer.parseInt(stringresourceid);

	    	String stdatetime = styear+"-"+stmonth+"-"+stday+" "+sthour+":"+stminute+":00";
	    	String enddatetime = endyear+"-"+endmonth+"-"+endday+" "+endhour+":"+endminute+":00";

	    	//送られてきた情報をReservContentsにセットしていく
	    	ReservContents reservcontents = new ReservContents();


	    	//送られてきたメールアドレスの分だけ取り出したい！

	    	String mails[] = request.getParameterValues("mail");
		    if (mails != null){
		    	for (int i = 0 ; i < mails.length ; i++){
		    		reservcontents.setMaillist(mails[i]);
		    	}
		    }

		    reservcontents.setTitle(title);
		    reservcontents.setResourceid(resourceid);
		    reservcontents.setStdatetime(stdatetime);
		    reservcontents.setEnddatetime(enddatetime);

		    //予約をする
		    ReservLogic reservlogic =new ReservLogic();
		    int judgenum = reservlogic.execute(reservcontents);

		    //エラー関連
		    Judge judge = new Judge();
		    judge.setJudge(judgenum);
		    session.setAttribute("judge", judge);

		    //予約した日付を1日の予約一覧に反映させる
		    SelectDateTime reservdatetime = new SelectDateTime(stdatetime);
		    session.setAttribute("selectdatetime", reservdatetime);
		    SearchLogic searchlogic = new SearchLogic();
		    session.setAttribute("reservlist", searchlogic.execute(styear+"-"+stmonth+"-"+stday));

		    RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
		    dispatcher.forward(request,response);
	    	}
		}

}
