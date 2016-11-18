package common.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.GarageUsage;
import common.ParkingGarage;
import common.Ticket;
import server.ParkingGarageImpl;

public class GarageUsageTest {

	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarageImpl();
	}
	
	@Test
	public void testInitialValuesFinance() {
		GarageUsage usage = new GarageUsage();
		assertEquals(0, usage.getTickets().size());
	}	

	@Test
	public void testAddTicketFinance() {
		GarageUsage usage = new GarageUsage();
		Ticket ticket = new Ticket(1,2);
		usage.addTransaction(ticket);
		assertEquals(1, usage.getTickets().size());
	}
	
	@Test
	public void testAddTicketsMultipleFinance() {
		GarageUsage usage = new GarageUsage();
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
	public void testGetUsageHourFinance() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getFinanceUsage("Hour");
		assertEquals(true, string.equals("Total usage for the last Hour is $0"));
	}	
	
	@Test
	public void testGetUsageDayFinance() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getFinanceUsage("Hour");
		assertEquals(true, string.equals("Total usage for the last Hour is $0"));
	}	
	
	@Test
	public void testGetUsageWeekFinance() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getFinanceUsage("Week");
		assertEquals(true, string.equals("Total usage for the last Week is $0"));
	}	
	
	@Test
	public void testGetUsageMonthFinance() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getFinanceUsage("Month");
		assertEquals(true, string.equals("Total usage for the last Month is $0"));
	}	
	
	@Test
	public void testGetUsageWrongFinance() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getFinanceUsage("Invalid");
		assertEquals(true, string.equals("Please enter a correct timeframe."));
	}	
	
	@Test
	public void testGetUsageHourOccupancy() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getOccupancyUsage("Hour");
		assertEquals(true, string.equals("Total occupancy usage for the last Hour is 0 cars."));
	}	
	
	@Test
	public void testGetUsageDayOccupancy() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getOccupancyUsage("Hour");
		assertEquals(true, string.equals("Total occupancy usage for the last Hour is 0 cars."));
	}	
	
	@Test
	public void testGetUsageWeekOccupancy() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getOccupancyUsage("Week");
		assertEquals(true, string.equals("Total occupancy usage for the last Week is 0 cars."));
	}	
	
	@Test
	public void testGetUsageMonthOccupancy() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getOccupancyUsage("Month");
		assertEquals(true, string.equals("Total occupancy usage for the last Month is 0 cars."));
	}	
	
	@Test
	public void testGetUsageWrongOccupancy() {
		GarageUsage usage = new GarageUsage();
		String string = usage.getOccupancyUsage("Invalid");
		assertEquals(true, string.equals("Please enter a correct timeframe."));
	}	
}
