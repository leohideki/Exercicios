package Service;

import Model.Pais;


import java.util.ArrayList;

import DAO.PaisDAO;

public class ListarPaisService {
	PaisDAO dao;
	
	public ListarPaisService(){
		dao = new PaisDAO();
	}
	public ArrayList<Pais> listarPaises(){
		return dao.listarPaises();
	}
	public ArrayList<Pais> listarPaises(String chave){
		return dao.listarPaises(chave);
	}
}
