package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final ThreadLocal<Connection> conn = new ThreadLocal<>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection obtemConexao() throws SQLException {
		if (conn.get() == null){
			conn.set(DriverManager
					.getConnection("jdbc:mysql://127.0.0.1/bdEx02?user=root&password=Santosfc10"));
		}
		return conn.get();
	}
	
	public static void fecharConexao() throws SQLException {
		if(conn.get() != null){
			conn.get().close();
			conn.set(null);
		}
	}

}
