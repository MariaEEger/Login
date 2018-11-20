package br.com.login.bd.conexao;
import java.sql.Connection;

public class Conexao {
	
	private Connection conexao;
	
	//Abrindo conexão
	public Connection abrirConexao(){
		try{			
			Class.forName("org.gjt.mm.mysql.Driver");
			conexao = java.sql.DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Login",
					"root", "neo@1234");
		}
		catch (Exception e){			
			e.printStackTrace();
		}
		return conexao;
	}
	//Fechando conexão
	public void fecharConexao(){
		try{
			conexao.close();
		}catch (Exception e){			
			e.printStackTrace();
		}
	}

}
