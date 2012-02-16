package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import models.Cashier;
import models.Order;


public enum OrderController {
	INSTANCE;
	
	private Long CurrentOrderNumber = (long) 0;
	private Queue<Order> OrderQueue = new ConcurrentLinkedQueue<Order>(); 
	
	//private List<Order> Cookedat = Collections.synchronizedList(new ArrayList<Order>());
	private List<Order> Cooked = Collections.synchronizedList(new ArrayList<Order>());
	private List<Order> FinishedOrders = Collections.synchronizedList(new ArrayList<Order>());
	
	
	
	public Long getNextOrderNumber() {
		IncrementOrderNumber();
		return CurrentOrderNumber;
	}

	public void IncrementOrderNumber()
	{
	    CurrentOrderNumber++;
		++CurrentOrderNumber;
	}
	
	public void AddOrderToQueue(Order order)
	{
		OrderQueue.add(order);
		//TODO: this could be handled with offer this will mean that no oders are lost ie if the list is full, however is this a likely condition.
	}
	
	public Order GetNextItemOffQueue()
	{
		return OrderQueue.poll(); //returns null if empty
	}
	
	
	public void AddOrderToCooked(Order order)
	{
		Cooked.add(order);
	}
	
	public Order GetCookedOrder(Cashier cashier)
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -60);
		
		for(Order i : Cooked)
		{
			if(i.getCashier() == cashier)
			{
				Cooked.remove(i);
				return i;
				
			}else if( now.getTime().compareTo(i.getCookedAt()) > 0)//if after 60 seconds give the order out to another cashier
			{
				Cooked.remove(i);
				return i;
			}
		}
		
		return null;
	}
	
	public void AddOrderToFinished(Order order)
	{
		FinishedOrders.add(order);
	}
	
	
	public String[] GetCurrentOrderList()
	{
		
		Order[] orders = (Order[]) OrderQueue.toArray();//convert to array so collection doesn't change during iteration
		String OrdersInQueue[] = new String[orders.length];
		
		for(int i = 0; i < orders.length; i++)
			OrdersInQueue[i] = orders.toString(); 
		
		return OrdersInQueue;
	}
	
	
}