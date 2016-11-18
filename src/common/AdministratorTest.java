package common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdministratorTest {

	private ParkingGarage garage;
	
	@Before
	public void initialize() {
		garage = new ParkingGarage();
	}
	
	@Test
	public void testGarageAdminDefault() {
		assertEquals(true, garage.logInAdmin("admin", "password"));
	}
	
	@Test
	public void testGarageAdminIncorrect() {
		assertEquals(false, garage.logInAdmin("admin", "wrong"));
	}
	
	@Test
	public void testGarageAdminIncorrectUser() {
		assertEquals(false, garage.logInAdmin("wrong", "password"));
	}
	
	@Test
	public void testGarageAdminAdd() {
		assertEquals(false, garage.logInAdmin("admin2", "password2"));
		garage.addAdministrator("admin2", "password2");
		assertEquals(true, garage.logInAdmin("admin2", "password2"));
	}
	
	@Test
	public void testGarageAdminAddDuplicate() {
		assertEquals(true, garage.logInAdmin("admin", "password"));
		garage.addAdministrator("admin", "password2");
		assertEquals(false, garage.logInAdmin("admin", "password2"));
		assertEquals(true, garage.logInAdmin("admin", "password"));
	}
	
	@Test
	public void testGarageAdminAddDuplicatePassword() {
		assertEquals(true, garage.logInAdmin("admin", "password"));
		garage.addAdministrator("admin2", "password");
		assertEquals(true, garage.logInAdmin("admin", "password"));
		assertEquals(true, garage.logInAdmin("admin2", "password"));
	}

}
