import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class JanelaRelatorio extends JFrame{
	private String stringRelatorio; // Campo para receber a string do relatorio
	
	
	public JanelaRelatorio() {
		setTitle("Relatório");
		
		JPanel panelTexto = new JPanel();
		panelTexto.setBackground(new Color(209, 244, 254));
		this.setSize(500, 500);
		getContentPane().add(panelTexto, BorderLayout.CENTER);
		panelTexto.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 27, 419, 410);
		panelTexto.add(scrollPane);
		
		JTextArea areaTexto = new JTextArea();
		areaTexto.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		areaTexto.setEditable(false);
		scrollPane.setViewportView(areaTexto);
		
		stringRelatorio = new VeiculosDAO().geraRelatorio(); // Gera o relatorio
		areaTexto.setText(stringRelatorio); // Exibe o relatório
	}
}
