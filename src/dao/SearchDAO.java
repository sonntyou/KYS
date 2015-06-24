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
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/kysdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "levelfive";

	//データベースに日付で検索をかけて、予約ごとにインスタンスを生成し、リストに格納し、そのリストを返す。
	// String selectdate = yyyy-MM-dd
	public  List<ReservContents> search(String selectdate) {
		Connection conn =null;

		//ReservContentsを格納し、一日の予約を格納するリストを作成
		List<ReservContents> reservList = new ArrayList<ReservContents>();

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

			List<Integer> reservidlist = new ArrayList<Integer>();

			//SELECT文の準備
			//
			String searchreservsql = getSearchReservSQL(selectdate);
			PreparedStatement srstmt =conn.prepareStatement(searchreservsql);
			ResultSet srrs = srstmt.executeQuery();

			//SELECTされたオブジェクトを1行ごとにReservContents型のreservに格納し、Listに追加していく
			while(srrs.next()){
				int reservid=srrs.getInt("reservid");
				reservidlist.add(reservid);
				String title =srrs.getString("title");
				int resourceid=srrs.getInt("resourceid");
				String resource = srrs.getString("resource");
				String stdatetime = srrs.getString("sttime");
				String enddatetime = srrs.getString("endtime");

				ReservContents reserv = new ReservContents();
				reserv.setReservid(reservid);
				reserv.setTitle(title);
				reserv.setResourceid(resourceid);
				reserv.setResource(resource);
				reserv.setStdatetime(stdatetime);
				reserv.setEnddatetime(enddatetime);

				reservList.add(reserv);
			}

			if(0 < reservList.size()){
				String searchaccountsql = getSearchAccountSQL(reservidlist);
				PreparedStatement sastmt =conn.prepareStatement(searchaccountsql);
				ResultSet sars = sastmt.executeQuery();
				//SELECTされたオブジェクトを1行ごとにReservContents型のに格納し、Listに追加していく
				for(int i= 0;sars.next();){
					if(reservList.get(i).getReservid()==(sars.getInt("reservid"))){
					reservList.get(i).setReserveridlist(sars.getInt("accountid"));
					reservList.get(i).setReserverlist(sars.getString("name"));
					reservList.get(i).setMaillist(sars.getString("mail"));
					}else{
						i++;
					}
				}
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

	private String getSearchReservSQL(String selectdate){
		String searchreservsql="";
		searchreservsql +=
				"SELECT r.reservid,title,r.resourceid,resource.resource,r.sttime,r.endtime FROM kysdb.reservtable AS r JOIN kysdb.resourcetable AS resource "
				+ "ON r.resourceid = resource.resourceid"
				+ " WHERE DATEDIFF(r.sttime,'"+selectdate+"')=0 or "
						+ "DATEDIFF(r.endtime,'"+selectdate+"')=0 or (DATEDIFF(r.sttime,'"+selectdate+"')>=1 and DATEDIFF('"+selectdate+"',r.endtime)>=1)"
								+ "ORDER BY r.reservid;";
		return searchreservsql;
	}

	private String getSearchAccountSQL(List<Integer> reservidlist){
		String searchaccountsql="";
		searchaccountsql+="SELECT * from kysdb.reservertable AS R JOIN kysdb.accounttable AS A ON R.accountid=A.accountid ";
		if(0 < reservidlist.size()){
			searchaccountsql += "WHERE R.reservid IN (";
			for (int i=0;i<reservidlist.size();i++){
				searchaccountsql+= ""+ reservidlist.get(i);
				if(i == (reservidlist.size()-1)){
					searchaccountsql += ")";
				}else{
					searchaccountsql+=",";
				}
			}
		}
		searchaccountsql+="ORDER BY reservid;";
		return searchaccountsql;
	}
}
