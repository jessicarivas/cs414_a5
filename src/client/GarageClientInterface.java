package client;

import java.rmi.*;

public interface GarageClientInterface 
  extends java.rmi.Remote{

    public String notifyMe(String message) 
      throws java.rmi.RemoteException;
}