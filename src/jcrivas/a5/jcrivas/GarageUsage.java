package jcrivas.a5.jcrivas;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GarageUsage {

	protected Set<Ticket> _tickets;
	final long HOUR_TO_MILLISECONDS = 3600000;
	final long DAY_TO_MILLISECONDS = 86400000;
	final long WEEK_TO_MILLISECONDS = 604800000;
	final long MONTH_TO_MILLISECONDS = 18144000000L;
	
	public GarageUsage() {
		_tickets = new HashSet<Ticket>();
	}
	
	public void addTransaction(Ticket ticket) {
		_tickets.add(ticket);
	}

	public Set<Ticket> getTickets() {
		return _tickets;
	}
	
	public String getFinanceUsage(String timeFrame) {
		long time = 0;
		switch(timeFrame) {
			case "Hour": time = HOUR_TO_MILLISECONDS;
			case "Day": time = DAY_TO_MILLISECONDS;
			case "Week": time = WEEK_TO_MILLISECONDS;
			case "Month": time = MONTH_TO_MILLISECONDS;
		}
		if (time == 0) return "Please enter a correct timeframe.";
		long now  = new java.util.Date().getTime();
		String usage = "Total usage for the last " + timeFrame + " is " + findTicketSales(now, now - time);
		return usage;
	}
	
	private String findTicketSales(long now, long time) {
		int total = 0;
		for (Ticket ticket : _tickets) {
			long startTime = ticket.getStartTime();
			long endTime = ticket.getEndTime();
			if (endTime != 0) {
				if (endTime > time){ 
					if (startTime > time) 
						total += ticket.getHourlyCost() * Math.ceil(TimeUnit.MILLISECONDS.toHours(endTime - startTime));
					else 
						total += ticket.getHourlyCost() * Math.ceil(TimeUnit.MILLISECONDS.toHours(endTime - time));
				}
			} else if (startTime > time) {
				total += ticket.getHourlyCost() * Math.ceil(TimeUnit.MILLISECONDS.toHours(now - startTime));	
			}
		}
		String totalString = "$" + total;
		return totalString;
	}
	
	public String getOccupancyUsage(String timeFrame) {
		long time = 0;
		switch(timeFrame) {
			case "Hour": time = HOUR_TO_MILLISECONDS;
			case "Day": time = DAY_TO_MILLISECONDS;
			case "Week": time = WEEK_TO_MILLISECONDS;
			case "Month": time = MONTH_TO_MILLISECONDS;
		}
		if (time == 0) return "Please enter a correct timeframe.";
		long now  = new java.util.Date().getTime();
		String usage = "Total occupancy usage for the last " + timeFrame + " is " + findTicketOccupancy(now, now - time);
		return usage;
	}
	
	private String findTicketOccupancy(long now, long time) {
		int total = 0;
		for (Ticket ticket : _tickets) {
			long startTime = ticket.getStartTime();
			long endTime = ticket.getEndTime();
			if (endTime != 0) {
				if (endTime > time){ 
					total ++;
				}
			} else if (startTime > time) {
				total ++;	
			}
		}
		String totalString = total + " cars.";
		return totalString;
	}
}
