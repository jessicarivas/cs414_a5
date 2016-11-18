package common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TicketTest {

	
	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarage();
	}

	@Test
	public void testTicket() {
		Ticket ticket = new Ticket(1,2);
		assertEquals(2, ticket.getHourlyCost());
		assertEquals(1, ticket.getNumber());
	}
	
	@Test
	public void testGarageTickets() {
		garage.printTicket();
		assertEquals(true, garage.containsTicket("1"));
	}
	
	@Test
	public void testGarageTicketNonexistent() {
		garage.printTicket();
		assertEquals(false, garage.containsTicket("4"));
	}
	
	@Test
	public void testGarageLostTicket() {
		assertEquals(500, garage.getLostTicketFee());
	}

}
