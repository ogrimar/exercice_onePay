package training.onepay.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_ORDER")
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ORDER_ID", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "ORDER_NAME", nullable = false)
	private String name;

	@Column(name = "ORDER_AMOUNT", nullable = false)
	private int amount;

	@Column(name = "ORDER_PRICE", nullable = false)
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "ORDER_TRANSACTION_ID")
	private TransactionModel transaction;

	public OrderModel() {
		super();
	}

	public OrderModel(UUID id, String name, int amount, BigDecimal price, TransactionModel transaction) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.transaction = transaction;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public TransactionModel getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionModel transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", amount=" + amount + ", price=" + price + ", transaction="
				+ transaction + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OrderModel)) {
			return false;
		}
		OrderModel other = (OrderModel) obj;
		if (amount != other.amount) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (price.intValue() != other.price.intValue()) {
			return false;
		}
		return true;
	}
}
