package client;

import java.rmi.*;
import java.rmi.server.*;
import common.*;

public class GarageClientImpl extends UnicastRemoteObject
     implements GarageClientInterface {
	
	private ParkingGarageController _controller;
  
   public GarageClientImpl() throws RemoteException {
      super( );
   }

   public String notifyMe(int number) throws RemoteException{
      String returnMessage = "Number of spots: " + number;
      System.out.println(returnMessage);
      _controller.updateView(number);
      return returnMessage;
   } 
   
   public void addController(ParkingGarageController controller) {
	   _controller = controller;
   }

}// end CallbackClientImpl class   