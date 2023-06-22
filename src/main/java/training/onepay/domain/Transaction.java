package training.onepay.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import training.onepay.model.PaymentMode;
import training.onepay.model.Status;

public class Transaction {
	@JsonInclude(Include.NON_NULL)
	private UUID id;

	private BigDecimal sum;

	private Status status;

	private PaymentMode paymentMode;

	private List<Order> orders;

	public Transaction() {
		super();
		orders = new ArrayList<Order>();
	}

	public Transaction(UUID id, BigDecimal sum, Status status, PaymentMode paymentMode, List<Order> orders) {
		super();
		this.id = id;
		this.sum = sum;
		this.status = status;
		this.paymentMode = paymentMode;
		this.orders = orders;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Transaction [sum=" + sum + ", status=" + status + ", paymentMode=" + paymentMode + ", orders=" + orders
				+ "]";
	}
}
