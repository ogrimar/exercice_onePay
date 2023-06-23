package training.onepay.errors;

public abstract class WSException extends RuntimeException {
	public abstract int getCode();

	public abstract String getWsMessage();

	public WSException(Throwable e) {
		super(e);
	}

	public WSException() {
	}
}
