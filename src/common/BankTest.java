package common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
				
	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarage();
	}

	@Test
	public void testBankInitial() {
		Bank bank = new Bank();
		assertEquals(0, bank.getTotal());
	}

	@Test
	public void testBankAddPayment() {
		Bank bank = new Bank();
		bank.addPayment(12);
		assertEquals(12, bank.getTotal());
	}
	
	@Test
	public void testBankAddPaymentNegative() {
		Bank bank = new Bank();
		bank.addPayment(-12);
		assertEquals(-12, bank.getTotal());
	}

	@Test
	public void testBankAddPaymentMultiple() {
		Bank bank = new Bank();
		bank.addPayment(-12);
		bank.addPayment(36);
		assertEquals(24, bank.getTotal());
	}
}
