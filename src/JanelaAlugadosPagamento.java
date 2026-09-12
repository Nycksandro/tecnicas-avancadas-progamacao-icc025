import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class JanelaAlugadosPagamento extends JFrame{
	private JTable tabelaCarrosAlugados;
	private Integer idVeiculoAlugado = -1;
	private Integer idClienteAlugado = -1;
	
	public JanelaAlugadosPagamento() {
		setResizable(false);
		this.setSize(500, 500);
		setTitle("Pagamento de aluguel");
		getContentPane().setLayout(null);
		
		JPanel panelContorno = new JPanel();
		panelContorno.setBounds(0, 0, 486, 463);
		getContentPane().add(panelContorno);
		panelContorno.setBackground(new Color(209, 244, 254));
		
		JScrollPane scrollPane = new JScrollPane();
		panelContorno.add(scrollPane);
		
		tabelaCarrosAlugados = new JTable();
		tabelaCarrosAlugados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaCarrosAlugados.setFont(new Font("Times New Roman", Font.BOLD, 13));
		tabelaCarrosAlugados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(tabelaCarrosAlugados);
		
		JButton btnNewButton_1 = new JButton("CANCELAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // fecha a janela
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelContorno.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("ATUALIZAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idVeiculoAlugado = -1;
				idClienteAlugado = -1;
				new AlugadosDAO().exibeTabela(tabelaCarrosAlugados);
			}
		});
		btnNewButton.setToolTipText("Botão que atualiza as tabelas");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelContorno.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("SELECIONAR");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer linhaTabela = tabelaCarrosAlugados.getSelectedRow(); // Pega a linha selecionada
				if(linhaTabela != -1 ) { // Se tiver selecionado alguma linha
					idVeiculoAlugado = Integer.parseInt(tabelaCarrosAlugados.getValueAt(linhaTabela,2).toString()); // Pegando o campo idVeiculos 
					idClienteAlugado = Integer.parseInt(tabelaCarrosAlugados.getValueAt(linhaTabela,1).toString()); // Pegando o campo idCliente
				}
			}
		});
		btnNewButton_3.setToolTipText("Botão para selecionar um elemento da tabela");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelContorno.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("PAGAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// falta desalugar o veiculo e desoculpar o cliente quando remover da tabela e também exibir a mensagem de sucesso!!
				if(idVeiculoAlugado != -1 && idClienteAlugado != -1) { // Quando estão selecionados 
					Double aluguelComOpcionais = new AlugadosDAO().calculaAluguel(idVeiculoAlugado); // Calcula o aluguel total com base no id do veiculo que está armazenado no banco de dados
					int resposta = JOptionPane.showConfirmDialog( // janelinha de confirmação
	                        null, "O valor do aluguel total a ser pago é de: " + aluguelComOpcionais + " reais" +
	                        "\n\nDeseja confirmar o pagamento do aluguel?", 	
	                        "Pagamento de aluguel", JOptionPane.YES_NO_OPTION, 
	                        JOptionPane.QUESTION_MESSAGE
	                );
	                if (resposta == JOptionPane.YES_OPTION) { // Verifica a resposta se foi YES
	                	new VeiculosDAO().desalugaVeiculo(idVeiculoAlugado); // Desaluga o veículo
						new ClientesDAO().desalugaClientes(idClienteAlugado); // Desaluga o cliente
						new AlugadosDAO().removeTabela(idVeiculoAlugado); // Remove o elemento da tabela
	                    JOptionPane.showMessageDialog(null, "Pagamento do aluguel realizado com sucesso!", "Pagamento de aluguel", JOptionPane.INFORMATION_MESSAGE);
	                } else if (resposta == JOptionPane.NO_OPTION) { // Escolheu NO
	                    JOptionPane.showMessageDialog(null, "Pagamento Negado!", "Pagamento de aluguel", JOptionPane.ERROR_MESSAGE);
	                }
	               
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um Aluguel antes!", "Nenhum aluguel selecionado", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setToolTipText("Realiza o pagamento do aluguel");
		panelContorno.add(btnNewButton_2);
	}
	
}
