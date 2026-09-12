
import excecoes.IdadeInvalidaException;
import excecoes.NomeInvalidoException;

public class Cliente {
	private String nome;
	private Integer idade;
	private String endereco;
	private Boolean carteiraCNH;
	private Boolean carroAlugado;
	private String cpf;
	
	public Cliente(String nome, Integer idade, String endereco, Boolean carteiraCNH, String cpf, Boolean carroAlugado) throws NumeroForaDoIntervaloException, NomeInvalidoException, IdadeInvalidaException{
		if(nome == "" || nome == null | nome == " " || nome.length() < 3) throw new NomeInvalidoException();
		
		if(nome.length() > 2) {
			this.nome = nome;
		}
		
		if(idade < 18 || idade > 120) throw new IdadeInvalidaException();
		
		if(idade >= 18 && idade <= 120) {
			this.idade = idade;
		}
		
		if(endereco == "" || endereco == null || endereco.length() < 3 ) throw new NomeInvalidoException();
		
		if(endereco.length() > 2) {
			this.endereco = endereco;
		}
		
		if(cpf == "" || cpf == null || cpf.length() < 11 || cpf.length() > 11) throw new NomeInvalidoException();
		
		if(cpf.length() == 11) {
			this.cpf = cpf;
		}
		
		this.carteiraCNH = carteiraCNH;
		
		this.carroAlugado = false; // sempre recebe falso ao ser registrado
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getIdade() {
		return this.idade;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public boolean getSituacaoCarteira() {
		return this.carteiraCNH;
	}
	
	public boolean getCarroAlugado() {
		return this.carroAlugado;
	}
	
	public String getCPF() {
		return this.cpf;
	}
	
	public String toString() {
		String carteira = "";
		String carroAl = "";
		if(this.carroAlugado == false) {
			carroAl = "Não";
		}
		else {
			carroAl = "Sim";
		}
		if(this.getSituacaoCarteira() == true) {
			carteira = "Válida";
		}
		else {
			carteira = "Inválida";
		}
		return "Nome: " + this.getNome() + "\nIdade: " + this.getIdade() + "\nEndereço: " + this.getEndereco() + "Carteira CNH: " + carteira + "CPF: " + cpf + "\n" + "Carro alugado: " + carroAl + "\n";
	}
	
}
