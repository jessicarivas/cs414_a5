package common.tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import common.ParkingGarage;
import server.ParkingGarageImpl;
import server.ParkingGarageImpl;

public class AdministratorTest {

	private ParkingGarage garage;
	
	@Before
	public void initialize() throws RemoteException {
		garage = new ParkingGarageImpl();
	}
	
	@Test
	public void testGarageAdminDefault() {
		try {
			assertEquals(true, garage.logInAdmin("admin", "password"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGarageAdminIncorrect() {
		try {
			assertEquals(false, garage.logInAdmin("admin", "wrong"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGarageAdminIncorrectUser() {
		try {
			assertEquals(false, garage.logInAdmin("wrong", "password"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGarageAdminAdd() throws RemoteException {
		assertEquals(false, garage.logInAdmin("admin2", "password2"));
		garage.addAdministrator("admin2", "password2");
		assertEquals(true, garage.logInAdmin("admin2", "password2"));
	}
	
	@Test
	public void testGarageAdminAddDuplicate() throws RemoteException {
		assertEquals(true, garage.logInAdmin("admin", "password"));
		garage.addAdministrator("admin", "password2");
		assertEquals(false, garage.logInAdmin("admin", "password2"));
		assertEquals(true, garage.logInAdmin("admin", "password"));
	}
	
	@Test
	public void testGarageAdminAddDuplicatePassword() throws RemoteException {
		assertEquals(true, garage.logInAdmin("admin", "password"));
		garage.addAdministrator("admin2", "password");
		assertEquals(true, garage.logInAdmin("admin", "password"));
		assertEquals(true, garage.logInAdmin("admin2", "password"));
	}

}
