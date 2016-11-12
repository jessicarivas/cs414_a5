package jcrivas.a5.jcrivas;

public class Driver {
	
	private Ticket _ticket;
	
	public Driver(int id, int cost) {
		_ticket = new Ticket(id, cost);
	}

	public int getTicketNumber() {
		return _ticket.getNumber();
	}

	public int getTicketCost() {
		return _ticket.calculateCost();	
	}

	public Ticket getTicket() {
		return _ticket;
	}

}
