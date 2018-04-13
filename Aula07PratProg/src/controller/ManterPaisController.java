package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;


/**
 * Servlet implementation class ManterPaisController
 */
@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		long pPopulacao = Long.parseLong(request.getParameter("populacao"));
		double pArea = Double.parseDouble(request.getParameter("area"));
		String acao = request.getParameter("acao");
		
		//instanciar o javabean
		Pais pais = new Pais();
		pais.setNome(pNome);
		pais.setPopulacao(pPopulacao);
		pais.setArea(pArea);
		
		//instanciar o service
		PaisService ps = new PaisService();
		RequestDispatcher dispatcher = null;
		
		switch(acao){
		case "Incluir":
			ps.criar(pais);
			pais = ps.carregar(pais.getId());
			
			//manda parametro para o JSP via request
			request.setAttribute("pais", pais);
			dispatcher = request.getRequestDispatcher("Pais.jsp");
			break;
		}
		
		
		//despachar para o JSP correto
		dispatcher.forward(request, response);
		
	}

}
