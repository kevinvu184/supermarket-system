package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Product;
import business.Transaction;
import utilities.DateTime;

class TransactionTest {

	Transaction transaction;
	ArrayList<Product> prodList;

	@BeforeEach
	public void setUp() {
		prodList = new ArrayList<Product>();
		prodList.add(new Product("Pepsi", 1.6, 20, 40, 10, 8));
		prodList.add(new Product("Coca-Cola", 1.8, 20, 40, 10, 8));
		prodList.add(new Product("Mountain Dew", 1.4, 20, 40, 10, 8));
		transaction = new Transaction(new DateTime(18, 5, 2019), prodList, "AAAA");
	}

	@Test
	void testCalculatePayment() {
		transaction.calculatePayment(5);
		assertEquals(transaction.getPaymentDue(), 4.800000000000001);
	}

	@Test
	void testUpdatePoint() {
		transaction.calculatePayment(5);
		assertEquals(transaction.getPaymentDue(), 4.800000000000001);
		transaction.updatePoints();
		assertEquals(transaction.getPoints(), 5.0);
	}
}
