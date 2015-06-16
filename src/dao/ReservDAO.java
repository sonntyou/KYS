package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.ReservContents;

public class ReservDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:postgresql://localhost:3306/kysdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "1714";

	//データベースと照合し、重複がなければ予約追加します
	public int reserv(ReservContents reservcontents) {
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

			//ReservContentsに登録された予約情報をゲットしてます。
			String title = reservcontents.getTitle();
			int locateid=reservcontents.getLocateid();
			int accountid = reservcontents.getAccountid();
			String sttime =reservcontents.getSttime();
			String stdate = reservcontents.getStdate();
			String endtime = reservcontents.getEndtime();
			String enddate = reservcontents.getEnddate();

			//送られてきたlocateとdateに一致するデータを持ってくる
			String sql ="INSERT INTO kysdb.reservtable (title,locateid,accountid,sttime,endtime)  VALUES ('"+title+"',"+locateid+","+accountid+","+sttime+","+endtime+")"
							+ "not exist locateid="+locateid+" and((sttime<=and<endtime)or()or());";
			PreparedStatement pstmt =conn.prepareStatement(sql);

			//SQL文(SELECT)の実行
			ResultSet rs =pstmt.executeQuery();

			int count=0;
			while(rs.next()){

				//データベース上のdbsttimeとtime_endをHH:mmの形で取ってきてそれぞれdbsttimeとtime_endに代入
				String dbsttime = new SimpleDateFormat("HH:mm").format(rs.getTimestamp("dbsttime"));
				String dbendtime = new SimpleDateFormat("HH:mm").format(rs.getTimestamp("time_end"));

				//3文字目の:を削除
				String convdbsttime=dbsttime.substring(0,2)+dbsttime.substring(dbsttime.length()-2);
				String convtime_end=time_end.substring(0,2)+time_end.substring(time_end.length()-2);
				String convstime=stime.substring(0,2)+stime.substring(stime.length()-2);
				String convftime=ftime.substring(0,2)+ftime.substring(ftime.length()-2);

				//String型をint型に変換
				int intdbsttime= Integer.parseInt(convdbsttime);
				int intstime= Integer.parseInt(convstime);
				int intftime= Integer.parseInt(convftime);
				int inttime_end= Integer.parseInt(convtime_end);

				//時間がかぶっていたらカウントし、break
				if((intstime >= intdbsttime && intstime < inttime_end)||(intftime > intdbsttime && intftime <= inttime_end)||(intstime <= intdbsttime && intftime >= inttime_end)) {
					count++;
					break;
				}

			}//while文の最後

				//カウンターが0だったら、つまり、1つでも予定がかぶっていたらfalseを返す
				if(count == 1) {
					return 3;
				}else{
					//１つも予定がかぶっていなかったら、dateとstimeを足してpostgreSQLのtimestamp型に形成し、挿入
					String timestampstime = date + " " + stime + ":00";
					String timestampftime = date + " " + ftime + ":00";

					String insertsql ="insert into rsv_timetable (locate, name, title, dbsttime, time_end, kwd, mst_kwd) values"
							+ "("+locate+",'"+reserver+"','"+ title+"','"+ timestampstime+"','"+ timestampftime+"','"+ password+"','"+ mpassword+"');";

					PreparedStatement stmt = conn.prepareStatement(insertsql);

					//SQL文(INSERT)の実行
					int num = stmt.executeUpdate();

					//INSERTが期待通り(1件だけ挿入すること)の結果でなければfalseを返す
					if(num != 1){
						return 7;
					}

					//登録が正常に済めばtrueを返す
					return 5;
				}

			}catch(SQLException e){
			e.printStackTrace();
			return 8;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 9;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return 10;
				}
			}
		}
	}//reservメソッドの終わり
}
