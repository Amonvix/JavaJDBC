package atuandoComBancos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexao {
	public static Connection getConnection() {
		try {
			Properties properties = getProperties();
			String URL = properties.getProperty("banco.url");
			String user = properties.getProperty("banco.usuario");
			String pass = properties.getProperty("banco.senha");
			
			return DriverManager.getConnection(URL, user, pass);
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private static Properties getProperties() throws IOException {
		Properties properties = new Properties();
		String caminho = "/conexao.properties";
		properties.load(FabricaConexao.class.getResourceAsStream(caminho));
		return properties;
	}
}
