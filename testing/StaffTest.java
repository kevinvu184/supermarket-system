package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Staff;

/*
 * Class:			staffTest
 * Author:			[Nicholas Oliver] - [s3752041]
 */

public class StaffTest {

	Staff staff;

	@BeforeEach
	public void setUp() {
		staff = new Staff("Nicholas", "001", 1234);
	}

	@Test
	public void testGetName() {
		String name = staff.getName();
		assertEquals("Nicholas", name);
	}

	@Test
	public void testGetId() {
		String id = staff.getId();
		assertEquals("001", id);
	}

	@Test
	public void testGetSecurity() {
		int securityPin = staff.getSecurityPin();
		assertEquals(1234, securityPin);
	}

	@Test
	public void testToString() {
		String temp = staff.toString();
		assertNotNull(temp);
	}

}
