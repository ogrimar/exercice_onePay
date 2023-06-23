package training.onepay.mapper;

import java.util.ArrayList;
import java.util.List;

import training.onepay.domain.Order;
import training.onepay.domain.Transaction;
import training.onepay.model.OrderModel;
import training.onepay.model.TransactionModel;

public abstract class TransactionMapper {

	/**
	 * Transform a TransactionModel into a Transaction
	 */
	public static Transaction toModel(TransactionModel t) {
		List<Order> orders;
		if (t.getOrders() != null) {
			orders = t.getOrders().stream().map(o -> OrderMapper.toModel(o)).toList();
		} else {
			orders = new ArrayList<Order>();
		}
		return new Transaction(t.getId(), t.getSum(), t.getStatus(), t.getPaymentMode(), orders);
	}

	/**
	 * Transform a Transaction into a TransactionModel
	 */
	public static TransactionModel toDomain(Transaction t) {
		TransactionModel res = new TransactionModel();
		List<OrderModel> orders;
		if (t.getOrders() != null) {
			orders = t.getOrders().stream().map(o -> OrderMapper.toDomain(o, res)).toList();
		} else {
			orders = new ArrayList<OrderModel>();
		}
		res.setId(t.getId());
		res.setPaymentMode(t.getPaymentMode());
		res.setStatus(t.getStatus());
		res.setSum(t.getSum());
		res.setOrders(orders);
		return res;
	}
}
