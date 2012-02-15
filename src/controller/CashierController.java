package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Order;
import models.Cashier;

public enum CashierController {
	INSTANCE;

	private Random generator = new Random();	
	//private ArrayList<Cashier> Cashiers = new ArrayList<Cashier>();// = new List<Cashier>();
	private List<Cashier> Cashiers = Collections.synchronizedList(new ArrayList<Cashier>());
	
	
	public int GetRandomWaitTime()
	{
		return generator.nextInt(5001);
	}
	
	public void CashierSignIn(Cashier cashier){
		cashier.setLoggedIn();
		Cashiers.add(cashier);
	}
	
	public void CashierSignOut(Cashier cashier){
		Cashiers.remove(cashier); 
	}
	
	public void ReturnCompletedOrder(Cashier cashier, Order order)
	{
		if(Cashiers.contains(cashier))
			cashier.addItemToFinishedOrders(order);
		else
			Cashiers.get(generator.nextInt(Cashiers.size())).addItemToFinishedOrders(order);			
	}
	
	/*public void DayStart()
	{
	
		
	}
	
	public void DayEnd()
	*/
}
