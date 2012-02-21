package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Cashier;

public enum CashierController {
	INSTANCE;

	
	//private ArrayList<Cashier> Cashiers = new ArrayList<Cashier>();// = new List<Cashier>();
	private List<Cashier> Cashiers = Collections.synchronizedList(new ArrayList<Cashier>());
	
	

	
	public boolean CashierSignIn(Cashier cashier){
		
		for(Cashier c : Cashiers)
		{
			if(c.getId() == cashier.getId())
			{
				return false;
			}
		}
		
		cashier.setLoggedIn();
		Cashiers.add(cashier);
		return true; 
	}
	
	public boolean CashierSignOut(Cashier cashier){
		return Cashiers.remove(cashier); 
	}
	
	
	/*
	public void ReturnCompletedOrder(Cashier cashier, Order order)
	{
		if(Cashiers.contains(cashier))
			cashier.addItemToFinishedOrders(order);
		else
			Cashiers.get(generator.nextInt(Cashiers.size())).addItemToFinishedOrders(order);			
	}*/
	
	/*public void DayStart()
	{
	
		
	}
	
	public void DayEnd()
	*/
}
