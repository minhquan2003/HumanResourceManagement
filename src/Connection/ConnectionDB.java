package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlynhansu", "root", "wwafawdwg?");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return con;
	}
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnectionDB.getConnection();
		if(conn != null) {
			System.out.println("Connect to MySQL successfully!");
			conn.close();
		}else
			System.out.println("Can not connect to MySQL!");
	}
	
}
