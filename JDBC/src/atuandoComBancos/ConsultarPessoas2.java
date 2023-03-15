package atuandoComBancos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultarPessoas2 {	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = FabricaConexao.getConnection();
		String sqlString = """
				SELECT * FROM pessoas WHERE nome LIKE ?
				""";
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Qual nome iremos procurar: ");
		String name = entrada.nextLine();
		
		PreparedStatement stmt = connection.prepareStatement(sqlString);
		stmt.setString(1, "%"+ name + "%");
		ResultSet resultado = stmt.executeQuery();
		
		List<Pessoa> pessoas = new ArrayList<>(); 		
		
		while (resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nome = resultado.getString("nome");
			pessoas.add(new Pessoa(codigo, nome));
		}
		
		for (Pessoa p : pessoas) {
			System.out.println(p.getCodigo()+" ==> "+p.getNome());
		}
		
		entrada.close();
		connection.close();
		
	}

}
