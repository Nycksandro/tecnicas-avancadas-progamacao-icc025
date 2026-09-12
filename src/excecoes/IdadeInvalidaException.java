package excecoes;

public class IdadeInvalidaException extends Trab01Exceptions{
	public IdadeInvalidaException() {
		super("Idade inserida inválida");
	}
	public IdadeInvalidaException(String s) {
		super(s);
	}
}
