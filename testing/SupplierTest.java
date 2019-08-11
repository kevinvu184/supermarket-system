package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Supplier;
import utilities.Address;

class SupplierTest {

	Supplier supplier;

	@BeforeEach
	public void setUp() {
		supplier = new Supplier("Pepsico", "0999999", 3000, "North Melbounre", "VIC", "Melbounre", "Pepsi can", 20);
	}

	@Test
	void setContactNumberTest() {
		supplier.setContactNumber("0133");
		assertEquals(supplier.getContactNumber(), "0133");
	}

	@Test
	void setAddressTest() {
		supplier.setAddress(3015, "Footscray", "TAS", "Sydney");
		assertEquals(supplier.getAddress(), new Address(3015, "Footscray", "TAS", "Sydney"));
	}

	@Test
	void getNameTest() {
		assertEquals(supplier.getName(), "Pepsico");
	}

	@Test
	void toStringTest() {
		String str = supplier.toString();
		assertNotNull(str);
	}

}
