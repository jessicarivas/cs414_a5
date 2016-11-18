package common.tests;

import static org.junit.Assert.*;
import server.ParkingGarageImpl;

import org.junit.Before;
import org.junit.Test;

import common.Bank;
import common.ParkingGarage;

public class BankTest {
				
	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarageImpl();
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
