package server;

import java.util.HashSet;
import java.util.Set;
import java.rmi.RemoteException;
import java.util.*;

import java.util.Map;           // Superclass of TreeMap
import java.util.TreeMap;       // A container that supports lookup of values
                                // using keys
import java.util.Iterator;      // Used to traverse the TreeMap

import common.*;
import client.*;

public class ParkingGarageImpl 
	extends 	java.rmi.server.UnicastRemoteObject
    implements	ParkingGarage
{
	private ParkingGarageView _view;
	private int _totalSpots;
	private int _totalDrivers;
	private Boolean _gateOpen;
	private int _ticketTracker;
	private int _lostTicketFee;
	private int _hourlyCost;
	private Set<Driver> _drivers;
	private Set<Administrator> _admins;
	private GarageUsage gUsage;
	private Bank _bank;
	private Vector clientList;
	public ParkingGarage client;

 public ParkingGarageImpl ()
	throws java.rmi.RemoteException {	
		 super();
	     clientList = new Vector();
		client = null;
		 _totalSpots = 0;
		_totalDrivers = 0;
		_lostTicketFee = 500;
		_hourlyCost = 1;
		_gateOpen = false;
		_ticketTracker = 0;
		_drivers = new HashSet<Driver>();
		_admins = new HashSet<Administrator>();
		addAdministrator("admin", "password");
		gUsage = new GarageUsage();
		_bank = new Bank();
 	}

	public void addAdministrator(String name, String password) 
			throws java.rmi.RemoteException {
		Boolean containsAdmin = false;
		for (Administrator admin: _admins) {
			String username = admin.getUsername();
			if ((username == name)) {
				containsAdmin = true;
				break;
			}
		}
		if (!containsAdmin) {
			Administrator admin = new Administrator(name, password);	
			_admins.add(admin);
		}
	}

	public int getTotalOccupancy() 
			throws java.rmi.RemoteException {
		return _totalSpots;		
	}

	public int getAvailability() 
			throws java.rmi.RemoteException {
		return (_totalSpots - _totalDrivers);		
	}
	
	public void addView(ParkingGarageView view) 
			throws java.rmi.RemoteException {
		  _view = view;		
	}

	public void openGate() 
			throws java.rmi.RemoteException {
		_gateOpen = true;
	}

	public void closeGate() 
			throws java.rmi.RemoteException {

		_gateOpen = false;
	}
	
	public String getGatePosition() 
			throws java.rmi.RemoteException {

		if (_gateOpen)
			return "open";
		else 
			return "closed";
	}
	
	public int printTicket() 
			throws java.rmi.RemoteException {
		addDriver();
		_ticketTracker++;
		Driver driver = new Driver(_ticketTracker, _hourlyCost);
		_drivers.add(driver);
		gUsage.addTransaction(driver.getTicket());
		return _ticketTracker;
	}

	public void addDriver() 
			throws java.rmi.RemoteException {

		if(getAvailability() > 0)
			_totalDrivers++;
		doCallbacks();
	}
	
	public void removeDriver()
			throws java.rmi.RemoteException {
		if (_totalDrivers > 0)
			_totalDrivers--;
		doCallbacks();

		
	}
	
	public void payTicket(int payment) 
			throws java.rmi.RemoteException {
		_bank.addPayment(payment);
		removeDriver();
	}

	public boolean containsTicket(String text) 
			throws java.rmi.RemoteException {
		int ticket = 0;
		try {
		    ticket = Integer.parseInt(text);
		} catch (NumberFormatException e) {
		    return false;
		}
		for (Driver driver: _drivers) {
			int id = driver.getTicketNumber();
			long endDate = driver.getTicket().getEndTime();
			if ((id == ticket) && (endDate == 0)) { //ticket has not been used yet
				return true;
			}
		}
		return false;
	}

	public int getLostTicketFee() 
			throws java.rmi.RemoteException {
		return _lostTicketFee;
	}
	
	private Driver getDriver(int ticket)
			throws java.rmi.RemoteException {
		for (Driver driver: _drivers) {
			int id = driver.getTicketNumber();
			if ((id == ticket)) {
				return driver;
			}
		}
		return null;
	}

	public int getTicketCost(int ticket)
			throws java.rmi.RemoteException {
		Driver driver = getDriver(ticket);
		int cost = driver.getTicketCost();
		return cost;
	}

	public Boolean logInAdmin(String username, String password)
			throws java.rmi.RemoteException {
		for (Administrator admin: _admins) {
			String name = admin.getUsername();
			String pwd = admin.getPassword();
			if ((name.equals(username)) && (password.equals(pwd))) {
				return true;
			}
		}
		return false;
	}

	public void setTotalOccupancy(int number)
			throws java.rmi.RemoteException {
		if (number >= _totalDrivers && number > 0) {
			_totalSpots = number;
		}
		doCallbacks();

	}

	public void setTotalDrivers(int number)
			throws java.rmi.RemoteException {
		if (_totalSpots >= number && number > 0) {
			_totalDrivers = number;
		} 
	}

	public String getUsageString(String type, String time)
			throws java.rmi.RemoteException {
		String usage = "";
		if (type == "Finance") {
			usage = gUsage.getFinanceUsage(time);
		} else if (type == "Occupancy") {
			usage = gUsage.getOccupancyUsage(time);
		}
		return usage;
		
	}
	
	public void setClient(ParkingGarage garage) {
		client = garage;
	}
	
	public ParkingGarage getClient() {
		return client;
	}
	
	public void send(int s) throws RemoteException {
		System.out.println(s);
	}
	

	  public synchronized void registerForCallback(GarageClientInterface callbackClientObject)
	    throws java.rmi.RemoteException {
	      // store the callback object into the vector
	      if (!(clientList.contains(callbackClientObject))) {
	         clientList.addElement(callbackClientObject);
	      System.out.println("Registered new client ");
	      doCallbacks();
	    } // end if
	  }  


	  public synchronized void unregisterForCallback(GarageClientInterface callbackClientObject) 
	    throws java.rmi.RemoteException {
	    if (clientList.removeElement(callbackClientObject)) {
	      System.out.println("Unregistered client ");
	    } else {
	       System.out.println(
	         "unregister: client wasn't registered.");
	    }
	  } 
	  
	  private synchronized void doCallbacks( ) throws java.rmi.RemoteException{
		    // make callback to each registered client
		    System.out.println(
		       "**************************************\n"
		        + "Callbacks initiated ---");
		    for (int i = 0; i < clientList.size(); i++){
		      System.out.println("doing "+ i +"-th callback\n");    
		      // convert the vector object to a callback object
		      GarageClientInterface nextClient = 
		        (GarageClientInterface)clientList.elementAt(i);
		      // invoke the callback method
		        nextClient.notifyMe(getAvailability());
		    }// end for
		    System.out.println("********************************\n" +
		                       "Server completed callbacks ---");
		  } // doCallbacks

	
}