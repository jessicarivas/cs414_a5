package jcrivas.a5.jcrivas;

import java.util.HashSet;
import java.util.Set;

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
}
