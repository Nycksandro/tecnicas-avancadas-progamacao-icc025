import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String url = "jdbc:mysql://localhost:3306/veiculosbd";
	private static final String user = "root";
	private static final String password = "0321";
	
	private static Connection conexao;
	
	public static Connection getConexao() throws SQLException { // Método que tenta se conectar, se conseguir (ou ainda não se conectou), se conecta, se não conseguir realizar a conexão apresenta mensagem de erro
		try { // Tenta a conexão
			if(conexao == null) { // Se for igual a null, tenta se conectar e retorna essa conexão
				conexao = DriverManager.getConnection(url,user,password);
				return conexao;
			}
			else { // Não é null, apenas retorna a conexão
				return conexao;
			}
		}
		catch (SQLException e) { // Não conseguiu, printa a mensagem de erro e retorna nulo
			e.printStackTrace();
			return null;
		}
	}
}
