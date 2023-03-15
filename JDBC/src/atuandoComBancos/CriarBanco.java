package atuandoComBancos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarBanco {
	
	public static void main(String[] args) throws SQLException{
		
		String URL = "jdbc:mysql://localhost?verifyServerCertificate=false&useSSL=true";
		String user = "root";
		String pass = "Dani0102";
		
		Connection con = DriverManager.getConnection(URL, user, pass);
		
//		Connection con = KeyPass.getConnection();
		
				
		System.out.println("Conectou");
		
		Statement stmt = con.createStatement();
		stmt.execute("CREATE DATABASE IF NOT EXISTS curso_java");
		System.out.println("banco criado com sucesso");		
		con.close();
	}
	

}
