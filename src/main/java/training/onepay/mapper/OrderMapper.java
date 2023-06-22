package training.onepay.mapper;

import training.onepay.domain.Order;
import training.onepay.model.OrderModel;
import training.onepay.model.TransactionModel;

public abstract class OrderMapper {

	public static Order toModel(OrderModel o) {
		return new Order(o.getId(), o.getName(), o.getAmount(), o.getPrice());
	}
	
	public static OrderModel toDomain(Order o, TransactionModel t) {
		return new OrderModel(o.getId(), o.getName(), o.getAmount(), o.getPrice(), t);
	}
}
