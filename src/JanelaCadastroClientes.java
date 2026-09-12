
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import excecoes.IdadeInvalidaException;
import excecoes.NomeInvalidoException;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class JanelaCadastroClientes extends JFrame{
	private JTextField fieldNome;
	private JTextField fieldEndereco;
	private JTextField fieldCPF;
	
	//Variáveis para pegar os dados
	private String nome;
	private Integer idade;
	private String endereco;
	private Boolean carteiraCNH;
	private String cpf;
	
	public JanelaCadastroClientes() {
		setTitle("Controle de Clientes");
		this.setResizable(false);
	    this.setSize(720, 500);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panelNome = new JPanel();
	    panelNome.setBounds(0, 0, 706, 46);
	    getContentPane().add(panelNome);
	    panelNome.setLayout(null);
	    
	    JLabel labelNome = new JLabel("Nome:");
	    labelNome.setToolTipText("Insira o nome do cliente");
	    labelNome.setBounds(10, 17, 50, 19);
	    labelNome.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panelNome.add(labelNome);
	    
	    fieldNome = new JTextField();
	    fieldNome.setToolTipText("O nome inserido deve ter pelo menos 2 letras");
	    fieldNome.setBounds(69, 19, 233, 19);
	    panelNome.add(fieldNome);
	    fieldNome.setColumns(10);
	    
	    JPanel panelIdade = new JPanel();
	    panelIdade.setBounds(0, 44, 716, 53);
	    getContentPane().add(panelIdade);
	    panelIdade.setLayout(null);
	    
	    JLabel labelIdade = new JLabel("Idade:");
	    labelIdade.setBounds(10, 22, 50, 19);
	    labelIdade.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panelIdade.add(labelIdade);
	    
	    JSpinner spinnerIdade = new JSpinner();
	    spinnerIdade.setModel(new SpinnerNumberModel(0, 0, 120, 1));
	    spinnerIdade.setToolTipText("A idade deve ser pelo menos 18 anos");
	    spinnerIdade.setBounds(70, 24, 43, 19);
	    panelIdade.add(spinnerIdade);
	    
	    JPanel panelEndereco = new JPanel();
	    panelEndereco.setBounds(0, 95, 706, 53);
	    getContentPane().add(panelEndereco);
	    panelEndereco.setLayout(null);
	    
	    JLabel lblEndereo = new JLabel("Endereço:");
	    lblEndereo.setToolTipText("Insira o endereço do cliente");
	    lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblEndereo.setBounds(10, 10, 81, 33);
	    panelEndereco.add(lblEndereo);
	    
	    fieldEndereco = new JTextField();
	    fieldEndereco.setToolTipText("Deve ter pelo menos 2 caracteres");
	    fieldEndereco.setBounds(90, 19, 281, 19);
	    panelEndereco.add(fieldEndereco);
	    fieldEndereco.setColumns(10);
	    
	    JPanel panelBotoes = new JPanel();
	    panelBotoes.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panelBotoes.setBackground(new Color(213, 241, 249));
	    panelBotoes.setBounds(0, 417, 706, 46);
	    getContentPane().add(panelBotoes);
	    panelBotoes.setLayout(null);
	    
	    JButton botaoCadastar = new JButton("CADASTRAR");
	    botaoCadastar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String mensagem = "";
	    		NumeroForaDoIntervaloException e1 = new NumeroForaDoIntervaloException(); // Mensagem de erro para números fora do intervalo
	    		NomeInvalidoException e2 = new NomeInvalidoException(); // Mensagem de erro para nomes inválidos
	    		IdadeInvalidaException e3 = new IdadeInvalidaException();
	    		
	    		if(nome != null && nome.isEmpty() != true && idade != null && endereco.isEmpty() != true && carteiraCNH != null && cpf.isEmpty() != true) { // Verifico se há erro de memória ou algo assim
	    			if(nome.length() < 3) {
	    				mensagem = "O nome inserido é inválido!\n";
	    				JOptionPane.showMessageDialog(null, mensagem, "Cadastro de clientes" ,JOptionPane.ERROR_MESSAGE);
	    				e1.printStackTrace();
	    			}
	    			else if(idade < 18 || idade > 120) {
	    				mensagem = "A idade inserida é invalida!\n";
	    				JOptionPane.showMessageDialog(null, mensagem, "Cadastro de clientes" ,JOptionPane.ERROR_MESSAGE);
	    				e3.printStackTrace();
	    			}
	    			else if(endereco.length() < 3) {
	    				mensagem = "O endereço inserido é invalido!\n";
	    				JOptionPane.showMessageDialog(null, mensagem, "Cadastro de clientes" ,JOptionPane.ERROR_MESSAGE);
	    				e1.printStackTrace();
	    			}
	    			else if(cpf.length() < 11 || cpf.length() > 11) {
	    				mensagem = "O CPF inserido é inválido!\n";
	    				JOptionPane.showMessageDialog(null, mensagem, "Cadastro de clientes" ,JOptionPane.ERROR_MESSAGE);
	    				e1.printStackTrace();
	    			}
	    			else if(carteiraCNH == false) {
	    				mensagem = "O cliente está com a CNH inválida no momento!";
	    				JOptionPane.showMessageDialog(null, mensagem, "Cadastro de clientes", JOptionPane.ERROR_MESSAGE);
	    			}
	    			else {// Tudo certo
	    				try {
	    					Cliente novoCliente = new Cliente(nome,idade,endereco,carteiraCNH,cpf,false); // no veiculoAlugado não há nada, por isso o false
	    					new ClientesDAO().cadastrarCliente(novoCliente); //Cadastro o cliente no banco de dados
	    					mensagem = "Cliente registrado com sucesso!\n";
	    					JOptionPane.showMessageDialog(null, mensagem, "Cadastro de clientes" ,JOptionPane.INFORMATION_MESSAGE);
	    				}
	    				catch (NomeInvalidoException e11){
	    					mensagem = "Houve um erro inesperado\n";
	    					JOptionPane.showMessageDialog(null, mensagem, "Cadastro de clientes" ,JOptionPane.ERROR_MESSAGE);
		    				e2.printStackTrace();
	    				} catch (NumeroForaDoIntervaloException e22) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						} catch (IdadeInvalidaException e33) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
	    			}
	    		}
	    		else { // Algum erro de mémoria (null)
					//System.out.printf("Nome: %b\nIdade: %b\nEndereço: %b\nCarteiraCNH: %b\nCPF: %b\n",nome.isEmpty() != true , idade != null , endereco.isEmpty() != true , carteiraCNH != null , cpf.isEmpty() != true);
	    			mensagem = "Houve um erro inesperado.";
					JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Clietnes", JOptionPane.ERROR_MESSAGE); // Algum erro inesperado
	    		}
	    	}
	    });
	    
	    
	    botaoCadastar.setToolTipText("Realiza o cadastro do cliente");
	    botaoCadastar.setFont(new Font("Tahoma", Font.BOLD, 11));
	    botaoCadastar.setBounds(585, 10, 111, 28);
	    panelBotoes.add(botaoCadastar);
	    
	    JButton botaoCancelar = new JButton("CANCELAR");
	    botaoCancelar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    botaoCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
	    botaoCancelar.setBounds(464, 11, 111, 27);
	    panelBotoes.add(botaoCancelar);
	    
	    JPanel panelCNH = new JPanel();
	    panelCNH.setBounds(0, 145, 706, 46);
	    getContentPane().add(panelCNH);
	    panelCNH.setLayout(null);
	    
	    JLabel labelCNH = new JLabel("CNH válida:");
	    labelCNH.setBounds(10, 10, 93, 31);
	    labelCNH.setFont(new Font("Tahoma", Font.BOLD, 15));
	    labelCNH.setToolTipText("Selecione o estado da CNH do cliente\r\n");
	    panelCNH.add(labelCNH);
	    
	    JPanel panelJRadioButton = new JPanel();
	    panelJRadioButton.setBounds(103, 10, 229, 31);
	    panelCNH.add(panelJRadioButton);
	    panelJRadioButton.setLayout(null);
	    
	    JRadioButton rdbtnValida = new JRadioButton("Válida");
	    rdbtnValida.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    rdbtnValida.setBounds(6, 5, 103, 21);
	    panelJRadioButton.add(rdbtnValida);
	    
	    JRadioButton rdbtnInvalida = new JRadioButton("Inválida");
	    rdbtnInvalida.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    rdbtnInvalida.setBounds(111, 5, 103, 21);
	    panelJRadioButton.add(rdbtnInvalida);
	    
	    JPanel panelCPF = new JPanel();
	    panelCPF.setBounds(0, 193, 706, 46);
	    getContentPane().add(panelCPF);
	    panelCPF.setLayout(null);
	    
	    JLabel labelCPF = new JLabel("CPF:");
	    labelCPF.setToolTipText("Insira o CPF do cliente");
	    labelCPF.setFont(new Font("Tahoma", Font.BOLD, 15));
	    labelCPF.setBounds(10, 10, 42, 31);
	    panelCPF.add(labelCPF);
	    
	    fieldCPF = new JTextField();
	    fieldCPF.setToolTipText("O cpf não é separado por hífen e deve ter 11 dígitos continuos");
	    fieldCPF.setBounds(50, 18, 211, 19);
	    panelCPF.add(fieldCPF);
	    fieldCPF.setColumns(10);
	
	  // Pegar dados do campo Nome
	    
	    fieldNome.addFocusListener(new FocusListener() {// Quando estiver no Nome
	    	public void focusGained(FocusEvent evento) { // Quando estiver dentro da digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldNome.getText();
	    		if(temp.isEmpty() == false) { // Verifica se está vazio
		    		nome = temp;
	    		}
	    	}
	    });
	    
	    
	  // Pegar dados para o campo de Idade
	    
	    spinnerIdade.addChangeListener(new ChangeListener() { // Se o valor for atualizado 
	    	public void stateChanged(ChangeEvent evento) {
	    		if(spinnerIdade.getValue() != null) { // Se for diferente de null
	    			idade = (int) spinnerIdade.getValue(); // pega o objeto convertendo para int
	    		}
	    	}
	    });
	    
	    
	  // Pegar dados do campo Endereço
	    
	    fieldEndereco.addFocusListener(new FocusListener() {// Quando estiver no Endereço
	    	public void focusGained(FocusEvent evento) { // Quando estiver dentro da digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldEndereco.getText();
	    		if(temp.isEmpty() == false) { // Verifica se está vazio
		    		endereco = temp;
	    		}
	    	}
	    });
	    
	    
	  // Pegar dados do campo CNH válida
	    
	   ButtonGroup grupobotoes = new ButtonGroup();
	   grupobotoes.add(rdbtnValida);
	   grupobotoes.add(rdbtnInvalida);
	   
	   rdbtnValida.addItemListener(new ItemListener() { // Se o valor for atualizado
	    	public void itemStateChanged(ItemEvent evento) {
	    		if(evento.getStateChange() == ItemEvent.SELECTED) { // Se for igual ao que selecionei, então é a Valida
	    			carteiraCNH = true;
	    		}
	    		else if(evento.getStateChange() == ItemEvent.DESELECTED) { // Se não for igual, é Invalida
	    			carteiraCNH = false;
	    		}
	    	}
	    });
	    
	    rdbtnInvalida.addItemListener(new ItemListener() { // Se o valor for atualizado
	    	public void itemStateChanged(ItemEvent evento) {
	    		if(evento.getStateChange() == ItemEvent.SELECTED) { // Se for igual ao que selecionei, então é invalida
	    			carteiraCNH = false;
	    		}
	    		else if(evento.getStateChange() == ItemEvent.DESELECTED) { // Se não for igual, é valida
	    			carteiraCNH = true;
	    		}
	    	}
	    });
	   //É necessário cobrir os dois casos para evitar que haja perdas de informações
	   
	  
	  // Pegar dados do campo CPF
	    
	    fieldCPF.addFocusListener(new FocusListener() {// Quando estiver no CPF
	    	public void focusGained(FocusEvent evento) { // Quando estiver dentro da digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldCPF.getText();
	    		if(temp.isEmpty() == false) { // Verifica se está vazio
		    		cpf = temp;
	    		}
	    	}
	    }); 
	    
	}
	
}
