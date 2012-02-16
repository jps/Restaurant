package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
