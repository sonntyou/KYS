package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

			//予約者の人数を取得
			int reservnum=0;
			int reservernum=-1;	//INSERTされた数(reservernum)と予約者数(reservercount)が等しいかどうかを調べるためにreservercountとは違う値で初期化している。
			int reservercount=0;
			int reservid=0;

			String reservsql = getReservSQL(reservcontents);
			PreparedStatement reservstmt =conn.prepareStatement(reservsql);
			//SQL文(INSERT)の実行
			reservnum =reservstmt.executeUpdate();

			if(reservnum==1){
				String reservidsql = getReservidSQL(reservcontents);
				PreparedStatement reservidstmt =conn.prepareStatement(reservidsql);
				//SQL文(INSERT)の実行
				ResultSet rs =reservidstmt.executeQuery();
				while(rs.next()){
					reservid= rs.getInt("reservid");
				}
				String reserversql = getReserverSQL(reservid,reservcontents);
				PreparedStatement reserverstmt =conn.prepareStatement(reserversql);
				//SQL文(INSERT)の実行
				reservernum = reserverstmt.executeUpdate();
				reservercount = reservcontents.getReserveridlist().size();
			}


			if(reservnum == 0){
				//重複があった場合に実行
				return 4;
			}else if(reservnum ==1 && reservercount==reservernum){
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
		String sttime =reservcontents.getSttime();
		String endtime = reservcontents.getEndtime();

		String reservsql ="";
		reservsql += "INSERT INTO kysdb.reservtable (sttime,endtime,resourceid,title)  SELECT '"+sttime+"','"+endtime+"',"+resourceid+",'"+title+"' "
				+ " FROM dual "
				+ " WHERE NOT EXISTS ( SELECT * FROM kysdb.reservtable WHERE "
				+ " resourceid="+resourceid+" "
				+ " and ((sttime<='"+sttime+"' and '"+sttime+"' < endtime) "
				+ " or (sttime<'"+endtime+"'and '"+endtime+"' <= endtime) "
				+ " or (sttime >= '"+sttime+"' and '"+endtime+"'>= endtime))); ";

		return reservsql;
	}

	private String getReservidSQL(ReservContents reservcontents){
		String reservidsql = "";
		int resourceid=reservcontents.getResourceid();
		String sttime =reservcontents.getSttime();

		reservidsql += "SELECT reservid from kysdb.reservtable "
				+ "where resourceid="+resourceid+" and sttime='"+sttime+"';";

		return reservidsql;


	}

	private String getReserverSQL(int reservid,ReservContents reservcontents){
		String reserversql="";
		List<Integer> reserveridlist = reservcontents.getReserveridlist();

		reserversql += "INSERT INTO kysdb.reservertable (reservid,accountid)  VALUES";

		for(int i = 0 ; i < reserveridlist.size();i++){
			 reserversql += "("+reservid+","+reserveridlist.get(i)+")";
			 if(i<reserveridlist.size()-1){
				 reserversql += ",";
			 }

		}
		return reserversql;
	}


}
