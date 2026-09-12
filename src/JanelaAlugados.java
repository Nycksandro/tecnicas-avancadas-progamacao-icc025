import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaAlugados extends JFrame{
	private JTable tabelaCarrosAlugados;
	public JanelaAlugados() {
		setResizable(false);
		this.setSize(500, 500);
		setTitle("Carros Alugados");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 43, 397, 385);
		getContentPane().add(scrollPane);
		
		tabelaCarrosAlugados = new JTable();
		tabelaCarrosAlugados.setFont(new Font("Times New Roman", Font.BOLD, 13));
		tabelaCarrosAlugados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane.setViewportView(tabelaCarrosAlugados);
		
		JPanel panelContorno = new JPanel();
		panelContorno.setBounds(0, 0, 486, 463);
		getContentPane().add(panelContorno);
		panelContorno.setBackground(new Color(209, 244, 254));
		
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
				new AlugadosDAO().exibeTabela(tabelaCarrosAlugados);
			}
		});
		btnNewButton.setToolTipText("Botão que atualiza as tabelas");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelContorno.add(btnNewButton);
	}
	
}
