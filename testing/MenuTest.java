package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import application.Supermarket;
import business.Customer;
import business.Staff;
import business.WarehouseStaff;
import utilities.Address;

class MenuTest {
	Supermarket supermarket;

	@Before
	public void setUp() {
		supermarket = new Supermarket();
		supermarket.seedData();
	}

	@Test
	void testCorrectStaffLogin() {
		setUp();
		String loginStatus = supermarket.staffLogin("KEVIN", 1);
		assertEquals(loginStatus, "\tWelcome Kevin Vu Access Rights: [class business.WarehouseStaff]\n");
		Staff kevin = new WarehouseStaff("Kevin Vu", "KEVIN", 1);
		assertEquals(supermarket.getStaff(), kevin);
	}

	@Test
	void testInvalidStaffLogin() {
		setUp();
		String loginStatus = supermarket.staffLogin("KEVIN", 2);
		assertEquals(loginStatus, "\tInvalid username/password\n");
		assertEquals(supermarket.getStaff(), null);
	}

	@Test
	void testValidCustomerLogin() {
		setUp();
		String loginStatus = supermarket.customerLogin("AAAA");
		assertEquals(loginStatus, "\tWelcome back Milly !");
		Customer Milly = new Customer("Milly", "AAAA", new Address(3161, "Caufield", "VIC", "Melbourne"), 100);
		assertEquals(supermarket.getCustomer(), Milly);
	}

	@Test
	void testInvalidCustomerLogin() {
		setUp();
		String loginStatus = supermarket.customerLogin("AAAC");
		assertEquals(loginStatus, "\tNon-existent ID number");
		assertEquals(supermarket.getCustomer(), null);
	}

	@Test
	void testLogout() {
		setUp();
		String loginStatus = supermarket.customerLogin("AAAC");
		assertEquals(loginStatus, "\tNon-existent ID number");
		supermarket.staffLogout();
		assertEquals(supermarket.getStaff(), null);
	}

	@Test
	void testDoubleLogin() {
		setUp();
		supermarket.staffLogin("CHARLES", 1);
		String loginStatus = supermarket.staffLogin("KEVIN", 1);
		assertEquals(loginStatus, "\tWelcome Kevin Vu Access Rights: [class business.WarehouseStaff]\n");
	}
}
