package atuandoComBancos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcluirPessoas {
	
	public static void main(String[] args) throws SQLException{
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Qual registro iremos excluir? \n");
		
		DAO.selectFromDB();
		
		System.out.println();
				
		System.out.print("Digite o numero do nome correspondente: ");
		int code = entrada.nextInt();
		entrada.nextLine();
		System.out.println();
					
		mostraEscolhido(code);
		
		if (mostraEscolhido(code).equalsIgnoreCase("Pessoa não encontrada")){
			System.out.println("Registro não removido");
			
		}else {
			
			System.out.println("Tem certeza que quer excluir? ");
			String confirmacao = entrada.nextLine();
			
			
			
			if (confirmacao.equalsIgnoreCase("sim")) {
				DAO.deleteDadoID(code);
				System.out.println("Registro excluido com sucesso");
			}else {
				System.out.println("Registro não removido");
			}
			
		}
		
		System.out.println();
		DAO.selectFromDB();
		entrada.close();
	}
	
	public static String mostraEscolhido(int code) throws SQLException {

		Connection connection = FabricaConexao.getConnection();
		String escolhidoString = """
				SELECT * FROM pessoas WHERE codigo = ?
				""";

		PreparedStatement stmt = connection.prepareStatement(escolhidoString);
		stmt.setInt(1, code);
		ResultSet pessoaResultSet = stmt.executeQuery();

		if (pessoaResultSet.next()) {
			Pessoa p = new Pessoa(pessoaResultSet.getInt(1), pessoaResultSet.getString(2));
			return "O nome atual é " + p.getNome();
		} else {
			return "Pessoa não encontrada";

		}
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
