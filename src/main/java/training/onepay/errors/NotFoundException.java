package training.onepay.errors;

public class NotFoundException extends WSException {

	private int code = 204;
	private String wsMessage = "No content was found";

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
