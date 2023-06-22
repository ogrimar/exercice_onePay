package training.onepay.model;

public enum PaymentMode {
	CREDIT_CARD("CREDIT_CARD"), GIFT_CARD("GIFT_CARD"), PAYPAL("PAYPAL");

	private String value;

	private PaymentMode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
