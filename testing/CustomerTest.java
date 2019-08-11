package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Customer;
import business.Product;
import utilities.Address;

class CustomerTest {

	Customer customer;

	@BeforeEach
	public void setUp() {
		Address address = EasyMock.createMock(Address.class);
		customer = new Customer("Shrey", "S3710669", address, 20);
	}

	@Test
	public void getNameTest() {
		String name = customer.getName();
		assertEquals("Shrey", name);

	}

	@Test
	public void setPointsTest() {
		double points = 50;
		customer.setPoints(points);
		double swipePoints = customer.getSwipePoints();
		assertEquals(50, swipePoints);

	}

	@Test
	public void addProduct() {
		Product product = EasyMock.createMock(Product.class);
		customer.addProduct(product);
		assertTrue(customer.checkCart());
	}

	@Test
	public void clearCart() {
		customer.emptyCart();
		assertFalse(customer.checkCart());
	}

	@Test
	public void toStringTest() {
		String str = customer.toString();
		assertNotNull(str);
	}

}