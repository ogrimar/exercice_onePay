package training.onepay.errors;

public class RGException extends WSException {

	private int code;
	private String wsMessage;

	public RGException(int code, String wsMessage) {
		this.code = code;
		this.wsMessage = wsMessage;
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
