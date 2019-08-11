package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import business.SalesStaff;

/*
 * Class:			staffTest
 * Author:			[Nicholas Oliver] - [s3752041]
 */

public class SalesStaffTest {
	SalesStaff sales;
	String name;
	String id;
	String temp;
	int securityPin;

	@BeforeEach
	public void setUp() {
		sales = new SalesStaff("Henry Defoe", "002", 1234);
	}

	@Test
	public void testGetName() {
		name = sales.getName();
		assertEquals("Henry Defoe", name);
	}

	@Test
	public void testGetId() {
		id = sales.getId();
		assertEquals("002", id);
	}

	@Test
	public void testGetSecurity() {
		securityPin = sales.getSecurityPin();
		assertEquals(1234, securityPin);
	}

	@Test
	public void testToString() {
		temp = sales.toString();
		assertNotNull(temp);
	}

}
