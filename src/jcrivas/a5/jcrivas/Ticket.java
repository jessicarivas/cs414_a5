package jcrivas.a5.jcrivas;

import java.util.concurrent.TimeUnit;

public class Ticket {

	private int _ticketNumber;
	private int _cost;
	private long _startTime;
	private long _endTime;

	
	public Ticket(int id, int cost) {
		_ticketNumber = id;
		_startTime = new java.util.Date().getTime();
		_endTime = 0;
		_cost = cost;
	}

	public int getNumber() {
		return _ticketNumber;
	}
	
	public long getStartTime() {
		return _startTime;
	}

	public long getEndTime() {
		return _endTime;
	}

	public int calculateCost() {
		_endTime = new java.util.Date().getTime();
		long difference = _endTime - _startTime;
		long hours = TimeUnit.MILLISECONDS.toHours(difference) + 1; //rounds up to hour
		int total = (int) (hours * _cost);
		return total;
	}

	public int getHourlyCost() {
		return _cost;
	}
}
