package atuandoComBancos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Uteis {
	
	
		
	public static void selectDB() throws SQLException {
		Connection connection = FabricaConexao.getConnection();

		String selectString = """
				SELECT * FROM pessoas
				""";
		Statement stmt = connection.createStatement();
		ResultSet resultado = stmt.executeQuery(selectString);

		List<Pessoa> pessoas = new ArrayList<>();

		while (resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nome = resultado.getString("nome");
			pessoas.add(new Pessoa(codigo, nome));
		}
		for (Pessoa p : pessoas) {
			System.out.println(p.getCodigo() + " ==> " + p.getNome());
		}
		
	}
	
	public static void mostraEscolhido(int code) throws SQLException {
		
		Connection connection = FabricaConexao.getConnection();
		String escolhidoString = """
				SELECT * FROM pessoas WHERE codigo = ?
				""";

		PreparedStatement stmt = connection.prepareStatement(escolhidoString);	
		stmt.setInt(1, code);
		ResultSet pessoaResultSet = stmt.executeQuery();
		

		if (pessoaResultSet.next()) {
			Pessoa p = new Pessoa(pessoaResultSet.getInt(1), pessoaResultSet.getString(2));
			System.out.println("O nome atual é " + p.getNome());
			System.out.println();

		}
		stmt.close();
		connection.close();
	}
	
	public static void confirma(int code) throws SQLException {
			Connection connection = FabricaConexao.getConnection();
			String excluirString = """
					DELETE FROM pessoas WHERE codigo = ?
					""";
			PreparedStatement stmt = connection.prepareStatement(excluirString);
			stmt.setInt(1, code);
			stmt.execute();
			stmt.close();
			
			
		}
	}
	

