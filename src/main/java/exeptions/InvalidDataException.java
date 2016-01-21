package exeptions;


public class InvalidDataException extends WebtasksException {
	private static final long serialVersionUID = 2025717014644106349L;

	public InvalidDataException(String message) {
		super(message);
	}

	public InvalidDataException(Throwable cause) {
		super(cause);
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
