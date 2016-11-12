package jcrivas.a5.jcrivas;

public class Bank {
	
	private int _total;
	
	public Bank() {
		_total = 0;
	}

	public void addPayment(int payment) {
		_total += payment;
	}

	public int getTotal() {
		return _total;	
	}

}
