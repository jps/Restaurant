//package def;

import controller.CashierController;
import controller.CookController;
import models.*;
import clients.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boolean RestaurantOpen = true;
		
			
			Cashier ca2 = new Cashier("Nick","Cage");
			Cashier ca3 = new Cashier("Chris","Davis");
			
			
			CashierController.INSTANCE.CashierSignIn(ca2);
			CashierController.INSTANCE.CashierSignIn(ca3);
			

			ca2.start();
			ca3.start();
			
			Cook co1 = new Cook("James", "Spencer");
			Cook co2 = new Cook("Alice", "Stringer");
			Cook co3 = new Cook("Amber", "Ryan");
			Cook co4 = new Cook("Joel", "Herber");		
					
			CookController.INSTANCE.CookSignIn(co1);
			CookController.INSTANCE.CookSignIn(co2);
			CookController.INSTANCE.CookSignIn(co3);
			CookController.INSTANCE.CookSignIn(co4);
			
			co1.start();
			co2.start();
			co3.start(); 
			co4.start();
			
			//BaseClient testClient = new BaseClient();
			//BaseClient testClient1 = new BaseClient();
			@SuppressWarnings("unused")
			CashierGUI cashierGUI = new CashierGUI(0,0,200,410, "Cashier 1"); 
			@SuppressWarnings("unused")
			CashierGUI cashierClient1 = new CashierGUI(220,0,200,410, "Cashier 2");
			
			@SuppressWarnings("unused")
			CookGUI cookGUI = new CookGUI(0,420,200, 410, "cookClientTest");
			@SuppressWarnings("unused")
			CookGUI cookClient2 = new CookGUI(220,420,200, 410, "cookClientTest2");
			
			//ServerGUI serverGUI = new ServerGUI(450,0,750,410,"Server");
			
			while(RestaurantOpen)
			{
			
			}
	}

}
