package testing;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Account;
import business.Transaction;

public class AccountTest {
	Account account;

	@BeforeEach
	public void setUp() {
		Transaction transaction = EasyMock.createMock(Transaction.class);
		account = new Account(500.0, 50.0, transaction);
	}

	@Test
	public void getProfitTest() {
		Double profit = account.getProfit();
		assertEquals(50, profit.doubleValue());
	}

	@Test
	public void setRevenueTest() {
		double revenue = 500;
		account.setRevenue(revenue);
		double test = account.getRevenue();
		assertEquals(500, test);
	}

	@Test
	public void setProfitTest() {
		double profit = 50;
		account.setProfit(profit);
		double test = account.getProfit();
		assertEquals(50, profit);
	}
}
