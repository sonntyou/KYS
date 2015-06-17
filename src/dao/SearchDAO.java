package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ReservContents;

public class SearchDAO {
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:postgresql://localhost:3306/kysdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "1714";

	//データベースに日付で検索をかけて、予約ごとにインスタンスを生成し、リストに格納し、そのリストを返す。
	public  List<ReservContents> search(String tabledate) {
		Connection conn =null;

		//ReservContentsを格納し、一日の予約を格納するリストを作成
		List<ReservContents> reservList = new ArrayList<ReservContents>();

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

			//SELECT文の準備
			String searchsql =
			"SELECT reservid,title,locateid,accountid,sttime,endtime FROM kysdb.reservtable WHERE DATEDIFF(sttime,"+tabledate+")=0 or "
					+ "DATEDIFF(endtime,"+tabledate+")=0 or (DATEDIFF(sttime,"+tabledate+")>=1 and DATEDIFF("+tabledate+",endtime,)>=1);";
			PreparedStatement pstmt =conn.prepareStatement(searchsql);

			ResultSet rs = pstmt.executeQuery();

			//SELECTされたオブジェクトを1行ごとにReservContents型のyoyakuに格納し、Listに追加していく
			while(rs.next()){
				int reservid=rs.getInt("reservid");
				String title =rs.getString("title");
				int locateid=rs.getInt("locateid");
				int accountid=rs.getInt("accountid");
				String sttime = rs.getString("sttime");
				String endtime = rs.getString("endtime");

//				//年月日だけ抽出して入れる
//				String stdate =new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("sttime"));
//				String enddate =new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("endtime"));
//				//時分だけ抽出して入れる
//				String st = new SimpleDateFormat("HH:mm").format(rs.getTimestamp("sttime"));
//				String end = new SimpleDateFormat("HH:mm").format(rs.getTimestamp("endtime"));;

				ReservContents reserv = new ReservContents();
				reserv.setReservid(reservid);
				reserv.setTitle(title);
				reserv.setLocateid(locateid);
				reserv.setAccountid(accountid);
				reserv.setSttime(sttime);
				reserv.setEndtime(endtime);

				reservList.add(reserv);
			}

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
			return reservList;
	}
}
