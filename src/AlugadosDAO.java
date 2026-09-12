import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AlugadosDAO {
	public void colocarNaTabela(Pedido pedido) { // Recebe um pedido e basicamente seta as informações correspondente da tabela alugados
		String comando_sql = "INSERT INTO ALUGADOS (NOMECLIENTE, IDCLIENTES, IDVEICULOS, DIASALUGADOS, CPF, CATEGORIA, CUSTODIARIO, GPS, ASSENTOSCRIANCAS, SEGUROCOMPLETO, LOCALRETIRADA, LOCALDEVOLUCAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Comando para inserir um par Cliente-Veiculo no Banco
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql); // tenta estabelecer a conexão
			
			//Seta as variáveis para tabela
			ps.setString(1, pedido.getNome()); // seta o campo Nome
			ps.setInt(2, pedido.getIdCliente()); // seta o campo Id Cliente
			ps.setInt(3, pedido.getIdVeiculo()); // seta o campo Id Veiculo
			ps.setInt(4, pedido.getDiasAlugados()); // seta o campo diasAlugados
			ps.setString(5, pedido.getCPF()); // seta o campo CPF
			ps.setString(6, pedido.getCategoria()); // seta o campo categoria
			ps.setDouble(7, pedido.getCustoDiario()); // seta o campo custoDiario
			ps.setBoolean(8, pedido.getGPS()); // seta o campo GPS
			ps.setBoolean(9, pedido.getAssentosCriancas()); // seta o campo assentosCriancas
			ps.setBoolean(10, pedido.getSeguroCompleto()); // seta o campo seguroCompleto
			ps.setString(11, pedido.getLocalRetirada()); // seta o campo localRetirada
			ps.setString(12, pedido.getLocalDevolucao()); // seta o campo localDevolucao
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
		}
		catch (SQLException e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Banco de dados", "Houve um erro ao conectar ao banco de dados", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void removeTabela(Integer id_veiculo) { // Função que recebe o id do veiculo e remove do banco de alugados (pagando o alugel)
		String comando_sql = "DELETE FROM ALUGADOS WHERE idVeiculos = ?"; // Comando para remover um veículo no Banco atráves de um ID
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql); // tenta estabelecer a conexão
			ps.setInt(1, id_veiculo); // define o valor do parametro de id
			ps.executeUpdate(); // executa o comando
			ps.close(); // fecha a ps
		}
		catch (SQLException e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!", "Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
        }

	}
	
	public void exibeTabela(JTable tabela) { // Função que recebe um Jtable e exibe a tabela do banco de Dados
		Connection conexao = null;
		try{
			conexao = Conexao.getConexao(); // Estabelece conexão
			String sql = "SELECT * FROM ALUGADOS"; // Selecionando a tabela de Veiculos
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
	
	public Pedido procuraClienteEVeiculo(int idCliente, int idVeiculo) {
		String comando_sql = "SELECT * FROM CLIENTES WHERE IDCLIENTES = " + idCliente; // comando para achar a linha do id 
		String comando_sql2 = "SELECT * FROM VEICULOS WHERE IDVEICULOS = " + idVeiculo; // comando para achar a linha do id 
		ResultSet resultado = null;
		Statement st = null;
		try {
			String nome = null;
			String cpf = null;
			String categoria = null;
			Double custoDiario = -1.0;
			Boolean gps = false;
			Boolean assentosCriancas = false;
			Boolean seguroCompleto = false;
			Integer diasAlugados = 0;
			String localRetirada = null;
			String localDevolucao = null;
			
			st = Conexao.getConexao().createStatement(); // tenta estabelecer a conexão
			resultado = st.executeQuery(comando_sql); // Executa o comando
			
			//Coleta as variaveis
			if(resultado.next()) {// Se tiver pego algo, então prossigo
				nome = resultado.getString("nome");
				cpf = resultado.getString("cpf");
			}
			
			resultado = st.executeQuery(comando_sql2); //Executa o segundo comando
			if(resultado.next()) {// Se tiver pego algo, então prossigo
				categoria = resultado.getString("categoria");
				custoDiario = resultado.getDouble("custoDiario");
			}
			Pedido pedido = new Pedido(idCliente, idVeiculo, nome,cpf,categoria,custoDiario,gps,assentosCriancas,seguroCompleto,diasAlugados, localRetirada, localDevolucao);
			return pedido; // Retorna o pedido
		}
		catch (SQLException e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Banco de dados", "Houve um erro ao conectar ao banco de dados", JOptionPane.ERROR_MESSAGE);
        }
		return null;
	}
	
	public Double calculaAluguel(Integer id_veiculo) { // Calcula o valor total do aluguel com base na id do veiculo
		String comando_sql = "SELECT * FROM ALUGADOS WHERE IDVEICULOS = " + id_veiculo; // comando para achar a linha do id 
		ResultSet resultado = null;
		Statement st = null;
		try {
			Double aluguelTotal = 0.0;
			Boolean gps = false;
			Boolean assentosCriancas = false;
			Boolean seguroCompleto = false;
			Integer diasAlugados = 0;
			
			st = Conexao.getConexao().createStatement(); // tenta estabelecer a conexão
			resultado = st.executeQuery(comando_sql); // Executa o comando
			
			//Coleta as variaveis
			if(resultado.next()) {// Se tiver pego algo, então prossigo
				gps = resultado.getBoolean("gps"); //pega se possui ou não gps
				assentosCriancas = resultado.getBoolean("assentosCriancas"); //pega se possui ou não assento p/ crianças
				seguroCompleto = resultado.getBoolean("seguroCompleto"); // pega se possui ou não seguro total
				diasAlugados = resultado.getInt("diasAlugados"); // pega a quantidade de dias
				aluguelTotal = resultado.getDouble("custoDiario"); // pega o custo diario base
			}
			if(gps == true) {
				aluguelTotal += 50.0;
			}
			if(assentosCriancas == true) {
				aluguelTotal += 30.0;
			}
			if(seguroCompleto == true) {
				aluguelTotal += 80.0;
			}
			aluguelTotal = aluguelTotal * diasAlugados; // multiplica pelo número de dias
			
			return aluguelTotal;
		}
		catch (SQLException e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Banco de dados", "Houve um erro ao conectar ao banco de dados", JOptionPane.ERROR_MESSAGE);
        }
		return null;
	}
}
