package training.onepay.errors;

public class TechnicalException extends WSException {

	private int code;
	private String wsMessage;

	public TechnicalException(Exception e) {
		super(e);
		this.code = 500;
		this.wsMessage = e.getMessage();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getWsMessage() {
		return wsMessage;
	}

	public void setWsMessage(String wsMessage) {
		this.wsMessage = wsMessage;
	}

}
