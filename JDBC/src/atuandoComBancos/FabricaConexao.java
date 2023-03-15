package atuandoComBancos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	public static Connection getConnection() throws SQLException{
		try {
			String URL = "jdbc:mysql://localhost/curso_java?verifyServerCertificate=false&useSSL=true";
			String user = "root";
			String pass = "Dani0102";
			
			return DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
