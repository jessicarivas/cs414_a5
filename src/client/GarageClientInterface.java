package client;

import java.rmi.*;

import common.ParkingGarageController;

public interface GarageClientInterface 
  extends java.rmi.Remote{

    public String notifyMe(int i) 
      throws java.rmi.RemoteException;

	public void addController(ParkingGarageController controller)
		throws java.rmi.RemoteException;
}