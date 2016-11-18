package common;

import java.rmi.RemoteException;

public class ParkingGarageController
{

	private ParkingGarage garage;
	

	//Trivial constructor (could be automatically supplied by Java)
	public ParkingGarageController()
	{}

	//Refer to the model (used in all the button methods, to call
	//  methods in the model) 
	public void addModel(ParkingGarage g) {
		garage = g;
	}

public int printTicket() 
		throws RemoteException {
	garage.openGate();
	int ticket = garage.printTicket();
	return ticket;
}

public void payTicket(int cost) 
		throws RemoteException {
	garage.openGate();
	garage.payTicket(cost);
}

public void logInAdmin() {
	
}

public void closeGate() throws RemoteException {
	garage.closeGate();
}

public boolean getTicket(String text) 
		throws RemoteException {
	if (garage.containsTicket(text)) {
		return true;
	}  
	return false;
}

public int getLostTicketFee() 
		throws RemoteException {
	return garage.getLostTicketFee();
}

public int getTicketCost(String id) 
		throws RemoteException {
	int ticket = 0;
	try {
	    ticket = Integer.parseInt(id);
	} catch (NumberFormatException e) {
	}
	return garage.getTicketCost(ticket);
}

public Boolean logInAdmin(String username, String password) 
		throws RemoteException {
	return (garage.logInAdmin(username, password));
	// TODO Auto-generated method stub
	
}

public boolean isNumber(String text) {
	int number = 0;
	try {
	    number = Integer.parseInt(text);
	} catch (NumberFormatException e) {
		return false;
	}
	return true;
}

public void changeGarageOccupancy(String text) 
		throws RemoteException {
	int number = 0;
	try {
	    number = Integer.parseInt(text);
	} catch (NumberFormatException e) {
	}
	garage.setTotalOccupancy(number);
	
}

public void changeDriverTotal(String text) 
		throws RemoteException {
	int number = 0;
	try {
	    number = Integer.parseInt(text);
	} catch (NumberFormatException e) {
	}
	garage.setTotalDrivers(number);	
}

public String getUsageString(int usageType, int timeFrame) 
		throws RemoteException {
	String[] typeArray = {"Finance", "Occupancy"};
	String[] timeArray = {"Hour", "Day", "Week", "Month"};
	String usage = garage.getUsageString(typeArray[usageType], timeArray[timeFrame]);
	return usage;
	
}

public int getAvailability() throws RemoteException {
	return garage.getAvailability();
}

}


