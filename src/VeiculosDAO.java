

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VeiculosDAO {
	public void cadastrarVeiculo(Veiculo veiculo) { // Recebe um veiculo e basicamente seta as informações do veículo na correspondente da tabela
		String comando_sql = "INSERT INTO VEICULOS (CATEGORIA, NUMMAXPASSAG, TAMBAGAGEIRO, TIPOCAMBIO, ARCONDICIONADO, MEDIACONSUMO ,AIRBAG, FREIOABS, DVD, CUSTODIARIO, DISPONIBILIDADE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Comando para inserir um veículo no Banco
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql); // tenta estabelecer a conexão
			
			//Seta as variáveis para tabela
			ps.setString(1, veiculo.getCategoria()); // Seta a categoria
			ps.setInt(2, veiculo.getNumMaxPassageiros()); // Seta o número máximo de passageiros
			ps.setDouble(3, veiculo.getTamanhoBagageiro()); // Seta o tamanho do bagageiro
			ps.setString(4, veiculo.getTipoCambio()); // |Seta o tipo de câmbio
			ps.setBoolean(5, veiculo.temAr()); // Seta se tem ar ou não
			ps.setDouble(6, veiculo.getMediaConsumo()); // Seta o consumo médio
			ps.setBoolean(7, veiculo.temAirBag()); // Seta se tem Airbag ou não
			ps.setBoolean(8, veiculo.temFreioABS()); // Seta se tem freio abs ou não
			ps.setBoolean(9, veiculo.temDVD()); // Seta se tem DVD ou não
			ps.setDouble(10, veiculo.getCustoDiario()); // Seta o custo diário
			ps.setBoolean(11, veiculo.getDisponibilidade()); // Seta disponibilidade
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
		}
		catch (SQLException e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Banco de dados", "Houve um erro ao conectar ao banco de dados", JOptionPane.ERROR_MESSAGE);
        }
		
	}
	
	public void exibeTabela(JTable tabela) { // Função que recebe um Jtable e exibe a tabela do banco de Dados
		Connection conexao = null;
		try{
			conexao = Conexao.getConexao(); // Estabelece conexão
			String sql = "SELECT * FROM VEICULOS"; // Selecionando a tabela de Veiculos
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
	
	public void removerVeiculo(int id_veiculo) { // Função que remove o veículo pelo campo da ID
		String comando_sql = "DELETE FROM VEICULOS WHERE idVeiculos = ?"; // Comando para remover um veículo no Banco atráves de um ID
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
	
	public void alterarVeiculo(Veiculo veiculo, Integer id_veiculo) { // Função que atualiza o veículo pelo campo do ID
		String comando_sql = "UPDATE VEICULOS SET CATEGORIA = ?, NUMMAXPASSAG = ?, TAMBAGAGEIRO = ?, TIPOCAMBIO = ?, ARCONDICIONADO = ?, MEDIACONSUMO = ?, AIRBAG = ?, FREIOABS = ?, DVD = ?, CUSTODIARIO = ?, DISPONIBILIDADE = ? WHERE IDVEICULOS = ?";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql);
			
			//Seta as variáveis para tabela
			ps.setString(1, veiculo.getCategoria()); // Seta a categoria
			ps.setInt(2, veiculo.getNumMaxPassageiros()); // Seta o número máximo de passageiros
			ps.setDouble(3, veiculo.getTamanhoBagageiro()); // Seta o tamanho do bagageiro
			ps.setString(4, veiculo.getTipoCambio()); // |Seta o tipo de câmbio
			ps.setBoolean(5, veiculo.temAr()); // Seta se tem ar ou não
			ps.setDouble(6, veiculo.getMediaConsumo()); // Seta o consumo médio
			ps.setBoolean(7, veiculo.temAirBag()); // Seta se tem Airbag ou não
			ps.setBoolean(8, veiculo.temFreioABS()); // Seta se tem freio abs ou não
			ps.setBoolean(9, veiculo.temDVD()); // Seta se tem DVD ou não
			ps.setDouble(10, veiculo.getCustoDiario()); // Seta o custo diário
			ps.setBoolean(11, veiculo.getDisponibilidade()); // Seta a disponibilidade
			ps.setInt(12, id_veiculo); // Seta o Id Do veiculo
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
			
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
		}
	}
	public String geraRelatorio() { // Função que percorre a tabela e gera um relatório sobre as principais informações
		String comando_sql = "";
		String relatorio = "";
		Statement ps = null;
		try {
			Double maiorCustoDiario = -1.0; // Maior custo diário
			Double menorCustoDiario = -1.0; // Menor custo diário
			Double maiorBagageiro = -1.0; // Maior Bagageiro
			Double menorBagageiro = -1.0; // Menor Bagageiro
			Integer maisPassageiros = -1; // Maior capacidade de passageiros
			Integer menosPassageiros = -1; // Menor capacidade de passageiros
			Double maiorMediaConsumo = -1.0; // Maior Media de consumo
			Double menorMediaConsumo = -1.0; // Menor Media de consumo
			Integer quantVeiculosArCondicionado = 0; // Representa a quantidade de Veículos com Ar condicionado
			Integer quantTotalVeiculos = 0; // Representa a quantidade total de Veículos no Sistema
			Integer quantVeiculosDisponiveis = 0; // Representa a quantidade de veículos com carros disponíveis
			Integer quantVeiculosIndisponiveis = 0; // Representa a quantidade de veículos com carros indisponíveis
			Integer quantVeiculosAirBag = 0; // Representa a quantidade de veículos com Air Bag
			Integer quantVeiculosFreioABS = 0; // Representa a quantidade de veículos com FreioABS
			Integer quantVeiculosDVD = 0; // Representa a quantidade de carros com DVD
			Double lucroDiario = 0.0; // Representa o lucro diario
			
			ps = Conexao.getConexao().createStatement();
			ResultSet resultado = null; // Resultado da busca
			
			// Achar a quantidade total de veiculos
			comando_sql = "SELECT COUNT(*) AS total_linhas FROM VEICULOS"; // pega a quantidade total de linhas
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo
				quantTotalVeiculos = resultado.getInt("total_linhas"); // pega o número
			}
			
			// Achar a quantidade de veiculos disponiveis
			comando_sql = "SELECT COUNT(*) AS total_linhas FROM VEICULOS WHERE DISPONIBILIDADE = 1"; // pega a quantidade total de linhas
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo
				quantVeiculosDisponiveis = resultado.getInt("total_linhas"); // pega o número
			}
			
			// Achar a quantidade de veiculos indisponiveis
			comando_sql = "SELECT COUNT(*) AS total_linhas FROM VEICULOS WHERE DISPONIBILIDADE = 0"; // pega a quantidade total de linhas
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo
				quantVeiculosIndisponiveis = resultado.getInt("total_linhas"); // pega o número
			}
			
			// Acha o lucro diario
			comando_sql = "SELECT SUM(custoDiario) AS total_preco FROM VEICULOS WHERE DISPONIBILIDADE = 0";
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo  
				lucroDiario = resultado.getDouble("total_preco"); // pega o número
			}
			
			// Achar a quantidade de veiculos com ar-condicionado
			comando_sql = "SELECT COUNT(*) AS total_linhas FROM VEICULOS WHERE ARCONDICIONADO = 1"; // pega a quantidade total de linhas
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo
				quantVeiculosArCondicionado = resultado.getInt("total_linhas"); // pega o número
			}
			
			// Achar a quantidade de carros com AirBag
			comando_sql = "SELECT COUNT(*) AS total_linhas FROM VEICULOS WHERE AIRBAG = 1"; // pega a quantidade total de linhas
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo
				quantVeiculosAirBag = resultado.getInt("total_linhas"); // pega o número
			}
			
			// Achar a quantidade de carros com FreioABS
			comando_sql = "SELECT COUNT(*) AS total_linhas FROM VEICULOS WHERE FREIOABS = 1"; // pega a quantidade total de linhas
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo
				quantVeiculosFreioABS = resultado.getInt("total_linhas"); // pega o número
			}
		
			// Achar a quantidade de carros com DVD
			comando_sql = "SELECT COUNT(*) AS total_linhas FROM VEICULOS WHERE DVD = 1"; // pega a quantidade total de linhas
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { // Se conseguiu pegar algo
				quantVeiculosDVD = resultado.getInt("total_linhas"); // pega o número
			}
			
			//Acha o maior e o meno Bagageiro
			comando_sql = "SELECT MAX(tamBagageiro) AS maior_valor, MIN(tamBagageiro) AS menor_valor FROM VEICULOS";
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { //Se conseguiu pegar algo
				 maiorBagageiro = resultado.getDouble("maior_valor"); // Pega o maior
				menorBagageiro = resultado.getDouble("menor_valor"); // Pega o menor
			}
			
			// Achar o maior e menor custoDiario
			comando_sql = "SELECT MAX(custoDiario) AS maior_valor, MIN(custoDiario) AS menor_valor FROM VEICULOS";
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { //Se conseguiu pegar algo
				maiorCustoDiario = resultado.getDouble("maior_valor"); // Pega o maior
				menorCustoDiario = resultado.getDouble("menor_valor"); // Pega o menor
			}
			
			// Achar o maior e menor mediaConsumo
			comando_sql = "SELECT MAX(mediaConsumo) AS maior_valor, MIN(mediaConsumo) AS menor_valor FROM VEICULOS";
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { //Se conseguiu pegar algo
				maiorMediaConsumo = resultado.getDouble("maior_valor"); // Pega o maior
				menorMediaConsumo = resultado.getDouble("menor_valor"); // Pega o menor
			}
			
			// Achar a maior e menor Quantidade de passageiros
			comando_sql = "SELECT MAX(numMaxPassag) AS maior_valor, MIN(numMaxPassag) AS menor_valor FROM VEICULOS";
			resultado = ps.executeQuery(comando_sql); // executa e retorna um objeto
			if(resultado.next()) { //Se conseguiu pegar algo
				maisPassageiros = resultado.getInt("maior_valor"); // Pega o maior
				menosPassageiros = resultado.getInt("menor_valor"); // Pega o menor
			}
			ps.close(); // fecha
			
			//Cria a String do relatório
			relatorio = "Quantidade de veículos: " + quantTotalVeiculos + "\n"
					+ "Quantidade de veículos disponíveis: " + quantVeiculosDisponiveis + "\n"
					+ "Quantidade de veículos alugados: " + quantVeiculosIndisponiveis + "\n"
					+ "Maior custo diário: " + maiorCustoDiario + " R$\n"
					+ "Menor custo diário: " + menorCustoDiario + " R$\n"
					+ "Maior média de consumo: " + maiorMediaConsumo + " Km/L\n"
					+ "Menor média de consumo: " + menorMediaConsumo + " Km/L\n"
					+ "Tamanho do maior Bagageiro: " + maiorBagageiro + " Litros\n"
					+ "Tamanho do menor Bagageiro: " + menorBagageiro + " Litros\n"
					+ "Maior quantidade de passageiros: " + maisPassageiros + "\n"
					+ "Menor quantidade de passageiros: " + menosPassageiros + "\n"
					+ "Quantidade de veículos com Ar condiconado: " + quantVeiculosArCondicionado + "\n"
					+ "Quantidade de veículos com Air Bag: " + quantVeiculosAirBag + "\n"
					+ "Quantidade de veículos com Freio ABS: " + quantVeiculosFreioABS + "\n"
					+ "Quantidade de veículos com DVD: " + quantVeiculosDVD + "\n"
					+ "Lucro diário: " + lucroDiario + " R$\n";
					; 
			return relatorio;
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
		}
		return relatorio;
	}
	public void alugaVeiculo(Integer id_veiculo) { // Função que seta o campo de Disponibilidade como false
		String comando_sql = "UPDATE VEICULOS SET DISPONIBILIDADE = ? WHERE IDVEICULOS = ?";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql);
			
			//Seta as variáveis para tabela
			ps.setBoolean(1, false); // Seta false
			ps.setInt(2,id_veiculo); // Seta o id 
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
		}
	}
	
	public void desalugaVeiculo(Integer id_veiculo) { // Função que seta o campo de Disponibilidade como true
		String comando_sql = "UPDATE VEICULOS SET DISPONIBILIDADE = ? WHERE IDVEICULOS = ?";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(comando_sql);
			
			//Seta as variáveis para tabela
			ps.setBoolean(1, true); // Seta true
			ps.setInt(2,id_veiculo); // Seta o id 
			
			ps.execute(); // Executa e atualiza no banco
			ps.close(); // Fecha
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
		}
	}
	
	public Double procuraCustoDario(Integer id_veiculo) {
		String comando_sql = "SELECT * FROM VEICULOS WHERE IDVEICULOS = " + id_veiculo; // Comando para procurar nos idVeiculos o id desejado
		ResultSet resultado = null;
		Statement st = null;
		try {
			Double custoDiario = -1.0;
			
			st = Conexao.getConexao().createStatement(); // tenta estabelecer a conexão
			resultado = st.executeQuery(comando_sql); // Executa o comando
			
			//Coleta as variaveis
			if(resultado.next()) {// Se tiver pego algo, então prossigo
				custoDiario = Double.parseDouble(resultado.getString("custoDiario")); // Pega o valor e converte para Double
			}
			
			return custoDiario; // retorna o custoDiario
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
			return null;
		}
	}
	
	public Boolean verificaDisponibilidade(Integer id_veiculo) { // Função que recebe um id e procura no banco de dados para saber se o veiculo está disponivel
		String comando_sql = "SELECT * FROM VEICULOS WHERE IDVEICULOS = " + id_veiculo; // Comando para procurar nos idVeiculos o id desejado
		ResultSet resultado = null;
		Statement st = null;
		try {
			Boolean disponibilidade = null;
			
			st = Conexao.getConexao().createStatement(); // tenta estabelecer a conexão
			resultado = st.executeQuery(comando_sql); // Executa o comando
			
			//Coleta as variaveis
			if(resultado.next()) {// Se tiver pego algo, então prossigo
				if(Integer.parseInt(resultado.getString("disponibilidade")) == 1) { // Pega o valor e converte para Boolean
					disponibilidade = true;
				}
				else if(Integer.parseInt(resultado.getString("disponibilidade")) == 0){
					disponibilidade = false;
				}
			}
			return disponibilidade; // retorna a disponiilidade
		}
		catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Houve um erro no banco de dados!","Erro de Banco de dados", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro em uma janela de avisos
			return null;
		}
	}
}
