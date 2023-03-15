package atuandoComBancos;

import java.sql.SQLException;
import java.util.Scanner;

public class ExcluirPessoas {
	
	public static void main(String[] args) throws SQLException{
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Qual registro iremos excluir? \n");
		
		Uteis.selectDB();
		
		System.out.println();
				
		System.out.print("Digite o numero do nome correspondente: ");
		int code = entrada.nextInt();
		entrada.nextLine();
		System.out.println();
		
		Uteis.mostraEscolhido(code);
		
		System.out.println("Tem certeza que quer excluir? ");
		String confirmacao = entrada.nextLine();
		
		
		
		if (confirmacao.equalsIgnoreCase("sim")) {
			Uteis.confirma(code);
			System.out.println("Registro excluido com sucesso");
		}else {
			System.out.println("Registro não removido");
		}
		
		System.out.println();
		Uteis.selectDB();
		entrada.close();
	}

}
