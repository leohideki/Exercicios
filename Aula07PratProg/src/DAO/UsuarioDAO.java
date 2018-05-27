package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.ConnectionFactory;
import Model.Usuario;

public class UsuarioDAO {
	public boolean validar(Usuario usuario) {
		String sqlSelect = "SELECT login, senha FROM usuario "
				+ "WHERE login = ? and senha = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, usuario.getUsername());
			stm.setBytes(2, usuario.getPassword());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		return false;
	}
	
	public void criar(Usuario usuario) throws SQLException {
		String sqlSelect = "INSERT INTO usuario VALUES(?,?)";
		Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlSelect);
		try{
			stm.setString(1, usuario.getUsername());
			stm.setBytes(2, usuario.getPassword());
			stm.execute();
		}catch(Exception e) {
			System.out.println(e);;
		}
	}

}
