package common;
//Application to build/connect components in the garage package



public class Application
{
	public static void main(String[] args)
	{
    //Construct all the components
	  ParkingGarage  garage = new ParkingGarage();
	  ParkingGarageController controller = new ParkingGarageController();
	  ParkingGarageView view = new ParkingGarageView();
	  
	  //Notify each component of the other components it needs
	  garage.addView(view);
	  controller.addModel(garage);
	  view.addModel(garage);
	  view.addController(controller);
	  
	  //Build the application, then show it on the screen
	  view.build();
	  view.show();
	}
}