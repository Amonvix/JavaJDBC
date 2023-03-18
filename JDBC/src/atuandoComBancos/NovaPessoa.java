package atuandoComBancos;

import java.util.Scanner;

public class NovaPessoa {
	
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Informe o nome a ser adicionado: ");
		String nome = entrada.nextLine();		
		
		DAO.createDado(nome);
		entrada.close();
		System.out.println();
		DAO.selectFromDB();
//		
//		Connection con = FabricaConexao.getConnection();
//		
//		String sqlString = """
//				INSERT INTO pessoas (nome) VALUES(?)
//				""";
//		
//		PreparedStatement stmt = con.prepareStatement(sqlString);
//		stmt.setString(1, nome);
//		stmt.execute();
//		System.out.println("Pessoa incluida com sucesso! ");
//		con.close();
	}

}
