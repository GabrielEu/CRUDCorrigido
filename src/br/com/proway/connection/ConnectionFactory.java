package br.com.proway.connection;

//Importar classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	//Atributos
	private final String url = "jdbc:mysql://localhost:3306/escola_proway";
	private final String user = "root";
	private final String password = "";
	
	//Método para obter a conexão
	public Connection obterConexao(){
		
		//Variável contendo a conexão
		Connection conexao = null;
		
		//Tentar realizar a conexão
		try{
			conexao = DriverManager.getConnection(url, user, password);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		//Retornar a conexão
		return conexao;
	}
}









