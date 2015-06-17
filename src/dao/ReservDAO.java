package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			System.out.println("ここまでおーけ");

			//ReservContentsに登録された予約情報をゲットしてます。
			String title = reservcontents.getTitle();
			int locateid=reservcontents.getLocateid();
			int accountid = reservcontents.getAccountid();
			String sttime =reservcontents.getSttime();
			String endtime = reservcontents.getEndtime();


			//重複がなければINSERT
			String reservsql ="INSERT INTO kysdb.reservtable (title,locateid,accountid,sttime,endtime)  VALUES ('"+title+"',"+locateid+","+accountid+","+sttime+","+endtime+")"
							+ "not exist locateid="+locateid+" and((sttime<="+sttime+"and "+sttime+"< endtime)or((sttime<"+endtime+"and "+endtime+"<= endtime))or((sttime >="+sttime+"and "+endtime+">= endtime)));";
			PreparedStatement pstmt =conn.prepareStatement(reservsql);

			//SQL文(INSERT)の実行
			int num = pstmt.executeUpdate();

			if(num == 0){
				//重複があった場合に実行
				return 4;
			}else if(num==1){
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
}
