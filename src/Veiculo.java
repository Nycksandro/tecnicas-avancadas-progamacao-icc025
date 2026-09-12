

public class Veiculo {
	private String categoria;
	private Integer num_max_passageiros;
	private Double tamanho_bagageiro; // Litros 
	private String tipo_cambio;
	private Boolean ar_condicionado;
	private Double media_consumo; // KM/L
	private Boolean airbag; //acessorio_1
	private Boolean freio_abs; //acessorio_2
	private Boolean dvd; //acessorio_3
	private Double custo_dia; // Reais/Dia
	private Boolean disponibilidade; // Se disponivel para alugar ou não
	
	public Veiculo(String categoria, Integer num_max_passageiros, Double tamanho_bagageiro, String tipo_cambio, Boolean ar_condicionado, Double media_consumo, Boolean airbag, Boolean freio_abs, Boolean dvd, Double custo_dia) throws NumeroForaDoIntervaloException {
		if(categoria.contentEquals("Compacto") == true || categoria.contentEquals("Standard") == true || categoria.contentEquals("Grande") == true || categoria.equals("Econômico") == true || categoria.contentEquals("Premium") == true || categoria.contentEquals("Minivan") == true) { // vai ser escolhido na caixinha
			this.categoria = categoria;
		}
		if(num_max_passageiros < 1 || num_max_passageiros > 50) throw new NumeroForaDoIntervaloException(); // Coloquei o Integerervalo de 1 até 50 pois não há veículos (que podem ser alugados) com mais do que isso
		
		if(num_max_passageiros > 0 && num_max_passageiros <=50) {
			this.num_max_passageiros = num_max_passageiros;
		}
		
		if(tamanho_bagageiro < 10.0 || tamanho_bagageiro > 1000.0) throw new NumeroForaDoIntervaloException(); 
		
		if(tamanho_bagageiro >= 10.0 && tamanho_bagageiro <= 1000.0) { // Coloquei de 10 até 1000 L por ser um valor mais realista
			this.tamanho_bagageiro = tamanho_bagageiro;
		}
		
		if(tipo_cambio == "Manual" || tipo_cambio == "Automático") { // vai ser escolhido na caixinha
			this.tipo_cambio = tipo_cambio;
		}
		this.ar_condicionado = ar_condicionado; // True ou False
		
		if(media_consumo <= 0.0 || media_consumo > 100.0) throw new NumeroForaDoIntervaloException(); 
		
		if(media_consumo > 0.0 && media_consumo <= 100.0) { // Coloquei de maior que 0 até 100 pois acho difícil um veiculo fazer mais km/l do que isso.
			this.media_consumo = media_consumo;
		}
		
		this.airbag = airbag;
		this.freio_abs = freio_abs;
		this.dvd = dvd;
		
		if(airbag == null) {
			this.airbag = false;
		}
		if(freio_abs == null) {
			this.freio_abs = false;
		}
		
		if(dvd == null) {
			this.dvd = null;
		}
		
		if(custo_dia < 100.0 || custo_dia > 10000.0) throw new NumeroForaDoIntervaloException(); 
		
		if(custo_dia >= 100.0 && custo_dia <= 10000.0) { //Coloquei entre 100 e 10000 para ficar mais realista
			this.custo_dia = custo_dia;
		}
		
		this.disponibilidade = true; // Sempre vai ser criado como disponível
	}
	
	//METODOS
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public Integer getNumMaxPassageiros() {
		return this.num_max_passageiros;
	}
	
	public Double getTamanhoBagageiro() {
		return this.tamanho_bagageiro;
	}
	
	public String getTipoCambio() {
		return this.tipo_cambio;
	}
	
	public Boolean temAr(){
		return this.ar_condicionado;
	}
	
	public Double getMediaConsumo() {
		return this.media_consumo;
	}
	
	public Boolean temAirBag() {
		return this.airbag;
	}
	
	public Boolean temFreioABS() {
		return this.freio_abs;
	}
	
	public Boolean temDVD() {
		return this.dvd;
	}
	
	public Double getCustoDiario() {
		return this.custo_dia;
	}
	
	public Boolean getDisponibilidade() {
		return this.disponibilidade;
	}
	
	public String toString() {
		String acessorio = "";
		String ar = "";
		String disp = "";
		
		if(this.disponibilidade == true) {
			disp = "Sim";
		}
		else {
			disp = "Não";
		}
		if(this.temAr() == true) {
			ar = "Sim";
		}
		else {
			ar = "Não";
		}
		if(this.temAirBag() == false && this.temFreioABS() == false && this.temDVD() == false) {
			acessorio = "Nenhum";
		}
		else if(this.temAr() == true && this.temFreioABS() == false && this.temDVD() == false) {
			acessorio = "Airbag";
		}
		else if(this.temAirBag() == false && this.temFreioABS() == true && this.temDVD() == false) {
			acessorio = "Freio ABS";
		}
		else if(this.temAirBag() == false && this.temFreioABS() == false && this.temDVD() == true) {
			acessorio = "DVD";
		}
		else if(this.temAirBag() == true && this.temFreioABS() == true && this.temDVD() == false) {
			acessorio = "Airbag e Freio ABS";
		}
		else if(this.temAirBag() == true && this.temFreioABS() == false && this.temDVD() == true) {
		acessorio = "Airbag e DVD";
		}
		else if(this.temAirBag() == false && this.temFreioABS() == true && this.temDVD() == true) {
			acessorio = "Freio ABS e DVD";
		}
		else {
			acessorio = "Airbag, Freio ABS e DVD";
		}
		return "Categoria: " + this.getCategoria() + "\n" + "Número máximo de passageiros: " + this.getNumMaxPassageiros() + "\n" + "Tamanho do Bagageiro: " + this.getTamanhoBagageiro() + " L\n" + "Tipo de Câmbio: " + this.getTipoCambio() + "\n" + "Possui Ar condicionado?: " + ar + "\nMédia de consumo: " + this.getMediaConsumo() + " Km/L\n" + "Acessórios: " + acessorio + "\nCusto diário: " + this.getCustoDiario() + " R$\nDisponiblidade: " + disp + "\n";
	}
}
