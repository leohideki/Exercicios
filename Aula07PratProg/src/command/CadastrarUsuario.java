package command;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Usuario;
import Service.UsuarioService;



public class CadastrarUsuario implements Command{
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pUsername = request.getParameter("username");
		String pPassword = request.getParameter("password");
		Usuario usuario = new Usuario(pUsername, pPassword.getBytes("ISO-8859-1"));
		UsuarioService service = new UsuarioService();
		String path = request.getContextPath();
		System.out.println(path);
		try {
			service.criar(usuario);
			response.sendRedirect("index.jsp");
		
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}

}
