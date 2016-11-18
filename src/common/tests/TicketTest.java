package common.tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import server.ParkingGarageImpl;

import org.junit.Before;
import org.junit.Test;

import common.ParkingGarage;
import common.Ticket;

public class TicketTest {

	
	private ParkingGarage garage;
	
	@Before
	public void initialize() throws RemoteException {
		garage = new ParkingGarageImpl();
	}

	@Test
	public void testTicket() {
		Ticket ticket = new Ticket(1,2);
		assertEquals(2, ticket.getHourlyCost());
		assertEquals(1, ticket.getNumber());
	}
	
	@Test
	public void testGarageTickets() throws RemoteException {
		garage.printTicket();
		assertEquals(true, garage.containsTicket("1"));
	}
	
	@Test
	public void testGarageTicketNonexistent() throws RemoteException {
		garage.printTicket();
		assertEquals(false, garage.containsTicket("4"));
	}
	
	@Test
	public void testGarageLostTicket() throws RemoteException {
		assertEquals(500, garage.getLostTicketFee());
	}

}
