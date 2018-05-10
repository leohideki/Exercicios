package Service;

import DAO.UsuarioDAO;
import Model.Usuario;

public class UsuarioService {
	public boolean validar(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO();
		return dao.validar(usuario);
	}
}
