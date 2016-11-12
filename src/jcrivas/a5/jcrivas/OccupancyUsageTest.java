package jcrivas.a5.jcrivas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OccupancyUsageTest {

	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarage();
	}
	
	@Test
	public void testInitialValues() {
		OccupancyUsage usage = new OccupancyUsage();
		assertEquals(0, usage.getTickets().size());
	}	

	@Test
	public void testAddTicket() {
		OccupancyUsage usage = new OccupancyUsage();
		Ticket ticket = new Ticket(1,2);
		usage.addTransaction(ticket);
		assertEquals(1, usage.getTickets().size());
	}
	
	@Test
	public void testAddTicketsMultiple() {
		OccupancyUsage usage = new OccupancyUsage();
		Ticket ticket = new Ticket(1,1);
		Ticket ticket2 = new Ticket(2,2);
		Ticket ticket3 = new Ticket(3,3);
		Ticket ticket4 = new Ticket(4,4);
		usage.addTransaction(ticket);
		usage.addTransaction(ticket2);
		usage.addTransaction(ticket3);
		usage.addTransaction(ticket4);
		assertEquals(4, usage.getTickets().size());
	}	
	
	@Test
	public void testGetUsageHour() {
		OccupancyUsage usage = new OccupancyUsage();
		String string = usage.getUsage("Hour");
		assertEquals(true, string.equals("Total occupancy usage for the last Hour is 0 cars."));
	}	
	
	@Test
	public void testGetUsageDay() {
		OccupancyUsage usage = new OccupancyUsage();
		String string = usage.getUsage("Hour");
		assertEquals(true, string.equals("Total occupancy usage for the last Hour is 0 cars."));
	}	
	
	@Test
	public void testGetUsageWeek() {
		OccupancyUsage usage = new OccupancyUsage();
		String string = usage.getUsage("Week");
		assertEquals(true, string.equals("Total occupancy usage for the last Week is 0 cars."));
	}	
	
	@Test
	public void testGetUsageMonth() {
		OccupancyUsage usage = new OccupancyUsage();
		String string = usage.getUsage("Month");
		assertEquals(true, string.equals("Total occupancy usage for the last Month is 0 cars."));
	}	
	
	@Test
	public void testGetUsageWrong() {
		OccupancyUsage usage = new OccupancyUsage();
		String string = usage.getUsage("Invalid");
		assertEquals(true, string.equals("Please enter a correct timeframe."));
	}	
}
