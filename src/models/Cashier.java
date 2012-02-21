package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import clients.CashierClient;

//import java.util.List;
import enumerations.OrderStatus;

public class Cashier extends RestaurantMember {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ArrayList<Order> CompletedOrders = new ArrayList<Order>(); 
	private transient CashierClient cashierClient; 
	
	private List<Order> FinishedOrders = new ArrayList<Order>();
	private Queue<Order> CompletedOrders = new ConcurrentLinkedQueue<Order>(); 
	public Cashier(String firstName, String secondName, CashierClient client) {
		super(firstName, secondName);
		setName("Cashier:"+firstName+' '+secondName);
		cashierClient = client;
		
	}

	
	public synchronized void run()
	{
		CashierMessage("been started");
		while(getLoggedIn())
		{
			//create order
			takeOrder();
			//check finished orders 
			checkFinishedOrders();
			
			try {
				 wait(GetRandomWaitTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void takeOrder()
	{
		Order order = new Order(this);
		cashierClient.outQueue.OutQueue.add(order);
		//OrderController.INSTANCE.AddOrderToQueue(order);
		CashierMessage("taken an order");
	}
	
	public void checkFinishedOrders()
	{
		//populate the list
		
		Order order = CompletedOrders.poll();
		if (order != null)
		{
			//proccess order add to this cashiers completed and also send back to the sever for it's completed list
			order.setOrderStatus(OrderStatus.completed);
			FinishedOrders.add(order);
			cashierClient.outQueue.OutQueue.add(order);
		}else{
			//try and get more completed orders
			cashierClient.outQueue.OutQueue.add(this.getIdentity());			
		}
	}
		

	
	public int GetRandomWaitTime()
	{
		Random generator = new Random();	
		return generator.nextInt(5001);
	}
		
		
		/*
		for(@SuppressWarnings("unused")
		int i = 10; 1 < 10; i--)//get up to 10 completed orders 
		{
			Order order = OrderController.INSTANCE.GetCookedOrder(this);
			if(order == null)
				break; //returns null if none present so break
			
			CompletedOrders.add(order);
		}
		
		if(!CompletedOrders.isEmpty())
		{	
			while(!CompletedOrders.isEmpty())
			{
				Order completedOrder = CompletedOrders.poll();
				completedOrder.setOrderStatus(OrderStatus.completed);
				OrderController.INSTANCE.AddOrderToFinished(completedOrder);
			}
		}else
		{
			CashierMessage("no completed orders");
		}*/

	
	public synchronized void addItemToFinishedOrders(Order order)
	{
		CompletedOrders.add(order);
	}
	
	public synchronized void removeItemFromFinishedOrders(Order order)
	{
		CompletedOrders.remove(order);
	}
	
	
	public void CashierMessage(String action)
	{
		System.out.println("Cashier:"+ this.getMemberName() + " has " + action + ".");
	}
	
}
