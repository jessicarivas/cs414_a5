package common.tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import server.ParkingGarageImpl;

import org.junit.Before;
import org.junit.Test;

import common.ParkingGarage;

public class ParkingGarageTest {
	
	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarageImpl();
	}

	@Test
	public void testGarageDefaults() throws RemoteException {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		assertEquals(0, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}

	@Test
	public void testGarageOccupancy() throws RemoteException {
		garage.setTotalOccupancy(14);
		assertEquals(14, garage.getTotalOccupancy());
	}

	@Test
	public void testGarageOccupancyNegative() throws RemoteException {
		garage.setTotalOccupancy(-14);
		assertEquals(0, garage.getTotalOccupancy());
	}
	
	@Test
	public void testGarageAvailability() throws RemoteException {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(2);
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(12, garage.getAvailability());
	}

	@Test
	public void testGarageAvailabilityNoOccupancy() throws RemoteException {
		garage.setTotalDrivers(2);
		assertEquals(0, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}
	
	@Test
	public void testGarageAvailabilityNegative() throws RemoteException {
		garage.setTotalDrivers(-2);
		assertEquals(0, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}
	
	@Test
	public void testGarageGateOpen() throws RemoteException {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
	}
	
	@Test
	public void testGarageGateClose() throws RemoteException {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
		garage.closeGate();
		assertEquals(true, garage.getGatePosition().equals("closed"));
	}
	
	@Test
	public void testGarageGateOpenDuplicate() throws RemoteException {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
		garage.openGate();
		assertEquals(true, garage.getGatePosition().equals("open"));
	}
	
	@Test
	public void testGarageGateCloseDuplicate() throws RemoteException {
		assertEquals(true, garage.getGatePosition().equals("closed"));
		garage.closeGate();
		assertEquals(true, garage.getGatePosition().equals("closed"));
	}
	
	@Test
	public void testGarageAddDriver() throws RemoteException {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(2);
		garage.addDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(11, garage.getAvailability());
	}
	
	@Test
	public void testGarageAddDriverZero() throws RemoteException {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(14);
		garage.addDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(0, garage.getAvailability());
	}
	
	@Test
	public void testGarageRemoveDriver() throws RemoteException {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(14);
		garage.removeDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(1, garage.getAvailability());
	}
	
	@Test
	public void testGarageRemoveDriverZero() throws RemoteException {
		garage.setTotalOccupancy(14);
		garage.setTotalDrivers(0);
		garage.removeDriver();
		assertEquals(14, garage.getTotalOccupancy());
		assertEquals(14, garage.getAvailability());
	}


}
