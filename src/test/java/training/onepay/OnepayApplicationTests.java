package training.onepay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import training.onepay.controller.TransactionController;
import training.onepay.domain.Order;
import training.onepay.domain.Transaction;
import training.onepay.model.PaymentMode;
import training.onepay.model.Status;

@SpringBootTest
class OnepayApplicationTests {
	
	TransactionController controller;
	
	@Autowired
	public OnepayApplicationTests(TransactionController controller) {
		this.controller = controller;
	}

	@Test
	void integrationChainTest() {
		Transaction t1 = new Transaction();
		t1.setStatus(Status.NEW);
		t1.setPaymentMode(PaymentMode.CREDIT_CARD);
		t1.setSum(BigDecimal.valueOf(54.8));
		List<Order> orders1 = new ArrayList<Order>();
		orders1.add(new Order(null, "ski gloves", 4, BigDecimal.valueOf(10)));
		orders1.add(new Order(null, "wool cap", 1, BigDecimal.valueOf(14.8)));
		t1.setOrders(orders1);
		
		final Transaction t3 = controller.createTransaction(t1).getBody();
		
		t3.setStatus(Status.AUTHORIZED);
		controller.updateTransaction(t3);
		
		t3.setStatus(Status.CAPTURED);

		controller.updateTransaction(t3);
		
		Transaction t2 = new Transaction();
		t2.setStatus(Status.NEW);
		t2.setPaymentMode(PaymentMode.PAYPAL);
		t2.setSum(BigDecimal.valueOf(208));
		List<Order> orders2 = new ArrayList<Order>();
		Order order = new Order(null, "bike", 4, BigDecimal.valueOf(208));
		orders2.add(order);
		t2.setOrders(orders2);
		
		final Transaction t4 = controller.createTransaction(t2).getBody();
		
		List<Transaction> res = controller.getAll().getBody();

		assertEquals(res.size(), 2);
		Transaction resT1 = res.stream().filter(o -> t3.getId().equals(o.getId())).findFirst().get();
		Transaction resT2 = res.stream().filter(o -> t4.getId().equals(o.getId())).findFirst().get();
		assertEquals(resT1.getOrders().size(), t1.getOrders().size());
		assertEquals(resT2.getOrders().size(), t2.getOrders().size());

		assertEquals(resT1.getStatus(), t3.getStatus());
		assertEquals(resT1.getPaymentMode(), t1.getPaymentMode());
		assertEquals(resT2.getStatus(), t2.getStatus());
		assertEquals(resT2.getPaymentMode(), t2.getPaymentMode());
		assertEquals(resT1.getSum().intValue(), t1.getSum().intValue());
		assertEquals(resT2.getSum().intValue(), t2.getSum().intValue());
		
		Order oRes = resT2.getOrders().get(0);
		assertEquals(order.getAmount(), oRes.getAmount());
		assertEquals(order.getName(), oRes.getName());
		assertEquals(order.getPrice().intValue(), oRes.getPrice().intValue());
	}

}
