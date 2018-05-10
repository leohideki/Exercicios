package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Pais;
import DAO.ConnectionFactory;

public class PaisDAO {
	public int criar(Pais pais) throws IOException {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulacao());
				stm.setDouble(3, pais.getArea());
				stm.execute();
				String sqlQuery = "SELECT LAST_INSERT_ID()";
				try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
						ResultSet rs = stm2.executeQuery();) {
					if (rs.next()) {
						pais.setId(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return pais.getId();
	}

	public void atualizar(Pais pais) throws IOException {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
				stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulacao());
				stm.setDouble(3, pais.getArea());
				stm.setInt(4, pais.getId());
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
	}

	public void excluir(int id) throws IOException {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
				stm.setInt(1, id);
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
	}

	public Pais carregar(int id) throws IOException {
		Pais pais = new Pais();
		pais.setId(id);
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, pais.getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						pais.setNome(rs.getString("nome"));
						pais.setPopulacao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
					} else {
						pais.setId(-1);
						pais.setNome(null);
						pais.setPopulacao(0);
						pais.setArea(0);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return pais;
	}
	public void maiorPopulacao(Pais pais) {
		String sqlMaxPop = "SELECT * FROM pais WHERE populacao = (SELECT max(populacao) FROM Pais)";
		try (Connection conn = ConnectionFactory.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlMaxPop);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				pais.setId(rs.getInt("id"));
				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setArea(rs.getDouble("area"));
			}else {
				System.out.println("erro");
			}
			}catch (SQLException e) {
				System.out.println(e);
			}
	}
	public void menorArea(Pais pais) {
		String sqlMinArea = "SELECT * FROM pais WHERE area = (SELECT min(area) FROM pais)";
		try (Connection conn = ConnectionFactory.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlMinArea);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				pais.setId(rs.getInt("id"));
				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setArea(rs.getDouble("area"));
			}
			}catch (SQLException e) {
				System.out.println(e);
			}
	}
	public String[] vetorTresPaises() {
		String sqlVetTresPais = "SELECT nome FROM pais ORDER BY id";
		String[] vetorTresPais = new String[3];
		int contador = 0 ;
		try (Connection conn = ConnectionFactory.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlVetTresPais);
			ResultSet rs = stm.executeQuery();
			while(rs.next() && contador < 3 ) {
				vetorTresPais[contador] = rs.getString("nome");
				contador = contador + 1;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return vetorTresPais;
	}
	
	public ArrayList<Pais> listarPaises() throws IOException {
		Pais pais;
		ArrayList<Pais> lista = new ArrayList<>();
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			String sqlSelect = "SELECT id, nome, populacao, area FROM pais";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						pais = new Pais();
						pais.setId(rs.getInt("id"));
						pais.setNome(rs.getString("nome"));
						pais.setPopulacao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
						lista.add(pais);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return lista;
	}

	public ArrayList<Pais> listarPaises(String chave) throws IOException {
		Pais pais;
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais where upper(nome) like ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						pais = new Pais();
						pais.setId(rs.getInt("id"));
						pais.setNome(rs.getString("nome"));
						pais.setPopulacao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
						lista.add(pais);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return lista;
	}
}