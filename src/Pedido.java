
public class Pedido {
	private Integer idCliente;
	private Integer idVeiculo;
	private String nome;
	private String cpf;
	private String categoria;
	private Double custoDiario;
	private Boolean gps;
	private Boolean assentosCriancas;
	private Boolean seguroCompleto;
	private Integer diasAlugados;
	private String localRetirada;
	private String localDevolucao;
	
	public Pedido(Integer idCliente, Integer idVeiculo, String nome, String cpf, String categoria, Double custoDiario, Boolean gps, Boolean assentosCriancas, Boolean seguroCompleto, Integer diasAlugados, String localRetirada, String localDevolucao) { // Não preciso me preocupar com exceções aqui pois elas ja são filtradas nas outras etapas
		this.idCliente = idCliente;
		this.idVeiculo = idVeiculo;
		this.nome = nome;
		this.cpf = cpf;
		this.categoria = categoria;
		this.custoDiario = custoDiario;
		this.gps = gps;
		this.assentosCriancas = assentosCriancas;
		this.seguroCompleto = seguroCompleto;
		this.diasAlugados = diasAlugados;
		this.localRetirada = localRetirada;
		this.localDevolucao = localDevolucao;
	}
	
	public void setGPS(Boolean gps) {
		this.gps = gps;
	}
	
	public void setAssentosCriancas(Boolean assentosCriancas) {
		this.assentosCriancas = assentosCriancas;
	}
	
	public void setSeguroCompleto(Boolean seguroCompleto) {
		this.seguroCompleto = seguroCompleto;
	}
	
	public void setDiasAlugados(Integer diasAlugados) {
		this.diasAlugados = diasAlugados;
	}
	
	public void setLocalRetirada(String localRetirada) {
		this.localRetirada = localRetirada;
	}
	
	public void setLocalDevolucao(String localDevolucao) {
		this.localDevolucao = localDevolucao;
	}
	
	public Integer getIdCliente() {
		return this.idCliente;
	}
	
	public Integer getIdVeiculo() {
		return this.idVeiculo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCPF() {
		return this.cpf;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public Double getCustoDiario() {
		return this.custoDiario;
	}
	
	public Boolean getGPS() {
		return this.gps;
	}
	
	public Boolean getAssentosCriancas() {
		return this.assentosCriancas;
	}
	
	public Boolean getSeguroCompleto() {
		return this.seguroCompleto;
	}
	
	public Integer getDiasAlugados() {
		return this.diasAlugados;
	}

	public String getLocalRetirada() {
		return localRetirada;
	}

	public String getLocalDevolucao() {
		return localDevolucao;
	}
}
