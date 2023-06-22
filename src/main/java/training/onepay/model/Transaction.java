package training.onepay.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "TRANSACTION_ID", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "TRANSACTION_SUM", nullable = false)
	private BigDecimal sum;

	@Column(name = "TRANSACTION_STATUS", nullable = false)
	private Status status;

	@Column(name = "TRANSACTION_PAYMENT_MODE", nullable = false)
	private PaymentMode paymentMode;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction", cascade = { CascadeType.PERSIST })
	private List<Order> orders;

	public Transaction() {
		super();
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
