package jcrivas.a5.jcrivas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParkingGarageTest {
	
	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarage();
	}

	@Test
	public void testGarageDefaults() {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		assertEquals(0, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}

	@Test
	public void testGarageOccupancy() {
		garage.setTotalOccupancy(14);
		assertEquals(14, garage.getTotalOccupancy());
	}

	@Test
	public void testGarageOccupancyNegative() {
		garage.setTotalOccupancy(-14);
		assertEquals(0, garage.getTotalOccupancy());
	}
	
	@Test
	public void testGarageAvailability() {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(2);
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(12, garage.getAvailability());
	}

	@Test
	public void testGarageAvailabilityNoOccupancy() {
		garage.setTotalDrivers(2);
		assertEquals(0, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}
	
	@Test
	public void testGarageAvailabilityNegative() {
		garage.setTotalDrivers(-2);
		assertEquals(0, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}
	
	@Test
	public void testGarageGateOpen() {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
	}
	
	@Test
	public void testGarageGateClose() {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
		garage.closeGate();
		assertEquals(true, garage.getGatePosition().equals("closed"));
	}
	
	@Test
	public void testGarageGateOpenDuplicate() {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
	}
	
	@Test
	public void testGarageGateCloseDuplicate() {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.closeGate();
		assertEquals(true, garage.getGatePosition().equals("closed"));
	}
	
	@Test
	public void testGarageAddDriver() {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(2);
		garage.addDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(11, garage.getAvailability());
	}
	
	@Test
	public void testGarageAddDriverZero() {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(14);
		garage.addDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}
	
	@Test
	public void testGarageRemoveDriver() {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(14);
		garage.removeDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(1, garage.getAvailability());
	}
	
	@Test
	public void testGarageRemoveDriverZero() {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(0);
		garage.removeDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(14, garage.getAvailability());
	}


}
