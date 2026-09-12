import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientesDAO {
	public void cadastrarCliente(Cliente cliente) { // Recebe um Cliente e basicamente seta as informações do Cliente na correspondente da tabela
		String comando_sql = "INSERT INTO CLIENTES (NOME, CPF, IDADE, CNH, ENDERECO, VEICULOALUGADO) VALUES (?, ?, ?, ?, ?, ?)"; // Comando para inserir um Cliente no Banco
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql); // tenta estabelecer a conexão
			
			//Seta as variáveis para tabela
			ps.setString(1, cliente.getNome()); // seta o campo Nome
			ps.setString(2, cliente.getCPF()); // seta o campo CPF
			ps.setInt(3, cliente.getIdade()); // seta o campo idade
			ps.setBoolean(4, cliente.getSituacaoCarteira()); // seta o campo CNH
			ps.setString(5, cliente.getEndereco()); // seta o campo endereço
			ps.setBoolean(6, cliente.getCarroAlugado()); // seta o campo de carro alugado 
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
		}
		catch (SQLException e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Banco de dados", "Houve um erro ao conectar ao banco de dados", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void removerCliente(int id_cliente) { // Função que recebe um id e remove o Cliente do banco de dados com esse id
		String comando_sql = "DELETE FROM CLIENTES WHERE idclientes = ?"; // Comando para remover um cliente no Banco atráves de um ID
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql); // tenta estabelecer a conexão
			ps.setInt(1, id_cliente); // define o valor do parametro de id
			ps.executeUpdate(); // executa o comando
			ps.close(); // fecha a ps
		}
		catch (SQLException e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!", "Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
        }
	}
	
	public void alterarClientes(Cliente cliente, int id_cliente) { // Função que recebe um cliente e seu id(para identificar) e atualiza seus dados
		String comando_sql = "UPDATE CLIENTES SET NOME = ?, CPF = ?, VEICULOALUGADO = ?, IDADE = ?, CNH = ?, ENDERECO = ? WHERE IDCLIENTES = ?";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql);
			
			//Seta as variáveis para tabela
			//Seta as variáveis para tabela
			ps.setString(1, cliente.getNome()); // seta o campo Nome
			ps.setString(2, cliente.getCPF()); // seta o campo CPF
			ps.setBoolean(3, cliente.getCarroAlugado()); // Seta se já possui um carro alugado ou não
			ps.setInt(4, cliente.getIdade()); // seta o campo idade
			ps.setBoolean(5, cliente.getSituacaoCarteira()); // seta o campo CNH
			ps.setString(6, cliente.getEndereco()); // seta o campo endereço
			ps.setInt(7, id_cliente); // Seta o ID
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
			
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
		}
	}
	
	public void exibeTabela(JTable tabela) { // Função que recebe um Jtable e exibe a tabela do banco de Dados
		Connection conexao = null;
		try{
			conexao = Conexao.getConexao(); // Estabelece conexão
			String sql = "SELECT * FROM CLIENTES"; // Selecionando a tabela de Clientes
			Statement st = conexao.createStatement(); 
            ResultSet rs = st.executeQuery(sql);

            // Pego o modelo de tabela com os nomes das colunas
            ResultSetMetaData metaData = rs.getMetaData();
            int colunas = metaData.getColumnCount();
            String[] colunasNome = new String[colunas];
            	
            for (int i = 1; i <= colunas; i++) {
                colunasNome[i - 1] = metaData.getColumnName(i);
            }

            DefaultTableModel modelo = new DefaultTableModel() {; // crio o modelo
	            public boolean isCellEditable(int rowIndex, int collumnIndex) {
	            	return false;
	            }
	            
            };
            modelo.setColumnIdentifiers(colunasNome);
            
            while (rs.next()) { // Preenchendo o modelo com os dados do ResultSet
                Object[] dadosLinha = new Object[colunas];
                for (int i = 1; i <= colunas; i++) {
                    dadosLinha[i - 1] = rs.getObject(i);
                }
                modelo.addRow(dadosLinha);
            }

            tabela.setModel(modelo); // Coloca o modelo na tabela

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!", "Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
        }
    }
	
	public void alugaClientes(int id_veiculo) { // Função que seta o campo VeiculoAlugado como true
		String comando_sql = "UPDATE CLIENTES SET VEICULOALUGADO = ? WHERE IDCLIENTES = ?";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql);
			
			//Seta as variáveis para tabela
			ps.setBoolean(1, true); // Seta true
			ps.setInt(2, id_veiculo); // Seta o id 
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
		}
	}
	
	public void desalugaClientes(int id_veiculo) { // Função que seta o campo VeiculoAlugado como false
		String comando_sql = "UPDATE CLIENTES SET VEICULOALUGADO = ? WHERE IDCLIENTES = ?";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql);
			
			//Seta as variáveis para tabela
			ps.setBoolean(1, false); // Seta false
			ps.setInt(2, id_veiculo); // Seta o id 
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
		}
	}
	
	public Boolean verificaVeiculoAlugado(Integer id_cliente) { // Função que recebe um id e procura no banco de dados para saber se o veiculo está disponivel
		String comando_sql = "SELECT * FROM CLIENTES WHERE IDCLIENTES = " + id_cliente; // Comando para procurar nos idClientes o id desejado
		ResultSet resultado = null;
		Statement st = null;
		try {
			Integer veiculoAlugadoInt = null;
			Boolean veiculoAlugado = null;
			
			st = Conexao.getConexao().createStatement(); // tenta estabelecer a conexão
			resultado = st.executeQuery(comando_sql); // Executa o comando
			
			//Coleta as variaveis
			if(resultado.next()) {// Se tiver pego algo, então prossigo
				veiculoAlugadoInt = Integer.parseInt(resultado.getString("veiculoAlugado")); // Pega o valor e converte para Boolean
			}
			if(veiculoAlugadoInt == 1) {
				veiculoAlugado = true;
			}
			else if(veiculoAlugadoInt == 0) {
				veiculoAlugado = false;
			}
			return veiculoAlugado; // retorna a disponiilidade do veiculo
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
			return null;
		}
	}
}
