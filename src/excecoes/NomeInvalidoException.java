package excecoes;

public class NomeInvalidoException extends Trab01Exceptions{
	public NomeInvalidoException() {
		super("O nome inserido é inválido");
	}
	public NomeInvalidoException(String s) {
		super(s);
	}
}
