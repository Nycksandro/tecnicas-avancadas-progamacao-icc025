


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;


public class Trab_Pratico_01 {

	private JFrame frmSistemaDe;
	/**
	 * @wbp.nonvisual location=203,-6
	 */
	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("Trabalho Prático 01");
	private JTable tabelaVeiculos;
	private Integer idVeiculo = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trab_Pratico_01 window = new Trab_Pratico_01();
					window.frmSistemaDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Trab_Pratico_01() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDe = new JFrame();
		frmSistemaDe.setResizable(false);
		frmSistemaDe.setTitle("Sistema de Gestão para Locadora de Veículos - TP01");
		frmSistemaDe.setBounds(100, 100, 956, 527);
		frmSistemaDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollTabela = new JScrollPane();
		scrollTabela.setBounds(51, 47, 454, 372);
		
		JPanel painel_botoes = new JPanel();
		painel_botoes.setBounds(103, 424, 380, 66);
		
		JButton btnNewButton = new JButton("CADASTRAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setToolTipText("Cadastrar um novo veículo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaSecundariaCadastroVeiculos janela_Secundaria_Cadastro = new JanelaSecundariaCadastroVeiculos();
				janela_Secundaria_Cadastro.setVisible(true); // Abre a janela
				if(janela_Secundaria_Cadastro.isVisible() == false) { // Se a janela for fechada eu atualizo a tabela que mostra o banco
					new VeiculosDAO().exibeTabela(tabelaVeiculos);
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("REMOVER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idVeiculo != -1) { // Verifico se há uma linha selecionada
					if(new VeiculosDAO().verificaDisponibilidade(idVeiculo) == false) {
						JOptionPane.showMessageDialog(null, "O veículo selecionado está alugado no momento!", "Veículo já alugado", JOptionPane.ERROR_MESSAGE);
					}
					else { // Se ta tudo certo
						new VeiculosDAO().removerVeiculo(idVeiculo); // Remove linha selecionada atráves do ID
						JOptionPane.showMessageDialog(null, "Veículo removido com sucesso!", "Remover um veículo", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um veículo primeiro!", "Veículo não selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setToolTipText("Remover um veículo");
		
		JButton btnNewButton_2 = new JButton("ALTERAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idVeiculo != -1) { // Se uma linha estiver selecionada
					if(new VeiculosDAO().verificaDisponibilidade(idVeiculo) == false) { // Se o carro ja está alugado, não pode alterar
						JOptionPane.showMessageDialog(null, "O veículo selecionado está alugado no momento!", "Veículo já alugado", JOptionPane.ERROR_MESSAGE);
					}
					else { // Tudo certo
						JanelaSecundariaAlterarVeiculo janela_Alterar_Veiculo = new JanelaSecundariaAlterarVeiculo();
						janela_Alterar_Veiculo.idVeiculo = idVeiculo;
						janela_Alterar_Veiculo.setVisible(true); // Mostro a nova janela
					}
				}
				else { // Se não tiver com linha selecionada
					JOptionPane.showMessageDialog(null, "Selecione um veículo primeiro!", "Veículo não selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_2.setToolTipText("Altera as informações de um veículo");
		
		JButton btnNewButton_4 = new JButton("CLIENTES");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaSecundariaClientes janela_Secundaria_Clientes = new JanelaSecundariaClientes();
				janela_Secundaria_Clientes.setVisible(true); // Mostro a nova janela
			}
		});
		
		
		btnNewButton_4.setToolTipText("Menu dos clientes");
		painel_botoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		painel_botoes.add(btnNewButton);
		painel_botoes.add(btnNewButton_1);
		painel_botoes.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("ATUALIZAR");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idVeiculo = -1; // Reseto para nenhuma linha
				new VeiculosDAO().exibeTabela(tabelaVeiculos); //Atualiza a tabela
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_5.setToolTipText("Botão que atualiza a tabela de Veículos\r\n");
		painel_botoes.add(btnNewButton_5);
		
		JButton btnNewButton_3 = new JButton("RELATÓRIO");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaRelatorio janelaRelatorio = new JanelaRelatorio();
				janelaRelatorio.setVisible(true); //Mostra a janela
			}
		});
		
		JButton btnNewButton_6 = new JButton("SELECIONAR");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer linhaTabela = tabelaVeiculos.getSelectedRow(); // Pega a linha selecionada
				if(linhaTabela != -1 ) { // Se tiver selecionado alguma linha
					idVeiculo = Integer.parseInt(tabelaVeiculos.getValueAt(linhaTabela,0).toString()); // Pegando o campo idVeiculos e colocando na variavel
				}
			}
		});
		
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_6.setToolTipText("Seleciona um veículo (serve para remover ou alterar informações)");
		painel_botoes.add(btnNewButton_6);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_3.setToolTipText("Exibe um relatório sobre os veículos");
		painel_botoes.add(btnNewButton_3);
		painel_botoes.add(btnNewButton_4);
		
		JPanel panelTexto = new JPanel();
		panelTexto.setBounds(573, 47, 315, 372);
		panelTexto.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 315, 372);
		panelTexto.add(scrollPane);
		
		JTextArea txtrInformacoes = new JTextArea();
		txtrInformacoes.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtrInformacoes.setWrapStyleWord(true);
		txtrInformacoes.setLineWrap(true);
		txtrInformacoes.setText("TP01-TAP\r\nBem vindo ao Sistema de Gestão para Locadora de Veículos. \r\n\r\nEsse trabalho busca seguir todos os critérios de avaliação descrito no enuciado, implementando \r\num sistema de controle de veículos com as funcionalidades de Cadastrar, Remover e Alterar ligadas a um banco de dados. \r\nAlém disso, há uma área exclusiva para controle de Clientes, visando uma melhor organização para o Sistema.\r\n\r\nFoi implementado também uma tabela que mostra os Veículos do banco de dados e na área de cliente uma tabela com os clientes registrados e suas informações com intuito de facilitar a visualização dos dados.\r\n\r\nObs01: Para ações como \"REMOVER\" e \"ALTERAR\" é necessário que uma linha da tabela já esteja selecionada, e para isso utilize o botão \"SELECIONAR\".\r\n\r\nObs02: Para qual quer modificação nas tabelas é necessário clicar no botão \"ATUALIZAR\" para exibir a versão da tabela atualizada.\r\n\r\nObs03: Caso esteja dificil visualizar as colunas da tabela, clique com o botão direito do mouse entre duas colunas para esticar-lás.\r\n\r\nObs04: No \"Relatório\" para o calculo do lucro diário, considerei os opcionais como fora a parte, do lucro pois considero esses serviços como se fosse contratados por outra empresa.\r\n\r\nAluno: Nycksandro Lima dos Santos\r\nMatrícula: 22351228 ");
		scrollPane.setViewportView(txtrInformacoes);
		txtrInformacoes.setEditable(false);
		
		JPanel panelBorda = new JPanel();
		panelBorda.setBackground(new Color(38, 152, 172));
		scrollPane.setRowHeaderView(panelBorda);
		frmSistemaDe.getContentPane().setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelTitulo.setBounds(0, 0, 942, 37);
		panelTitulo.setBackground(new Color(189, 233, 240));
		panelTitulo.setLayout(null);
		
		JLabel labelTitulo = DefaultComponentFactory.getInstance().createTitle("Estoque de Veículos");
		labelTitulo.setBounds(171, 10, 202, 29);
		panelTitulo.add(labelTitulo);
		labelTitulo.setBackground(new Color(34, 14, 129));
		labelTitulo.setFont(new Font("Constantia", Font.PLAIN, 23));
		frmSistemaDe.getContentPane().add(panelTitulo);
		frmSistemaDe.getContentPane().add(scrollTabela);
		
		tabelaVeiculos = new JTable();
		tabelaVeiculos.setFont(new Font("Times New Roman", Font.BOLD, 13));
		tabelaVeiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaVeiculos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tabelaVeiculos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tabelaVeiculos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollTabela.setViewportView(tabelaVeiculos);
		frmSistemaDe.getContentPane().add(panelTexto);
		frmSistemaDe.getContentPane().add(painel_botoes);
		
	}
}
