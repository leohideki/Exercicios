package Service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import DAO.UsuarioDAO;
import Model.Usuario;
import utils.CryptoAES;

public class UsuarioService {
	UsuarioDAO dao;
	public UsuarioService() {
		dao = new UsuarioDAO();
	}
	public boolean validar(Usuario usuario){
		return dao.validar(usuario);
	}
	
	public void criar(Usuario usuario) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, 
	BadPaddingException, InvalidAlgorithmParameterException, ClassNotFoundException, IOException {	
	CryptoAES crypto = new CryptoAES();
	
	File chave = new File("C:\\Users\\leo\\Downloads\\ExercicioPratProg\\Aula07PratProg\\","chave.simetrica");
	crypto.geraCifra(usuario.getPassword(), chave);
	//crypto.geraCifra(usuario.getPassword(), new File("chave.simetrica"));
	try {
		usuario.setPassword(crypto.getTextoCifrado());
	} catch (Exception e) {
		e.printStackTrace();
	}
	dao.criar(usuario);
}

}
