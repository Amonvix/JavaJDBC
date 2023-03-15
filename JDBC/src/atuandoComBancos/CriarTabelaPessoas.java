package atuandoComBancos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelaPessoas {

	public static void main(String[] args) throws SQLException {

		Connection con = FabricaConexao.getConnection();

		String sqlString = """
				CREATE TABLE pessoas(
				codigo INT AUTO_INCREMENT PRIMARY KEY,
				nome VARCHAR(80) NOT NULL
				)
					""";

		Statement stmt = con.createStatement();
		stmt.execute(sqlString);

		System.out.println("Tabela pessoas criada com sucesso!");

		con.close();
	}

}
