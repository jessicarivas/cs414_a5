package client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import common.ParkingGarage;
import common.ParkingGarageController;
import common.ParkingGarageView;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 

//imports for GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;


public class GarageClient extends JFrame 
{
private ParkingGarage _garage; 
private ParkingGarageController controller;
private ParkingGarageView view;

public GarageClient (ParkingGarage garage, GarageClientInterface clientObj) {
	super ("Garage Interface");
	
	// set up data
	_garage = garage;
	
	 controller = new ParkingGarageController();
	 view = new ParkingGarageView();
	 
	  //Notify each component of the other components it needs
	  controller.addModel(_garage);
	  controller.addView(view);
	  view.addModel(_garage);
	  view.addController(controller);
	  try {
		clientObj.addController(controller);
	} catch (RemoteException e1) {
		e1.printStackTrace();
	}
	  
	  //Build the application, then show it on the screen
	  try {
		view.build();
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	view.show();

}

public ParkingGarageController getController() {
	return controller;
}

// run the program using
// java CalculatorClient <host> <port>
// where the host and port refer to the rmiregistry's host and port
   public static void main(String[] args) {
      try {
           ParkingGarage garage = (ParkingGarage) 
                    Naming.lookup("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageServer");
       System.out.println( "connected! yay" );
       
       GarageClientInterface clientObj = 
   	        new GarageClientImpl();
       GarageClient application = new GarageClient(garage, clientObj); 
       

       garage.registerForCallback(clientObj);

//         h.unregisterForCallback(callbackObj);
           
      }

      catch (MalformedURLException murle) {
             System.out.println("MalformedURLException");
             System.out.println(murle);
         } catch (RemoteException re) {
             System.out.println("RemoteException"); 
             System.out.println(re);
         } catch (NotBoundException nbe) {
             System.out.println("NotBoundException");
             System.out.println(nbe);
         } catch (java.lang.ArithmeticException ae) {
              System.out.println("java.lang.ArithmeticException");
              System.out.println(ae);
         }
   }
}
