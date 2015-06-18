package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.ReservContents;

public class ReservDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/kysdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "levelfive";

	//データベースと照合し、重複がなければ予約追加します
	public int reserv(ReservContents reservcontents) {
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

			//重複がなければINSERT
			String reservsql =getReservSQL(reservcontents);
			PreparedStatement reservstmt =conn.prepareStatement(reservsql);

			System.out.println(reservsql);

			//SQL文(INSERT)の実行
			int num = reservstmt.executeUpdate();
			System.out.println("ここまでおーけ");


			if(num == 0){
				//重複があった場合に実行
				return 4;
			}else if(num !=0){
				//予約が正常に終了したときに実行
				return 5;
			}

		}catch(SQLException e){
			e.printStackTrace();
			return 9;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 10;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return 11;
				}
			}
		}

		//どこでもキャッチされなかったときに実行(予期せぬ事態です。)
		return 0;
	}//reservメソッドの終わり

	private String getReservSQL(ReservContents reservcontents){
		//ReservContentsに登録された予約情報をゲットしてます。
		String title = reservcontents.getTitle();
		int resourceid=reservcontents.getResourceid();
		List<Integer> reserveridlist = reservcontents.getReserveridlist();
		String sttime =reservcontents.getSttime();
		String endtime = reservcontents.getEndtime();

		String reservsql ="BEGIN; ";
		reservsql += "INSERT INTO kysdb.reservtable (sttime,endtime,locateid,title)  SELECT '"+sttime+"','"+endtime+"',"+resourceid+",'"+title+"' "
				+ " FROM dual "
				+ " WHERE NOT EXISTS ( SELECT * FROM kysdb.reservtable WHERE "
				+ " locateid="+resourceid+" "
				+ " and ((sttime<='"+sttime+"' and '"+sttime+"' < endtime) "
				+ " or (sttime<'"+endtime+"'and '"+endtime+"' <= endtime) "
				+ " or (sttime >= '"+sttime+"' and '"+endtime+"'>= endtime))); ";

		for(int i = 0 ; i < reserveridlist.size();i++){
			reservsql += "INSERT INTO kysdb.reservertable (reservid,accountid)  SELECT (select max(reservid) from kysdb.reservtable),"+reserveridlist.get(i)+" "
					+ " FROM dual "
					+ " WHERE EXISTS ( SELECT * FROM kysdb.reservtable WHERE "
					+ " locateid = "+resourceid+" "
					+ " and ((sttime <= '"+sttime+"' and '"+sttime+"' < endtime)"
					+ " or (sttime < '"+endtime+"'and '"+endtime+"' <= endtime)"
					+ " or (sttime >= '"+sttime+"' and '"+endtime+"' >= endtime))); ";

		}
		reservsql += "COMMIT;";

		return reservsql;
	}


}
