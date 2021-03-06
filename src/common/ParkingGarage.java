package common;

import java.util.HashSet;
import java.util.Set;

import java.rmi.*;
import client.*;

public interface ParkingGarage extends Remote {
	
	public void addAdministrator(String name, String password) 
			throws RemoteException;

	public int getTotalOccupancy() 
			throws RemoteException;


	public int getAvailability() 	
			throws RemoteException;

	public void addView(ParkingGarageView view) 
			throws RemoteException;

	public void openGate()
			throws RemoteException;


	public void closeGate()
			throws RemoteException;

	public String getGatePosition() 
			throws RemoteException;

	
	public int printTicket()
			throws RemoteException;


	public void addDriver()
			throws RemoteException;

	public void removeDriver()
			throws RemoteException;
	
	public void payTicket(int payment)
			throws RemoteException;

	public boolean containsTicket(String text)
			throws RemoteException;


	public int getLostTicketFee()	
			throws RemoteException;

		public int getTicketCost(int ticket)
			throws RemoteException;


	public Boolean logInAdmin(String username, String password)
			throws RemoteException;


	public void setTotalOccupancy(int number)
			throws RemoteException;

	public void setTotalDrivers(int number) 	
			throws RemoteException;

	public String getUsageString(String type, String time)
			throws RemoteException;
	
	public void send(int availability) 
			throws RemoteException;
	
	public void setClient(ParkingGarage garage)
			throws RemoteException;
	
	public ParkingGarage getClient()
			throws RemoteException;
	
	public void registerForCallback(GarageClientInterface clientObject) 
			throws java.rmi.RemoteException;

		// This remote method allows an object client to 
		// cancel its registration for callback

	public void unregisterForCallback(GarageClientInterface clientObject)
			throws java.rmi.RemoteException;

}