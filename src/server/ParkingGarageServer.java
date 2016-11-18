package server;

import common.ParkingGarage;


import java.rmi.Naming;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

// imports for GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class ParkingGarageServer extends JFrame implements ActionListener
{
 private ParkingGarageImpl dc;
 private JButton saveButton;
 private JButton exitButton;

 public ParkingGarageServer (ParkingGarageImpl parkingGarageParm)
  {
   super ("Parking Garage Server Interface");

   dc = parkingGarageParm;

   FlowLayout layout = new FlowLayout();

   // get content pane and set its layout
   Container container = getContentPane();
   container.setLayout( layout );  
   

   setSize( 700, 500 );
   setVisible( true );

  }

 // handle button events
 public void actionPerformed( ActionEvent event )
  {
   if (event.getSource() == saveButton)
      doSaveData ();

   else if (event.getSource() == exitButton)
      doShutDownServer();
  }

 public void doSaveData()
  {
   try
   {

   String s = (String)JOptionPane.showInputDialog(
                    this,
                    "Enter Filename to Save Bidder Data:\n",
                    "Save Bidder Data",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "bidder.csv");

   if (s != null)
//	   parkingGarage.writeBidders(s);

   s = (String)JOptionPane.showInputDialog(
                    this,
                    "Enter Filename to Save Item Data:\n",
                    "Save Item Data",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "item.csv");

//   if (s != null)
//	   parkingGarage.writeItems(s);
   }
   catch (Exception e)
   {
    e.printStackTrace();
   } 
  }   

 public void doShutDownServer()
  {
      System.exit( 0 );
  }

 public static void main(String args[]) 
  {
   String portNum, registryURL;

   try{     
      
      int RMIPortNum = 20002;
      startRegistry(RMIPortNum);

      // set up the database
      ParkingGarageImpl parkingGarage = new ParkingGarageImpl();

      registryURL = 
        "rmi://localhost:" + RMIPortNum + "/ParkingGarageServer";

      Naming.rebind(registryURL, parkingGarage);
      System.out.println("Garage Server ready.");

      ParkingGarageServer application = new ParkingGarageServer(parkingGarage);
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }// end try

    catch (Exception re) {
      System.out.println(
        "Exception in ParkingGarageServer.main: " + re);
    } // end catch
  } // end main

  //This method starts a RMI registry on the local host, if
  //it does not already exists at the specified port number.
  private static void startRegistry(int RMIPortNum)
    throws RemoteException{
    try {
      Registry registry = 
        LocateRegistry.getRegistry(RMIPortNum);
      registry.list( );  
        // This call will throw an exception
        // if the registry does not already exist
    }
    catch (RemoteException e) { 
      // No valid registry at that port.
      Registry registry = 
        LocateRegistry.createRegistry(RMIPortNum);
    }
  } // end startRegistry

} // end class
