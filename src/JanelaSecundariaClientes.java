
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class JanelaSecundariaClientes extends JFrame {
	private int idCliente = -1; // Variável que guarda o id do cliente
	private JTable tabelaClientes;
	
	public JanelaSecundariaClientes(){
		setTitle("Gestão de Clientes");
		this.setResizable(false);
	    this.setSize(720, 500);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panelBotoes = new JPanel();
	    panelBotoes.setBounds(136, 363, 424, 79);
	    getContentPane().add(panelBotoes);
	    panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    JButton botaoCadastrar = new JButton("CADASTRAR");
	    botaoCadastrar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JanelaCadastroClientes janelaCadastroClientes = new JanelaCadastroClientes();
	    		janelaCadastroClientes.setVisible(true);	    	}
	    });
	    botaoCadastrar.setVerticalAlignment(SwingConstants.TOP);
	    botaoCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
	    botaoCadastrar.setToolTipText("Cadastra um cliente");
	    panelBotoes.add(botaoCadastrar);
	    
	    JButton botaoRemover = new JButton("REMOVER");
	    botaoRemover.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(idCliente != -1) { // Se tiver selecionado uma linha
	    			if(new ClientesDAO().verificaVeiculoAlugado(idCliente) == true) { // Se o cliente já alugou um veiculo, não pode remover
						JOptionPane.showMessageDialog(null, "O cliente selecionado está com um veículo alugado no momento!", "Cliente com aluguel pendente", JOptionPane.ERROR_MESSAGE);
	    			}
	    			else { // Tudo certo
	    				new ClientesDAO().removerCliente(idCliente); //Remove um cliente através do ID
	    				JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!", "Remover um cliente", JOptionPane.INFORMATION_MESSAGE);
	    			}
	    		}
	    		else { // Se não tiver selecionado uma linha apresenta um erro
	    			JOptionPane.showMessageDialog(null, "Selecione um Cliente primeiro!", "Cliente não selecionado", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	    botaoRemover.setToolTipText("Remove um cliente");
	    botaoRemover.setFont(new Font("Tahoma", Font.BOLD, 11));
	    panelBotoes.add(botaoRemover);
	    
	    JButton botaoAlterar = new JButton("ALTERAR");
	    botaoAlterar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(idCliente != -1) { // Verifico se foi selecionado alguma linha
	    			if(new ClientesDAO().verificaVeiculoAlugado(idCliente) == true){
						JOptionPane.showMessageDialog(null, "O cliente selecionado está com um veículo alugado no momento!", "Cliente com aluguel pendente", JOptionPane.ERROR_MESSAGE);
	    			}
	    			else { // Tudo certo
	    				JanelaAlterarClientes janelaAlterarClientes = new JanelaAlterarClientes();
	    				janelaAlterarClientes.idCliente = idCliente; // Passo o id para a janela
	    				janelaAlterarClientes.setVisible(true); // Mostra a janela
	    			}
	    		}
	    		else { // Apresenta um erro caso não tenha linha selecionada
	    			JOptionPane.showMessageDialog(null, "Selecione um Cliente primeiro!", "Cliente não selecionado", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	    botaoAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
	    botaoAlterar.setVerticalAlignment(SwingConstants.TOP);
	    botaoAlterar.setToolTipText("Altera informações de um cliente");
	    panelBotoes.add(botaoAlterar);
	    
	    JButton botaoAtualizar = new JButton("ATUALIZAR");
	    botaoAtualizar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		idCliente = -1; // restando para nenhuma linha
	    		new ClientesDAO().exibeTabela(tabelaClientes); // Exibe a tabela atualizada
	    	}
	    });
	    botaoAtualizar.setToolTipText("Botão que atualiza a tabela de Clientes");
	    botaoAtualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
	    panelBotoes.add(botaoAtualizar);
	    
	    JButton botaoSelecionar = new JButton("SELECIONAR");
	    botaoSelecionar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Integer linhaTabela = tabelaClientes.getSelectedRow(); // Pega a linha selecionada
				if(linhaTabela != -1 ) { // Se tiver selecionado alguma linha
					idCliente = Integer.parseInt(tabelaClientes.getValueAt(linhaTabela,0).toString()); // Pegando o campoidVeiculos e colocando na variavel
				}
	    	}
	    });
	    botaoSelecionar.setToolTipText("Seleciona um Cliente (serve para remover ou atualizar)");
	    botaoSelecionar.setFont(new Font("Tahoma", Font.BOLD, 11));
	    panelBotoes.add(botaoSelecionar);
	    
	    JButton botaoPagamento = new JButton("PAGAMENTO");
	    botaoPagamento.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JanelaAlugadosPagamento janelaPagamento = new JanelaAlugadosPagamento();
	    		janelaPagamento.setVisible(true);
	    	}
	    });
	    botaoPagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
	    botaoPagamento.setVerticalAlignment(SwingConstants.TOP);
	    botaoPagamento.setToolTipText("Fechar a conta do cliente");
	    panelBotoes.add(botaoPagamento);
	    
	    JButton botaoAlugar = new JButton("ALUGAR ");
	    botaoAlugar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JanelaAlugar janelaAlugar = new JanelaAlugar();
	    		janelaAlugar.setVisible(true); // Mostra a janela
	    	}
	    });
	    botaoAlugar.setFont(new Font("Tahoma", Font.BOLD, 11));
	    botaoAlugar.setVerticalAlignment(SwingConstants.TOP);
	    botaoAlugar.setToolTipText("Alugar um carro");
	    panelBotoes.add(botaoAlugar);
	    
	    JScrollPane scrollTabela = new JScrollPane();
	    scrollTabela.setBounds(136, 37, 424, 316);
	    getContentPane().add(scrollTabela);
	    
	    tabelaClientes = new JTable();
	    tabelaClientes.setBorder(new LineBorder(new Color(0, 0, 0)));
	    tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tabelaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tabelaClientes.setFont(new Font("Times New Roman", Font.BOLD, 11));
	    scrollTabela.setViewportView(tabelaClientes);
	    
	    JPanel panelBorda = new JPanel();
	    panelBorda.setBounds(0, 0, 706, 27);
	    panelBorda.setBackground(new Color(196, 250, 253));
	    getContentPane().add(panelBorda);
	    panelBorda.setLayout(null);
	    
	    JLabel labelTitulo = new JLabel("Gestão de Clientes");
	    labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    labelTitulo.setFont(new Font("Constantia", Font.BOLD, 23));
	    labelTitulo.setBounds(248, 0, 231, 40);
	    panelBorda.add(labelTitulo);
	}
}
