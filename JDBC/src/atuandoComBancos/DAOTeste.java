package atuandoComBancos;

public class DAOTeste {
	
	public static void main(String[] args) {
		
	
	DAO dao = new DAO();
	
	String sqlString= "INSERT INTO pessoas (nome) VALUES (?)";
	
	dao.incluir(sqlString, "João da Silva");
	}
	
	

}
