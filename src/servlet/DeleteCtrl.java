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

import model.DeleteLogic;
import model.Judge;
import model.ReservContents;
import model.SearchLogic;

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

		String Stringreservid = request.getParameter("reservid");
		int reservid = Integer.parseInt(Stringreservid);
		String mail = request.getParameter("mail");
		String selectdate = request.getParameter("selectdate");

		//削除します
		DeleteLogic deletelogic = new DeleteLogic();
		int judgenum =deletelogic.execute(reservid, mail);

		//エラー関連
		Judge judge = new Judge();
		judge.setJudge(judgenum);
		session.setAttribute("judge", judge);

		//削除した結果を受けて、削除した予定の日付で再検索をかけます。
		SearchLogic searchlogic = new SearchLogic();
		List<ReservContents> reservlist =searchlogic.execute(selectdate);
		if(reservlist != null){
			session.setAttribute("reservlist", reservlist);
		}else{
			judge.setJudge(15);
			session.setAttribute("judge", judge);
			reservlist = new ArrayList<ReservContents>();
			session.setAttribute("reservlist", reservlist);
		}

		RequestDispatcher dispatcher=request.getRequestDispatcher("/Top.jsp");
		dispatcher.forward(request,response);

	}

}
;
