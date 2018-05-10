package Service;

import Model.Pais;

import java.io.IOException;
import java.util.ArrayList;

import DAO.PaisDAO;

public class ListarPaisService {
	PaisDAO dao;
	
	public ListarPaisService() throws IOException{
		dao = new PaisDAO();
	}
	public ArrayList<Pais> listarPaises() throws IOException{
		return dao.listarPaises();
	}
	public ArrayList<Pais> listarPaises(String chave) throws IOException{
		return dao.listarPaises(chave);
	}
}
