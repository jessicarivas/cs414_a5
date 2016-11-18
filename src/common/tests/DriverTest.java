package common.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import common.Driver;

public class DriverTest {

	@Test
	public void testDriver() {
		Driver driver = new Driver(1, 2);
		assertEquals(2, driver.getTicket().getHourlyCost());
		assertEquals(1, driver.getTicket().getNumber());
	}

}
