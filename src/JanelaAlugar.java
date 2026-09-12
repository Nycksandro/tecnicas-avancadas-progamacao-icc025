import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;


public class JanelaAlugar extends JFrame{
	private JTable tabelaClientes;
	private JTable tabelaVeiculos;
	
	private Integer linhaTabelaVeiculos = -1;
	private Integer linhaTabelaClientes = -1;
	
	private Integer idVeiculo = -1; 
	private Integer idCliente = -1;
	
	private Double valorGPS = 0.0;
	private Double valorAssentoCriancas = 0.0;
	private Double valorSeguroCompleto = 0.0;
	
	private Double valorTotal = 0.0;
	
	private Integer diasAlugados = 0;
	
	private Boolean gps = false;
	private Boolean assentosCriancas = false;
	private Boolean seguroCompleto = false;
	private String localRetirada = "";
	private String localDevolucao = "";
	private JTextField fieldRetirada;
	private JTextField fieldDevolucao;
	
	JanelaAlugar(){
		setResizable(false);
		this.setSize(890, 537);
		getContentPane().setLayout(null);
		
		JScrollPane scrollTabelaClientes = new JScrollPane();
		scrollTabelaClientes.setBounds(12, 44, 300, 450);
		getContentPane().add(scrollTabelaClientes);
		
		tabelaClientes = new JTable();
		tabelaClientes.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaClientes.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tabelaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollTabelaClientes.setViewportView(tabelaClientes);
		
		JPanel panelTitulos = new JPanel();
		panelTitulos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelTitulos.setBackground(new Color(205, 252, 252));
		panelTitulos.setBounds(0, 0, 876, 34);
		getContentPane().add(panelTitulos);
		panelTitulos.setLayout(null);
		
		JLabel labelVeiculos = new JLabel("Veículos");
		labelVeiculos.setBounds(452, 10, 94, 29);
		labelVeiculos.setFont(new Font("Constantia", Font.BOLD, 23));
		panelTitulos.add(labelVeiculos);
		
		JLabel labelClientes = new JLabel("Clientes");
		labelClientes.setFont(new Font("Constantia", Font.BOLD, 23));
		labelClientes.setBounds(103, 10, 118, 29);
		panelTitulos.add(labelClientes);
		
		JScrollPane scrollTabelaVeiculos = new JScrollPane();
		scrollTabelaVeiculos.setBounds(345, 44, 300, 450);
		getContentPane().add(scrollTabelaVeiculos);
		
		tabelaVeiculos = new JTable();
		tabelaVeiculos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tabelaVeiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaVeiculos.setFont(new Font("Times New Roman", Font.BOLD, 13));
		tabelaVeiculos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollTabelaVeiculos.setViewportView(tabelaVeiculos);
		
		JPanel panelOpcionais = new JPanel();
		panelOpcionais.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelOpcionais.setBackground(new Color(228, 254, 254));
		panelOpcionais.setBounds(655, 44, 206, 336);
		getContentPane().add(panelOpcionais);
		panelOpcionais.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Opcionais (Valor por dia)");
		lblNewLabel.setBounds(30, 10, 142, 18);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panelOpcionais.add(lblNewLabel);
		
		JCheckBox checkBoxGPS = new JCheckBox("GPS (+50 R$)", false);
		checkBoxGPS.setFont(new Font("Times New Roman", Font.BOLD, 13));
		checkBoxGPS.setBackground(new Color(228, 254, 254));
		checkBoxGPS.setBounds(6, 50, 111, 21);
		panelOpcionais.add(checkBoxGPS);

		
		JCheckBox checkBoxAssentos = new JCheckBox("Assentos p/ crianças  (+30R$)", false);
		checkBoxAssentos.setFont(new Font("Times New Roman", Font.BOLD, 13));
		checkBoxAssentos.setBounds(6, 85, 184, 21);
		checkBoxAssentos.setBackground(new Color(228, 254, 254));
		panelOpcionais.add(checkBoxAssentos);
		
		JCheckBox checkBoxSeguro = new JCheckBox("Seguro completo (+80R$)", false);
		checkBoxSeguro.setFont(new Font("Times New Roman", Font.BOLD, 13));
		checkBoxSeguro.setBounds(6, 124, 184, 21);
		checkBoxSeguro.setBackground(new Color(228, 254, 254));
		panelOpcionais.add(checkBoxSeguro);
		
		JSpinner spinnerDiasAlugados = new JSpinner();
		spinnerDiasAlugados.setToolTipText("Entre 1 e 1000");
		spinnerDiasAlugados.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
		spinnerDiasAlugados.setBounds(6, 161, 51, 20);
		panelOpcionais.add(spinnerDiasAlugados);
		
		JLabel labelDiasAlugados = new JLabel("Dias a ser alugado");
		labelDiasAlugados.setFont(new Font("Times New Roman", Font.BOLD, 13));
		labelDiasAlugados.setBounds(67, 161, 105, 18);
		panelOpcionais.add(labelDiasAlugados);
		
		JLabel labelTotal = new JLabel("Total da diária:");
		labelTotal.setToolTipText("Total do aluguel diário em R$");
		labelTotal.setFont(new Font("Times New Roman", Font.BOLD, 13));
		labelTotal.setBounds(6, 194, 89, 26);
		panelOpcionais.add(labelTotal);
		
		JLabel labelValorTotal = new JLabel(valorTotal.toString());
		labelValorTotal.setFont(new Font("Times New Roman", Font.BOLD, 13));
		labelValorTotal.setBounds(93, 201, 66, 13);
		panelOpcionais.add(labelValorTotal);
		
		JLabel labelLocalRetirada = new JLabel("Local de Retirada:");
		labelLocalRetirada.setFont(new Font("Times New Roman", Font.BOLD, 13));
		labelLocalRetirada.setBounds(6, 236, 105, 21);
		panelOpcionais.add(labelLocalRetirada);
		
		fieldRetirada = new JTextField();
		fieldRetirada.setBounds(16, 256, 165, 21);
		panelOpcionais.add(fieldRetirada);
		fieldRetirada.setColumns(10);
		
		JLabel labelLocalDevolucao = new JLabel("Local de Devolução:");
		labelLocalDevolucao.setToolTipText("Insira o local de devolução desejada");
		labelLocalDevolucao.setFont(new Font("Times New Roman", Font.BOLD, 13));
		labelLocalDevolucao.setBounds(6, 285, 111, 21);
		panelOpcionais.add(labelLocalDevolucao);
		
		fieldDevolucao = new JTextField();
		fieldDevolucao.setBounds(16, 305, 165, 21);
		panelOpcionais.add(fieldDevolucao);
		fieldDevolucao.setColumns(10);
		
		JPanel panelPreencher = new JPanel();
		panelPreencher.setBounds(670, 456, 206, 34);
		getContentPane().add(panelPreencher);
		panelPreencher.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelPreencher.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ALUGAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idVeiculo != -1 && idCliente != -1) { // Se as duas linhas estáo selecionados, então posso alugar
					Boolean disponibilidadeCarro = new VeiculosDAO().verificaDisponibilidade(idVeiculo); // Se um carro está disponivel retorna True, se não retorna False
					Boolean clienteJaAlugou = new ClientesDAO().verificaVeiculoAlugado(idCliente); // Se um cliente não alugou nenhum veiculo do estoque retorna False, se não retorna True
					
					if(disponibilidadeCarro == false && clienteJaAlugou == true) { // Verifico se há alguma indisponibilidade
						JOptionPane.showMessageDialog(null, "O carro selecionado já foi alugado e o cliente já possui um carro em seu nome!", "Veículo e Cliente indisponível", JOptionPane.ERROR_MESSAGE);
					}
					
					else if(disponibilidadeCarro == false) { // Verifico se há alguma indisponibilidade
						JOptionPane.showMessageDialog(null, "O carro selecionado já foi alugado!", "Veículo indisponível", JOptionPane.ERROR_MESSAGE);

					}
					else if(clienteJaAlugou == true) { // Verifico se há alguma indisponibilidade
						JOptionPane.showMessageDialog(null, "O cliente já possui um carro alugado!", "Cliente já alugou um veículo", JOptionPane.ERROR_MESSAGE);

					}
					
					else if(disponibilidadeCarro == true && clienteJaAlugou == false){ // Tudo certo
						if(localRetirada.length() < 3 || localRetirada.length() > 50) { // Verifico se há local de Retirada é valido
							JOptionPane.showMessageDialog(null, "Local de Retirada inválido!", "Erro de local de retirada", JOptionPane.ERROR_MESSAGE);
						}
						else if(localDevolucao.length() < 3 || localDevolucao.length() > 50) { // Verifico se há local de Retirada é valido
							JOptionPane.showMessageDialog(null, "Local de Devolução inválido!", "Erro de local de devolução", JOptionPane.ERROR_MESSAGE);
						}
						else {
							Pedido pedido = new AlugadosDAO().procuraClienteEVeiculo(idCliente,idVeiculo); // cria o pedido
							
							//seta os campos retirados das informações inseridas
							pedido.setGPS(gps); // seta se quer ou não GPS
							pedido.setAssentosCriancas(assentosCriancas); // seta se quer ou não assentos para crianças
							pedido.setSeguroCompleto(seguroCompleto); // seta se quer ou não seguro completo;
							pedido.setDiasAlugados(diasAlugados); // seta os dias que o carro ficará alugado
							pedido.setLocalRetirada(localRetirada); // seta o local de retirada do veículo
							pedido.setLocalDevolucao(localDevolucao); // seta o local de devolução do veículo
							
							new AlugadosDAO().colocarNaTabela(pedido); // coloca na tabela
							new VeiculosDAO().alugaVeiculo(idVeiculo); // Seta a disponibilidade do veiculo como indisponível(0)
							new ClientesDAO().alugaClientes(idCliente); // Seta o VeiculoAlugado como true
							JOptionPane.showMessageDialog(null, "Cliente alugou um veículo com sucesso!", "Aluguel de Veículos" ,JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Antes de alugar, selecione um veículo e um cliente primeiro!", "Veículo não selecionado", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setToolTipText("Aluga um veiculo selecionado para um cliente");
		panelPreencher.add(btnNewButton_1);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(655, 390, 211, 56);
		getContentPane().add(panelBotoes);
		
		JButton btnNewButton_2 = new JButton("ATUALIZAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Atualiza as tabelas
				idVeiculo = -1; // Reseto a variavel idVeiculo
				idCliente = -1; // Reseto a variavel idCliente  
				valorTotal = 0.0; // Reseto a variavel valorTotal
				localRetirada = ""; // Reseto localRetirada
				localDevolucao = ""; // Reseto localDevolucao
				
				labelValorTotal.setText(valorTotal.toString()); // atualiza a label para mostrar o valor total
				new VeiculosDAO().exibeTabela(tabelaVeiculos); // Atualiza tabela de veiculos
				new ClientesDAO().exibeTabela(tabelaClientes); // Atualiza tabela de clientes
			}
		});
		panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnNewButton_2.setToolTipText("Botão que atualiza as tabelas");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelBotoes.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("SELECIONAR");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linhaTabelaVeiculos = tabelaVeiculos.getSelectedRow(); // Pega a linha da tabela dos Veiculos
				linhaTabelaClientes = tabelaClientes.getSelectedRow(); // Pega a linha da tabela dos clientes
				if(linhaTabelaVeiculos == -1) { // Nenhum Veículo selecionado
					JOptionPane.showMessageDialog(null, "Selecione um veículo também!", "Veículo não selecionado", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(linhaTabelaClientes == -1) { // Nenhum Cliente selecionado
					JOptionPane.showMessageDialog(null, "Selecione um cliente também!", "Cliente não selecionado", JOptionPane.INFORMATION_MESSAGE);

				}
				else if(linhaTabelaVeiculos != -1 && linhaTabelaClientes != -1) { // Veículo e Cliente Selecionado
					idVeiculo = Integer.parseInt(tabelaVeiculos.getValueAt(linhaTabelaVeiculos,0).toString()); // Pegando o campo idVeiculos e colocando na variavel
					idCliente = Integer.parseInt(tabelaClientes.getValueAt(linhaTabelaClientes,0).toString()); // Pegando o campo idClientes e colocando na variavel
					valorTotal = new VeiculosDAO().procuraCustoDario(idVeiculo); // atualiza o valorTotal
					Double novoValorTotal = (valorTotal + valorGPS + valorAssentoCriancas + valorSeguroCompleto);
					labelValorTotal.setText(novoValorTotal.toString()); // Atualiza a label que mostra o valor total 
				}
			}
		});
		btnNewButton_3.setToolTipText("Seleciona elementos das tabelas");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelBotoes.add(btnNewButton_3);
		
		JButton ALUGADOS = new JButton("ALUGADOS");
		ALUGADOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaAlugados janelaAlugados = new JanelaAlugados();
				janelaAlugados.setVisible(true); // Mostra a janela
			}
		});
		ALUGADOS.setToolTipText("Botão que mostra uma tabela com os carros alugados");
		ALUGADOS.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelBotoes.add(ALUGADOS);
		
		//Pegar infomações do checkBoxs
		
		//Check box do GPS
		checkBoxGPS.addActionListener(new ActionListener(){ // Verifica se há mudança de estado
	    	public void actionPerformed(ActionEvent evento) { 
	    		if(checkBoxGPS.isSelected() == true) { // Se tiver marcado estará com true
	    			gps = true;
	    			valorGPS = 50.0;
	    		}
	    		else if(checkBoxGPS.isSelected() == false) { // Se tiver desmarcado estará com false
	    			gps = false;
	    			valorGPS = 0.0;
	    		}
	    	}
	    });
		
		//Check box dos Assentos para crianças
		checkBoxAssentos.addActionListener(new ActionListener(){ // Verifica se há mudança de estado
	    	public void actionPerformed(ActionEvent evento) { 
	    		if(checkBoxAssentos.isSelected() == true) { // Se tiver marcado estará com true
	    			assentosCriancas = true;
	    			valorAssentoCriancas = 30.0;
	    		}
	    		else if(checkBoxAssentos.isSelected() == false) { // Se tiver desmarcado estará com false
	    			assentosCriancas = false;
	    			valorAssentoCriancas = 0.0;
	    		}
	    	}
	    });
		
		//Check box do Seguro completo
		checkBoxSeguro.addActionListener(new ActionListener(){ // Verifica se há mudança de estado
	    	public void actionPerformed(ActionEvent evento) { 
	    		if(checkBoxSeguro.isSelected() == true) { // Se tiver marcado estará com true
	    			seguroCompleto = true;
	    			valorSeguroCompleto = 80.0;
	    		}
	    		else if(checkBoxSeguro.isSelected() == false) { // Se tiver desmarcado estará com false
	    			seguroCompleto = false;
	    			valorSeguroCompleto = 0.0;
	    		}
	    	}
	    });
		
		//Pega informação do spinner
		spinnerDiasAlugados.addChangeListener(new ChangeListener() { // Se o valor for atualizado 
	    	public void stateChanged(ChangeEvent evento) {
	    		if(spinnerDiasAlugados.getValue() != null) { // Se for diferente de null
	    			diasAlugados = (int) spinnerDiasAlugados.getValue();
	    		}
	    	}
	    });
		
		// Pega informação do campo fieldRetirada
		fieldRetirada.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent evento) { // Quando estiver dentro da digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldRetirada.getText(); // pega o conteudo do campo Retirada
	    		if(temp.isEmpty() == false) { // Verifica se está vazio
		    		localRetirada = temp;
	    		}
	    	}
		});
		
		// Pega informação do campo fieldDevolucao
		fieldDevolucao.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent evento) { // Quando estiver dentro da digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldDevolucao.getText(); // pega o conteudo do campo Retirada
	    		if(temp.isEmpty() == false) { // Verifica se está vazio
		    		localDevolucao = temp;
	    		}
	    	}
		});
	}
}
