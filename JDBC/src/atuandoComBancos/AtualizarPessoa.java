package atuandoComBancos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtualizarPessoa {
	public static void main(String[] args) throws SQLException {

		Connection connection = FabricaConexao.getConnection();

		System.out.println("Qual registro iremos alterar? \n");

		String selectString = """
				SELECT * FROM pessoas
				""";
		String updateString = """
				UPDATE pessoas SET nome = ? WHERE codigo = ?
						""";
		

		// SELECT
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
		System.out.println();
		
		
		// Mostrar selecionado

		Scanner entrada = new Scanner(System.in);
		System.out.print("Digite o numero do nome correspondente: ");
		int code = entrada.nextInt();
		System.out.println();
		
		String escolhidoString = """
				SELECT * FROM pessoas WHERE codigo = ?
				""";

		PreparedStatement stmt3 = connection.prepareStatement(escolhidoString);	
		stmt3.setInt(1, code);
		ResultSet pessoaResultSet = stmt3.executeQuery();
		

		if (pessoaResultSet.next()) {
			Pessoa p = new Pessoa(pessoaResultSet.getInt(1), pessoaResultSet.getString(2));
			System.out.println("O nome atual é " + p.getNome());
			entrada.nextLine();
			System.out.println();

		}
		stmt3.close();
		
		//MUDANÇA DE DADO
		
		System.out.println("Para qual nome iremos atualizar? ");
		String novoNome = entrada.nextLine();
		
		PreparedStatement stmt2 = connection.prepareStatement(updateString);
		stmt2.setString(1, novoNome);
		stmt2.setInt(2, code);
		stmt2.execute();
		
		stmt2.close();
		System.out.println("Nome atualizado com sucesso");

		ResultSet resultado2 = stmt.executeQuery(selectString);

		List<Pessoa> pessoas2 = new ArrayList<>();

		while (resultado2.next()) {
			int codigo = resultado2.getInt("codigo");
			String nome = resultado2.getString("nome");
			pessoas2.add(new Pessoa(codigo, nome));
		}
		for (Pessoa p : pessoas2) {
			System.out.println(p.getCodigo() + " ==> " + p.getNome());
		}

		stmt.close();
		entrada.close();
		connection.close();

	}

}
