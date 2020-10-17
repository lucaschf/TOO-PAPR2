package tsi.too.excercise2.exception;

public class NoSuchTypeException extends IllegalArgumentException {

	private static final long serialVersionUID = -7148319296942744544L;

	public NoSuchTypeException() {
		super("No such type");
	}

	public NoSuchTypeException(String s) {
		super(s);
	}

	public NoSuchTypeException(Throwable cause) {
		super(cause);
	}

	public NoSuchTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
