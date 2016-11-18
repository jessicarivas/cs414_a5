package common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ParkingGarageTest.class, DriverTest.class, AdministratorTest.class, BankTest.class, TicketTest.class, GarageUsageTest.class})

//Tests all of the suites.
public class TestAll {
	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestAll.class);
	}

}
