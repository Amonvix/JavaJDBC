package atuandoComBancos;

public class ConsultarPessoas1 {

	public static void main(String[] args) {

		
			DAO.selectFromDB();
		

}}

//		
//		
//		Connection connection = FabricaConexao.getConnection();
//		
//		String sqlString = """
//				SELECT * FROM pessoas 
//				""";
//		
//		Statement stmt = connection.createStatement();
//		ResultSet resultado = stmt.executeQuery(sqlString);
//		
//		List<Pessoa> pessoas = new ArrayList<>(); 		
//		
//		while (resultado.next()) {
//			int codigo = resultado.getInt("codigo");
//			String nome = resultado.getString("nome");
//			pessoas.add(new Pessoa(codigo, nome));
//		}
//		
//		for (Pessoa p : pessoas) {
//			System.out.println(p.getCodigo()+" ==> "+p.getNome());
//		}
//		
//		
//		connection.close();
