package atuandoComBancos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarPessoa {
	public static void main(String[] args) throws SQLException {

		Connection connection = FabricaConexao.getConnection();

		System.out.println("Qual registro iremos alterar? \n");

		//refactored
		
		DAO.selectFromDB();
		
		

		// SELECT
		
		
//		String selectString = """
//				SELECT * FROM pessoas
//				""";
//		Statement stmt = connection.createStatement();
//		ResultSet resultado = stmt.executeQuery(selectString);
//
//		List<Pessoa> pessoas = new ArrayList<>();
//
//		while (resultado.next()) {
//			int codigo = resultado.getInt("codigo");
//			String nome = resultado.getString("nome");
//			pessoas.add(new Pessoa(codigo, nome));
//		}
//		for (Pessoa p : pessoas) {
//			System.out.println(p.getCodigo() + " ==> " + p.getNome());
//		stmt.close();
//		}
		System.out.println();
		
		
		// Mostrar selecionado

		Scanner entrada = new Scanner(System.in);
		System.out.print("Digite o numero do nome correspondente: ");
		int code = entrada.nextInt();
		System.out.println();
		
		String escolhidoString = """
				SELECT * FROM pessoas WHERE codigo = ?
				""";

		PreparedStatement stmt = connection.prepareStatement(escolhidoString);	
		stmt.setInt(1, code);
		ResultSet pessoaResultSet = stmt.executeQuery();
		

		if (pessoaResultSet.next()) {
			Pessoa p = new Pessoa(pessoaResultSet.getInt(1), pessoaResultSet.getString(2));
			System.out.println("O nome atual é " + p.getNome());
			entrada.nextLine();
			System.out.println();

		}
		stmt.close();
		
		//MUDANÇA DE DADO UPDATE
		String updateString = """
				UPDATE pessoas SET nome = ? WHERE codigo = ?
						""";
		
		System.out.println("Para qual nome iremos atualizar? ");
		String novoNome = entrada.nextLine();
		
		
		PreparedStatement stmt1 = connection.prepareStatement(updateString);
		stmt1.setString(1, novoNome);
		stmt1.setInt(2, code);
		stmt1.execute();
		
		stmt1.close();
		System.out.println("Nome atualizado com sucesso");

		DAO.selectFromDB();

		
		entrada.close();
		connection.close();

	}

}
