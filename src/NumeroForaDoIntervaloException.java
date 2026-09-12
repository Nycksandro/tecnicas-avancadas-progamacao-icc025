
import excecoes.Trab01Exceptions;

public class NumeroForaDoIntervaloException extends Trab01Exceptions{
	private static final long serialVersionUID = 1L;

	public NumeroForaDoIntervaloException() {
		super("Número fora do intervalo");
	}
	public NumeroForaDoIntervaloException(String s) {
		super(s);
	}
}
