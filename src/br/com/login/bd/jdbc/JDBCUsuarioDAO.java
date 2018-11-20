package br.com.login.bd.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.login.objetos.Usuario;

public class JDBCUsuarioDAO{
	private Connection conexao;

	public JDBCUsuarioDAO(Connection conexao) {
		this.conexao = conexao;
	}

	
	/*BUSCAR*/
	public Usuario logar(String login, String senha){
		String comando = "select * from usuario where usuario = '"+ login + "' and senha = '" + senha + "'";				
		Usuario usuario = new Usuario();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()){
				int idUsuario = rs.getInt("idusuario");								
				String loginUsuario = rs.getString("usuario");				
				String senhaUsuario = rs.getString("senha");
				
				usuario.setId(idUsuario);
				usuario.setLogin(loginUsuario);
				usuario.setSenha(senhaUsuario);				
			}
		}catch (Exception e){
			e.printStackTrace();			
		}
		return usuario;	
	}
}