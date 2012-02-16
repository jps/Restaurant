package protocol;

import models.Cashier;
import models.Cook;
import models.Order;

import controller.*;

public class RestaurantProtocol {
	
	public String processInput(String theInput) {
		
		String theOutput = null; 
		
		
		
		
		return theOutput;
	}
	
	
	public Object processInput(Object obj)
	{
		System.out.println("Cashier Received :" + obj.toString());
		
		if(obj instanceof Cashier)
		{
			System.out.println("Cashier Received");
			Cashier cashier = (Cashier)obj;
			if(!cashier.getLoggedIn())
				if(CashierController.INSTANCE.CashierSignIn(cashier))
				{
					//signed in correctly
					System.out.println("Signed in");
					return "SignedIn";
				}else{
					//sign in failed close the client
					
					return "Failed";
				}
			else
				if(CashierController.INSTANCE.CashierSignOut(cashier))
				{
					return "SignedOut";
					
				}else{
					//this shouldn't occur
					return "Failed";
				}
			//
		}else if( obj instanceof Cook)
		{
			//sign in 
			
			
			//sign off
			
		}else if( obj instanceof Order)
		{
			
			
		}
		
		
		return null;
	}
}
