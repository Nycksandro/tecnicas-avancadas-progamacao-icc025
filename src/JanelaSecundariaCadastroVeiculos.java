
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SpinnerNumberModel;

public class JanelaSecundariaCadastroVeiculos extends JFrame {
	private JTextField fieldMediaConsumo;
	private JTextField fieldCustoDia;
	
	//Variavéis para construir um veículo
	String categoria = "Compacto"; // Padrão, para evitar erros
	private Integer num_passageiros;
	private String tipo_cambio;
	private Boolean ar_condicionado;
	private Double media_consumo;
	private Boolean air_bag;
	private Boolean freio_abs;
	private Boolean dvd;
	private Double custo_dia;
	private Double tamanho_bagageiro;
	
	
	private JTextField fieldTamanhoBagageiro;
	
	
	public JanelaSecundariaCadastroVeiculos(){
		super("Cadastro de Veículos");
	    getContentPane().setLayout(null); 
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(10, 10, 606, 451);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JPanel panel_Categoria = new JPanel();
	    panel_Categoria.setBounds(0, 0, 606, 46);
	    panel.add(panel_Categoria);
	    panel_Categoria.setLayout(null);
	    
	    JLabel label_Categoria = new JLabel("Categoria:");
	    label_Categoria.setToolTipText("Escolha uma das 6 categorias disponíveis");
	    label_Categoria.setBounds(10, 10, 79, 19);
	    label_Categoria.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_Categoria.add(label_Categoria);
	    
	    JComboBox<String> comboBox_Categoria = new JComboBox();
	    comboBox_Categoria.setBounds(99, 11, 153, 21);
	    panel_Categoria.add(comboBox_Categoria);
	    
	    //Preenchendo a comboBox
	    comboBox_Categoria.addItem("Compacto");
	    comboBox_Categoria.addItem("Standard");
	    comboBox_Categoria.addItem("Grande");
	    comboBox_Categoria.addItem("Econômico");
	    comboBox_Categoria.addItem("Premium");
	    comboBox_Categoria.addItem("Minivan");
	    
	    JPanel panel_NumPassageiros = new JPanel();
	    panel_NumPassageiros.setBounds(0, 45, 606, 45);
	    panel.add(panel_NumPassageiros);
	    panel_NumPassageiros.setLayout(null);
	    
	    JLabel labelNumPassageiros = new JLabel("Número de Passageiros:");
	    labelNumPassageiros.setToolTipText("Escolha o Número máximo de passageiros dos veículos");
	    labelNumPassageiros.setBounds(10, 10, 185, 19);
	    labelNumPassageiros.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_NumPassageiros.add(labelNumPassageiros);
	    
	    JSpinner spinner_2 = new JSpinner();
	    spinner_2.setModel(new SpinnerNumberModel(0, 0, 50, 1));
	    spinner_2.setToolTipText("No mínimo 2 passageiros");
	    spinner_2.setBounds(205, 12, 38, 20);
	    panel_NumPassageiros.add(spinner_2);
	    
	    JPanel panel_tipoCambio = new JPanel();
	    panel_tipoCambio.setBounds(0, 88, 606, 48);
	    panel.add(panel_tipoCambio);
	    panel_tipoCambio.setLayout(null);
	    
	    JLabel labelTipoCambio = new JLabel("Tipo de Câmbio:");
	    labelTipoCambio.setToolTipText("Escolha o tipo de câmbio do veículo");
	    labelTipoCambio.setBounds(10, 19, 125, 19);
	    labelTipoCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_tipoCambio.add(labelTipoCambio);
	    
	    JPanel panel_3_sub = new JPanel();
	    panel_3_sub.setBounds(138, 10, 171, 38);
	    panel_tipoCambio.add(panel_3_sub);
	    panel_3_sub.setLayout(null);
	    
	    JRadioButton rdbtnBotaoAuto = new JRadioButton("Automático");
	    rdbtnBotaoAuto.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    rdbtnBotaoAuto.setBounds(6, 11, 88, 21);
	    panel_3_sub.add(rdbtnBotaoAuto);
	    
	    JRadioButton rdbtnBotaoManual = new JRadioButton("Manual");
	    rdbtnBotaoManual.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    rdbtnBotaoManual.setBounds(113, 11, 103, 21);
	    panel_3_sub.add(rdbtnBotaoManual);
	    
	    ButtonGroup buttonGroup1 = new ButtonGroup();
	    buttonGroup1.add(rdbtnBotaoAuto);
	    buttonGroup1.add(rdbtnBotaoManual);
	    
	    JPanel panel_Arcondicionado = new JPanel();
	    panel_Arcondicionado.setBounds(0, 138, 606, 50);
	    panel.add(panel_Arcondicionado);
	    panel_Arcondicionado.setLayout(null);
	    
	    JLabel labelArcondicionado = new JLabel("Ar-condicionado:");
	    labelArcondicionado.setToolTipText("Escolha se o veículo possui ou não um ar-codicionado");
	    labelArcondicionado.setBounds(10, 21, 130, 19);
	    labelArcondicionado.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_Arcondicionado.add(labelArcondicionado);
	    
	    JPanel panel_4_sub_1 = new JPanel();
	    panel_4_sub_1.setLayout(null);
	    panel_4_sub_1.setBounds(137, 10, 174, 38);
	    panel_Arcondicionado.add(panel_4_sub_1);
	    
	    JRadioButton rdbtnSim = new JRadioButton("Sim");
	    rdbtnSim.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    rdbtnSim.setBounds(6, 11, 88, 21);
	    panel_4_sub_1.add(rdbtnSim);
	    
	    JRadioButton rdbtnNao = new JRadioButton("Não");
	    rdbtnNao.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    rdbtnNao.setBounds(113, 11, 103, 21);
	    panel_4_sub_1.add(rdbtnNao);
	    
	    ButtonGroup buttonGroup2 = new ButtonGroup();
	    buttonGroup2.add(rdbtnSim);
	    buttonGroup2.add(rdbtnNao);
	    
	    JPanel panel_mediaConsumo = new JPanel();
	    panel_mediaConsumo.setBounds(0, 189, 606, 46);
	    panel.add(panel_mediaConsumo);
	    panel_mediaConsumo.setLayout(null);
	    
	    JLabel labelMediaConsumo = new JLabel("Média de Consumo (Km/L):");
	    labelMediaConsumo.setToolTipText("Escolha a média de consumo do veículo (Km/L)");
	    labelMediaConsumo.setBounds(10, 17, 212, 19);
	    labelMediaConsumo.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_mediaConsumo.add(labelMediaConsumo);
	    
	    fieldMediaConsumo = new JTextField();
	    fieldMediaConsumo.setToolTipText("O valor inserido deve estar entre 0.1 e 100.0");
	    fieldMediaConsumo.setBounds(219, 19, 134, 19);
	    panel_mediaConsumo.add(fieldMediaConsumo);
	    fieldMediaConsumo.setColumns(10);
	    
	    JPanel panel_Acessorio = new JPanel();
	    panel_Acessorio.setBounds(0, 234, 606, 62);
	    panel.add(panel_Acessorio);
	    panel_Acessorio.setLayout(null);
	    
	    JLabel labelAcessorios = new JLabel("Acessórios:");
	    labelAcessorios.setToolTipText("Selecione os acessórios que o veículo possui (opcional)");
	    labelAcessorios.setBounds(10, 10, 87, 25);
	    labelAcessorios.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_Acessorio.add(labelAcessorios);
	    
	    JPanel panel_6_sub = new JPanel();
	    panel_6_sub.setBounds(96, 10, 201, 36);
	    panel_Acessorio.add(panel_6_sub);
	    
	    JCheckBox checkBoxAirBag = new JCheckBox("AirBag", false);
	    checkBoxAirBag.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    panel_6_sub.add(checkBoxAirBag);
	    
	    JCheckBox checkBoxFreioABS = new JCheckBox("Freio ABS", false);
	    checkBoxFreioABS.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    panel_6_sub.add(checkBoxFreioABS);
	    
	    JCheckBox checkBoxDVD = new JCheckBox("DVD", false);
	    checkBoxDVD.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    panel_6_sub.add(checkBoxDVD);
	    
	    JPanel panel_Custo = new JPanel();
	    panel_Custo.setBounds(0, 295, 606, 57);
	    panel.add(panel_Custo);
	    panel_Custo.setLayout(null);
	    
	    JLabel labelCustoDia = new JLabel("Custo (R$/dia):");
	    labelCustoDia.setToolTipText("Insira o aluguel diário do véiculo em R$");
	    labelCustoDia.setBounds(10, 10, 130, 19);
	    labelCustoDia.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_Custo.add(labelCustoDia);
	    
	    fieldCustoDia = new JTextField();
	    fieldCustoDia.setToolTipText("O valor inserido deve está entre 100.0 e 10000.0");
	    fieldCustoDia.setBounds(138, 12, 150, 19);
	    panel_Custo.add(fieldCustoDia);
	    fieldCustoDia.setColumns(10);
	    
	    JPanel panel_Botoes = new JPanel();
	    panel_Botoes.setBounds(382, 380, 224, 45);
	    panel.add(panel_Botoes);
	    
	    JButton btnNewButton = new JButton("Cadastrar");
	    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
	    btnNewButton.setBounds(121, 5, 93, 30);
	    btnNewButton.addActionListener(new ActionListener() { // Botão cadastrar
	    	String mensagem = "";
	    	NumeroForaDoIntervaloException e1 = new NumeroForaDoIntervaloException(); // Mensagem de erro para números fora do intervalo
	    	
	    	public void actionPerformed(ActionEvent e) {
				if(categoria.isEmpty() != true && num_passageiros != null && tamanho_bagageiro != null && tipo_cambio.isEmpty() != true && ar_condicionado != null && media_consumo != null && custo_dia != null) {
					if(num_passageiros <= 1 || num_passageiros > 50) {
						mensagem = "O número de passageiros está fora do intervalo!\n";
						JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Veículos" ,JOptionPane.ERROR_MESSAGE);
						//System.out.println(mensagem);
						e1.printStackTrace();
					}
					else if(tamanho_bagageiro < 10.0 || tamanho_bagageiro > 1000.0){
						mensagem = "O tamanho do bagageiro está fora do intervalo!\n";
						JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Veículos", JOptionPane.ERROR_MESSAGE);
						//System.out.println(mensagem);
						e1.printStackTrace();
					}
					else if(media_consumo <= 0.0 || media_consumo > 100.0) {
						mensagem = "A média de consumo está fora do intervalo!\n";
						JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Veículos", JOptionPane.ERROR_MESSAGE);
						//System.out.println(mensagem);
						e1.printStackTrace();
					}
					else if(custo_dia < 100.0 || custo_dia > 10000.0){
						mensagem = "O custo diário está fora do invervalo!\n";
						JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Veículos", JOptionPane.ERROR_MESSAGE);

						e1.printStackTrace();
					}
					// Verifica para não haver problemas
					if(air_bag == null) {
						air_bag = false;
					}
					if(dvd == null) {
						dvd = false;
					}
					if(freio_abs == null) {
						freio_abs = false;
					}
					else { // Tudo certo para criar o veículo
						try {
							Veiculo novoVeiculo = new Veiculo(categoria, num_passageiros, tamanho_bagageiro, tipo_cambio, ar_condicionado, media_consumo, air_bag, freio_abs, dvd, custo_dia);
							new VeiculosDAO().cadastrarVeiculo(novoVeiculo);
							mensagem = "Veículo cadastrado com sucesso!\n";
							JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Veículos", JOptionPane.INFORMATION_MESSAGE); // Deu certo
						}
						catch (NumeroForaDoIntervaloException e1){
							mensagem = "Houve um erro inesperado.";
							JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Veículos", JOptionPane.ERROR_MESSAGE); // Algum erro inesperado
							e1.printStackTrace();
						}
					}
				}
				else { // Erro de memoria(null)
					mensagem = "Houve um erro inesperado.";
					JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Veículos", JOptionPane.ERROR_MESSAGE); // Algum erro inesperado
				}
	    	}
	    });
	    
	    panel_Botoes.setLayout(null);
	    btnNewButton.setToolTipText("Finaliza o cadastro do veículo");
	    panel_Botoes.add(btnNewButton);
	    
	    JButton btnNewButton_1 = new JButton("Cancelar");
	    btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose(); // Fecha a janela
	    	}
	    });
	    btnNewButton_1.setBounds(28, 5, 87, 30);
	    btnNewButton_1.setToolTipText("Cancela o cadastro");
	    panel_Botoes.add(btnNewButton_1);
	    
	    JPanel panel_Bagageiro = new JPanel();
	    panel_Bagageiro.setBounds(0, 349, 355, 48);
	    panel.add(panel_Bagageiro);
	    panel_Bagageiro.setLayout(null);
	    
	    JLabel labelTamanhoBagageiro = new JLabel("Tamanho do Bagageiro (L):");
	    labelTamanhoBagageiro.setToolTipText("Escolha o tamanho máximo do bagageiro (em Litros)");
	    labelTamanhoBagageiro.setBounds(10, 10, 210, 19);
	    labelTamanhoBagageiro.setFont(new Font("Tahoma", Font.BOLD, 15));
	    panel_Bagageiro.add(labelTamanhoBagageiro);
	    
	    fieldTamanhoBagageiro = new JTextField();
	    fieldTamanhoBagageiro.setToolTipText("Insira um número entre 10.0 e 1000.0\r\n");
	    fieldTamanhoBagageiro.setBounds(222, 12, 128, 19);
	    panel_Bagageiro.add(fieldTamanhoBagageiro);
	    fieldTamanhoBagageiro.setColumns(10);
	    this.setResizable(false);
	    this.setSize(640, 480);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    
	    //Pegando dados para o campo da Categoria.
	    
	    comboBox_Categoria.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent evento) {
	    		if(evento.getStateChange() == ItemEvent.SELECTED) { // Verifico se foi trocado de opção
	    			categoria = (String) comboBox_Categoria.getSelectedItem(); // Pega a categoria escolhida e transformo para String
	    			////System.out.print("A categoria selecionada foi: " + categoria + "\n");
	    		}
	    	}
	    });
	    
	    // Pegando dados para o campo do Número máximo de passageiros
	    
	    spinner_2.addChangeListener(new ChangeListener() { // Se o valor for atualizado 
	    	public void stateChanged(ChangeEvent evento) {
	    		if(spinner_2.getValue() != null) { // Se for diferente de null
	    			num_passageiros = (int) spinner_2.getValue();
	    			//System.out.printf("Número passageiros: %d\n", num_passageiros);
	    		}
	    	}
	    });
	    
	    //Pegando dados para o campo do Tipo de Câmbio
	    
	    rdbtnBotaoAuto.addItemListener(new ItemListener() { // Se o valor for atualizado
	    	public void itemStateChanged(ItemEvent evento) {
	    		if(evento.getStateChange() == ItemEvent.SELECTED) { // Se for igual ao que selecionei, então é a opção Automático
	    			tipo_cambio = "Automático";
	    		}
	    		else if(evento.getStateChange() == ItemEvent.DESELECTED) { // Se não for igual, é "Manual"
	    			tipo_cambio = "Manual";
	    		}
	    		//System.out.printf("Tipo de Cambio: " + tipo_cambio + "\n");
	    	}
	    });
	    
	    rdbtnBotaoManual.addItemListener(new ItemListener() { // Se o valor for atualizado
	    	public void itemStateChanged(ItemEvent evento) {
	    		if(evento.getStateChange() == ItemEvent.SELECTED) { // Se for igual ao que selecionei, então é "Manual"
	    			tipo_cambio = "Manual";
	    		}
	    		else if(evento.getStateChange() == ItemEvent.DESELECTED) { // Se não for igual, é "Automático"
	    			tipo_cambio = "Automático";
	    		}
	    		//System.out.printf("Tipo de Cambio: " + tipo_cambio + "\n");
	    	}
	    });
	   //É necessário cobrir os dois casos para evitar que haja perdas de informações
	    
	    //Pegando dados para o campo do Ar-Condicionado
	       
	    rdbtnSim.addItemListener(new ItemListener(){ // Se o valor for atualizado
	    	public void itemStateChanged(ItemEvent evento) {
		    	if(evento.getStateChange() == ItemEvent.SELECTED) { // Se o que for igual ao que selecionei, então a opção é "Não"
		    		ar_condicionado = true;
		    	}
		    	else if(evento.getStateChange() == ItemEvent.DESELECTED) { // Se não for igual é "Sim"
		    		ar_condicionado = false;
		    	}
		    	/*
		    	if(ar_condicionado == true) {
		    		System.out.printf("Ar condiconado: Sim\n");
		    	}
		    	else if(ar_condicionado == false) {
		    		System.out.printf("Ar condiconado: Não\n");
		    	}
		    	*/
	    	}
	    });
	    
	    rdbtnNao.addItemListener(new ItemListener(){ // Se o valor for atualizado
	    	public void itemStateChanged(ItemEvent evento) {
		    	if(evento.getStateChange() == ItemEvent.SELECTED) { // Se o que for igual ao que selecionei, então a opção é "Sim"
		    		ar_condicionado = false;
		    	}
		    	else if(evento.getStateChange() == ItemEvent.DESELECTED) { // Se não for igual é "Não"
		    		ar_condicionado = true;
		    	}
		    	/*
		    	if(ar_condicionado == false) {
		    		System.out.printf("Ar condiconado: Não\n");
		    	}
		    	else if(ar_condicionado == true) {
		    		System.out.printf("Ar condiconado: Sim\n");
		    	}
		    	*/
	    	}
	    });
	    //É necessário cobrir os dois casos para evitar que haja perdas de informações
	    
	    
	    //Pegando dados para o campo da Media de Consumo
	    
	    fieldMediaConsumo.addFocusListener(new FocusListener() {// Quando estiver na MediaConsumo
	    	public void focusGained(FocusEvent evento) { // Quando estiver dentro da digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldMediaConsumo.getText();
	    		if(temp.isEmpty() == false) { // Verifica se está vazio
		    		media_consumo = Double.parseDouble(temp);
	    		//System.out.printf("Média consumo: %.2f\n", media_consumo);
	    		}
	    	}
	    });
	    
	  //Pega dados para o campo de Acessórios
	    
	    //Air Bag
	    checkBoxAirBag.addActionListener(new ActionListener(){ // Verifica se há mudança de estado
	    	public void actionPerformed(ActionEvent evento) { 
	    		if(checkBoxAirBag.isSelected() == true) { // Se tiver marcado estará com true
	    			air_bag = true;
	    		}
	    		else if(checkBoxAirBag.isSelected() == false) { // Se tiver desmarcado estará com false
	    			air_bag = false;
	    		}
	    		/*
	    		if(air_bag == true) {
	    			//System.out.print("Air bag : tem");
	    		}
	    		else if(air_bag == false) {
	    			//System.out.print("Air bag : não tem");
	    		}
	    		*/
	    	}
	    });
	    
	    //Freio ABS
	    checkBoxFreioABS.addActionListener(new ActionListener(){ // Verifica se há mudança de estado
	    	public void actionPerformed(ActionEvent evento) { 
	    		if(checkBoxFreioABS.isSelected() == true) { // Se tiver marcado estará com true
	    			freio_abs = true;
	    		}
	    		else if(checkBoxFreioABS.isSelected() == false) { // Se tiver desmarcado estará com false
	    			freio_abs = false;
	    		}
	    		/*
	    		if(freio_abs == true) {
	    			//System.out.print("Freio ABS: tem");
	    		}
	    		else if(freio_abs == false) {
	    			//System.out.print("Freio ABS: não tem");
	    		}
	    		*/
	    	}
	    });
	    
	    //DVD
	    checkBoxDVD.addActionListener(new ActionListener(){ // Verifica se há mudança de estado
	    	public void actionPerformed(ActionEvent evento) { 
	    		if(checkBoxDVD.isSelected() == true) { // Se tiver marcado estará com true
	    			dvd = true;
	    		}
	    		else if(checkBoxDVD.isSelected() == false) { // Se tiver desmarcado estará com false
	    			dvd = false;
	    		}
	    		/*
	    		if(dvd == true) {
	    			//System.out.print("DVD: tem");
	    		}
	    		else if(dvd == false) {
	    			//System.out.print("DVD: não tem");
	    		}
	    		*/
	    	}
	    });
	    
	   
	  //Pegando dados para o campo do Custo Diário
	    
	    fieldCustoDia.addFocusListener(new FocusListener() { // Quando estiver no Custo Dia
	    	public void focusGained(FocusEvent evento) { // Quando estiverna da digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldCustoDia.getText(); // Pego a variavel temporariamente
	    		if(temp.isEmpty() == false) { // Verifica se não está vazio ou NULL
		    		custo_dia = Double.parseDouble(temp);
		    		//System.out.printf("Custo diário: %.2f\n", custo_dia);
	    		}
	    	}
	    });
	    
	    // Pegando dados para o campo Tamanho do bagageiro
	    	    
	    fieldTamanhoBagageiro.addFocusListener(new FocusListener() { // Quando estiver no tamanhoBagageiro
	    	public void focusGained(FocusEvent evento) { // Quando estiver na digitação
	    		// Faz nada
	    	}
	    	public void focusLost(FocusEvent evento) { // Quando sair da digitação
	    		String temp = fieldTamanhoBagageiro.getText(); // Pego a variavel temporariamente
	    		if(temp.isEmpty() == false) { // Verifica se não está vazio ou NULL
		    		tamanho_bagageiro = Double.parseDouble(temp);
		    		//System.out.printf("Tamanho bagageiro: %.2f\n", tamanho_bagageiro);
	    		}
	    	}
	    });
	    
	}
}

