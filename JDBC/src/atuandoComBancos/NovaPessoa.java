package atuandoComBancos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NovaPessoa {
	
	public static void main(String[] args) throws SQLException {
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Informe o nome a ser adicionado: ");
		String nome = entrada.nextLine();
		Connection con = FabricaConexao.getConnection();
		
		String sqlString = """
				INSERT INTO pessoas (nome) VALUES(?)
				""";
		
		PreparedStatement stmt = con.prepareStatement(sqlString);
		stmt.setString(1, nome);
		stmt.execute();
		entrada.close();
		System.out.println("Pessoa incluida com sucesso! ");
		con.close();
	}

}
