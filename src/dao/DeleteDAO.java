package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:postgresql://localhost:3306/kysdb";
	private final String DB_USER = "root";
	private final String DB_PASS = "1714";
	
	public int delete(int reservid , String password) {
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			
			
			String deletesql ="delete from KYSDB.reservtable join KYSDB.accounttable on KYSDB.accounttable.accountid=KYSDB.reservtable.accountid "
					+ "where"+reservid+"=reservid and'"+password+"'=password;";
			
			PreparedStatement pstmt =conn.prepareStatement(deletesql);
			//SQL文の実行
			int num = pstmt.executeUpdate();
			
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