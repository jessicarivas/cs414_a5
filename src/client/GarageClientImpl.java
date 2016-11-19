package client;

import java.rmi.*;
import java.rmi.server.*;

public class GarageClientImpl extends UnicastRemoteObject
     implements GarageClientInterface {
  
   public GarageClientImpl() throws RemoteException {
      super( );
   }

   public String notifyMe(String message){
      String returnMessage = "Call back received: " + message;
      System.out.println(returnMessage);
      return returnMessage;
   }      

}// end CallbackClientImpl class   