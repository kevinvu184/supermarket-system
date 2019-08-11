package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.Address;

class AddressTest {

	Address address;

	@BeforeEach
	public void setUp() {
		address = new Address(3000, "North Melbounre", "VIC", "Melbounre");
	}

	@Test
	void setCityTest() {
		address.setCity("NY");
		assertEquals(address.getCity(), "NY");
	}

	@Test
	void setStateTest() {
		address.setState("TAS");
		assertEquals(address.getState(), "TAS");
	}

	@Test
	void setPostCodeTest() {
		address.setPostCode(3000);
		assertEquals(address.getPostCode(), 3000);
	}

	@Test
	void setSuburbTest() {
		address.setSuburb("Southerm");
		assertEquals(address.getSuburb(), "Southerm");
	}

	@Test
	void toStringTest() {
		String str = address.toString();
		assertNotNull(str);
	}

}
