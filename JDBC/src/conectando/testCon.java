package conectando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testCon {
	
	public static void main(String[] args) throws SQLException{
		
		String URL = "jdbc:mysql://localhost/wm?verifyServerCertificate=false&useSSL=true";
		String user = "root";
		String pass = "Dani0102";
		
		Connection con = DriverManager.getConnection(URL, user, pass);		
		System.out.println("Conectou");
		
		con.close();
	}

}
