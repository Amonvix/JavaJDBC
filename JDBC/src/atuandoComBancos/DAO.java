package atuandoComBancos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	private static Connection conexao;

	// method para conectar no BD via properties

	private static Connection conectar() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {
		}
		conexao = FabricaConexao.getConnection();
		return conexao;
	}

	public static void createDado(String nome) {
		try {
			String sqlString = """
					INSERT INTO pessoas (nome) VALUES(?)
					""";

			PreparedStatement stmt = conectar().prepareStatement(sqlString);
			stmt.setString(1, nome);
			stmt.execute();
			System.out.println("Pessoa incluida com sucesso! ");
			conectar().close();
		} catch (SQLException e) {
		}
	}

	// SELECT no BD

	public static void selectFromDB() {
		try {
			String selectString = """
					SELECT * FROM pessoas
					""";
			Statement stmt = conectar().createStatement();
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
			conectar().close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// UPDATE BD

	public int incluir(String sql, Object... atributos) {
		try {
			PreparedStatement stmt = conectar().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			updateDados(stmt, atributos);
			if (stmt.executeUpdate()>0) {
				ResultSet resultado = stmt.getGeneratedKeys();
				if (resultado.next()) {
					return resultado.getInt(1);
					
				}
				
			}
			return -1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private void updateDados(PreparedStatement stmt, Object[] atributos) throws SQLException {

		int indice = 1;
		for (Object atributo : atributos) {
			if (atributo instanceof String) {
				stmt.setString(indice, (String) atributo);
			} else if (atributo instanceof Integer) {
				stmt.setInt(indice, (Integer) atributo);
			}
			indice++;

		}

	}

	public static void deleteDadoID(int code) {

		try {
			String sql = """
					DELETE FROM pessoas WHERE codigo = ?
					""";
			PreparedStatement stmt = conectar().prepareStatement(sql);
			stmt.setInt(1, code);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
