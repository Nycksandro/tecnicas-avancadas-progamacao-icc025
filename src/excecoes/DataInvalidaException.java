package excecoes;

public class DataInvalidaException extends Trab01Exceptions{
	public DataInvalidaException() {
		super("A Data inserida é inválida");
	}
	public DataInvalidaException(String s) {
		super(s);
	}
}
