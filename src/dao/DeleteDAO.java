package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/kysdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "levelfive";

	public int delete(int reservid, String mail) {
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);


			String searchsql =""
					+ "SELECT reservid,mail "
					+ "FROM kysdb.reservertable as a "
					+ "JOIN kysdb.accounttable as b ON a.accountid = b.accountid "
					+ "where a.reservid = "+reservid+" and b.mail = '"+mail+"';";
			System.out.println(searchsql);
			PreparedStatement pstmt =conn.prepareStatement(searchsql);
			//SQL文の実行
			ResultSet rs = pstmt.executeQuery();

			int num;

			if(rs.next()){
				//reservidとmailが一致しているときに実行
				String deletesql = ""
						+ "DELETE FROM kysdb.reservtable WHERE reservid = "+reservid+";";
				PreparedStatement delstmt = conn.prepareStatement(deletesql);
				num = delstmt.executeUpdate();
			}else{
				//reservidとmailが一致していないときに実行
				return 14;
			}

			if(num==1){
				return 6;
		}
			else if(num==0){
				return 12;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return 8;
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
	return 0;
	}
}