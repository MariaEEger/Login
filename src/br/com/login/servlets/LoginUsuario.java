package br.com.login.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import br.com.login.objetos.Usuario;
import br.com.login.bd.conexao.Conexao;
import br.com.login.bd.jdbc.JDBCUsuarioDAO;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class LoginUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public LoginUsuario(){
		super();

	}
	private void process(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {

		Usuario usuario = new Usuario();

		try{			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			String login =  request.getParameter("login").toString();			
			String senha = request.getParameter("senha").toString();			
			usuario = jdbcUsuario.logar(login, senha);			
			String redic = null;	
			Map<String, String> msg = new HashMap<String, String>();			
			
			//Valida sessao
			if ((usuario.getLogin() == null) && (usuario.getSenha() == null)){
				msg.put("msg", "Não foi possível fazer login do usuário.");				
				redic = "/login/";
			}else{					
				HttpSession sessao = request.getSession();
				//sessao.setAttribute("txtusuario", request.getParameter("txtusuario"));				
				sessao.setAttribute("id", usuario.getId());
				msg.put("msg", "Usuário logado com sucesso.");
				redic = "/login/resources/usuario/logarUsuario.html";				
			}
			msg.put("redic", redic);
			conec.fecharConexao();
			/* Retorna a resposta(mensagem) para o usuário a partir do Json*/
			String json = new Gson().toJson(msg);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");					
			response.getWriter().write(json);		
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		process(request, response);				
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		process(request, response);				
	}

}
