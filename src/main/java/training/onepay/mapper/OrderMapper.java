package training.onepay.mapper;

import training.onepay.domain.Order;
import training.onepay.model.OrderModel;
import training.onepay.model.TransactionModel;

public abstract class OrderMapper {

	/**
	 * Transform an OrderModel into an Order
	 */
	public static Order toModel(OrderModel o) {
		return new Order(o.getId(), o.getName(), o.getAmount(), o.getPrice());
	}

	/**
	 * Transform an Order into an OrderModel
	 */
	public static OrderModel toDomain(Order o, TransactionModel t) {
		return new OrderModel(o.getId(), o.getName(), o.getAmount(), o.getPrice(), t);
	}
}
