package training.onepay.model;

public enum Status {
	NEW("NEW"), AUTHORIZED("AUTHORIZED"), CAPTURED("CAPTURED");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}